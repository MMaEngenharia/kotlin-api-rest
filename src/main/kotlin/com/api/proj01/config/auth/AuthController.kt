package com.api.proj01.config.auth

import com.api.proj01.model.dto.APIResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDTO): ResponseEntity<APIResponse> {
        return ResponseEntity.ok(authService.login(payload))
    }

    @PostMapping("/logout")
    fun logout(@RequestBody payload: LoginDTO): ResponseEntity<APIResponse> {
        return ResponseEntity.ok(APIResponse(message = "Logaout realizado com sucesso"))
    }
}