package com.arthur.calculadorarpg.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> autenticar(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = loginService.autenticar(request);
        return ResponseEntity.ok(response);
    }
}