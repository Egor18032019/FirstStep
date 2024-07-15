package com.example.demo.api;

import com.example.demo.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoint.USER)
@Tag(name = "Для пользователей")
public class UserController {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью USER")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("USER");
    }
}
