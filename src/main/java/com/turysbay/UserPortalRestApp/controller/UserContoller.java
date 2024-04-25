package com.turysbay.UserPortalRestApp.controller;

import com.turysbay.UserPortalRestApp.dto.LoginRequest;
import com.turysbay.UserPortalRestApp.entity.User;
import com.turysbay.UserPortalRestApp.exceptions.UserAlreadyExistsException;
import com.turysbay.UserPortalRestApp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserContoller {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> registrations(@Valid @RequestBody @NotNull User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }

        try {
            User registeredUser = userService.userRegistration(user);
            return ResponseEntity.created(new URI("/users/" + registeredUser.getId())).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this login already exists.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed.");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @NotNull LoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        if (userService.authenticateUser(login, password)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
        }
    }
}
