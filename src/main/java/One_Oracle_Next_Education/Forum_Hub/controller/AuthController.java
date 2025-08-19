package One_Oracle_Next_Education.Forum_Hub.controller;

import One_Oracle_Next_Education.Forum_Hub.dto.LoginRequest;
import One_Oracle_Next_Education.Forum_Hub.dto.TokenResponse;
import One_Oracle_Next_Education.Forum_Hub.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        String token = authService.autenticarUsuario(request.email(), request.senha());
        return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
    }
}