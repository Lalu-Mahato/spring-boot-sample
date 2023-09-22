package com.example.springbootsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootsample.dto.CreateUserDto;
import com.example.springbootsample.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "List all users")
    // @ApiResponses(value = {
    // @ApiResponse(responseCode = "200", description = "Found the User", content =
    // {
    // @Content(mediaType = "application/json", schema = @Schema(implementation =
    // User.class)) }),
    // @ApiResponse(responseCode = "400", description = "Invalid id supplied",
    // content = @Content),
    // @ApiResponse(responseCode = "404", description = "User not found", content =
    // @Content)
    // })
    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            ResponseEntity<Object> response = userService.findAll();
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateUserDto createUserDto) {
        try {
            ResponseEntity<Object> response = userService.addUser(createUserDto);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }
}
