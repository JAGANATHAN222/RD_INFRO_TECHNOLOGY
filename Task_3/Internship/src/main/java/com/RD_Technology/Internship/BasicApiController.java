package com.RD_Technology.Internship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class BasicApiController {

    @Autowired
    private AppService appService;

    @GetMapping("/hello")
    String hello() {
        appService.printGoal();
        return "Hello from Task 3 API";
    }

    @GetMapping("/hello/{id}")
    String helloWithId(@PathVariable int id) {
        return "Hello user with id: " + id;
    }

    @GetMapping("/info")
    String info(@RequestParam("value") long value) {
        return "Info value: " + value;
    }

    @PostMapping("/user")
    String createUser(@RequestParam("name") String user) {
        return "User created: " + user;
    }

    @PostMapping("/user/body")
    String createUserByBody(@RequestBody String user) {
        return "User from body: " + user;
    }

    @PutMapping("/user")
    String updateUser() {
        return "User updated successfully";
    }

    @DeleteMapping("/user")
    String deleteUser() {
        return "User deleted successfully";
    }
}
