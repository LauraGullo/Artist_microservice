package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role){
        log.info("saving role to the database");
        return roleRepository.save(role);
    }

    @Transactional
    public void addRoleToUser(String fullName, String roleName){
        log.info("Adding {} role to user: {}", roleName, fullName);
        User user = userRepository.findByFullName(fullName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void updateUser(User user, Long id) {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "user with id: " + id + "doesn´t exists"
        ));
        if (!Objects.equals(updatedUser.getFullName(), user.getFullName())){
            updatedUser.setFullName(user.getFullName());
        }

    }


    @Override
    public UserDetails loadUserByUsername(String fullName) throws UsernameNotFoundException {
        User user = userRepository.findByFullName(fullName);
        if(user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not Found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getFullName(), user.getPassword(),authorities);
    }
}
