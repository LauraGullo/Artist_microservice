package com.example.demo.controller;



import com.example.demo.model.User;
import com.example.demo.model.Role;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @PreAuthorize("hasRole('ADMINISTRATOR')")
        @GetMapping
        public List<User> getUser(){
            return userService.getUser();
        }

        @PostMapping("/saveRole")
        public Role saveRole(@RequestBody Role role){

            return userService.saveRole(role);
        }

        @PutMapping("/{fullName}/add/{name}")
        public ResponseEntity<?> addRole(@PathVariable String fullName, @PathVariable String name){
            userService.addRoleToUser(fullName, name);
            return  ResponseEntity.ok("Role added");
        }

        @GetMapping("/{id}")
        public Optional<User> getUser(@PathVariable Long id){
            return userService.getUser(id);
        }

        @PostMapping("/addUser")
        public User addUser(@RequestBody User user){
            return userService.addUser(user);
        }

        @DeleteMapping("/deleteUser/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Long id){
            userService.deleteUser(id);
            return ResponseEntity.ok("User delete");
        }

        @PutMapping("/updateUser/{id}")
        public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id){

            userService.updateUser(user, id);
            return ResponseEntity.ok("user update");
        }
}
