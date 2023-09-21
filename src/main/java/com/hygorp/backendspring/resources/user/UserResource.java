package com.hygorp.backendspring.resources.user;

import com.hygorp.backendspring.models.category.Category;
import com.hygorp.backendspring.models.user.User;
import com.hygorp.backendspring.models.user.UserDTO;
import com.hygorp.backendspring.models.user.UserAuthenticationDTO;
import com.hygorp.backendspring.models.user.enums.UserRole;
import com.hygorp.backendspring.services.security.TokenService;
import com.hygorp.backendspring.services.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Authenticate User", description = "Must authenticate a User and response a BearerToken",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = UserAuthenticationDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<String> login(@RequestBody UserAuthenticationDTO user) {
        UsernamePasswordAuthenticationToken usernameAndPassword = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        Authentication auth = authenticationManager.authenticate(usernameAndPassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    @Operation(summary = "Create User", description = "Must create a User, making you able to authenticate yourself",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = UserAuthenticationDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> register(@RequestBody UserAuthenticationDTO user) {
        if(userService.checkLoginExisting(user.login())) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(user.login(), encryptedPassword, UserRole.USER);
        userService.saveUser(newUser);
        return ResponseEntity.ok().build();
    }
}
