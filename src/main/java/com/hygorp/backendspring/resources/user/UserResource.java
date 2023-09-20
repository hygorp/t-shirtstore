package com.hygorp.backendspring.resources.user;

import com.hygorp.backendspring.models.user.User;
import com.hygorp.backendspring.models.user.UserDTO;
import com.hygorp.backendspring.services.security.TokenService;
import com.hygorp.backendspring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user) {
        UsernamePasswordAuthenticationToken usernameAndPassword = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        Authentication auth = authenticationManager.authenticate(usernameAndPassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDTO user) {
        if(userService.checkLoginExisting(user.login())) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(user.login(), encryptedPassword, user.role());
        userService.saveUser(newUser);
        return ResponseEntity.ok().build();
    }
}
