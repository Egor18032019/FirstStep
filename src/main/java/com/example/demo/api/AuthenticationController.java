package com.example.demo.api;


import com.example.demo.model.JwtAuthenticationResponse;
import com.example.demo.model.SignInRequest;
import com.example.demo.model.SignUpRequest;
import com.example.demo.model.TokenRequest;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtTokenService;
import com.example.demo.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = EndPoint.API + EndPoint.AUTH)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication Controller", description = "Контроллер для аутентификации и регистрации пользователей")
public class AuthenticationController {
      AuthenticationService authenticationService;
      JwtTokenService jwtService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping(EndPoint.REGISTER)
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping(EndPoint.LOGIN)
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @PostMapping(EndPoint.REFRESH)
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody TokenRequest request) {
        // Проверка на валидность токена уже была произведене
        return ResponseEntity.ok(jwtService.refreshToken(request.getToken()));
    }
}
