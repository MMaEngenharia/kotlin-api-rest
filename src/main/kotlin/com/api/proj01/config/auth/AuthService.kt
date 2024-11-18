package com.api.proj01.config.auth

import com.api.proj01.config.token.TokenService
import com.api.proj01.model.dto.APIResponse
import com.api.proj01.service.implementation.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    //private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService
) {

    @Transactional(readOnly = true)
    fun login(payload: LoginDTO): APIResponse {
        val user = userService.getUserByUsername(payload.username)

        /*val ps1 = hashService.hashBcrypt(user.password)
        System.out.println("ps1: " + ps1)
        val ps2 = hashService.hashBcrypt(user.password)
        System.out.println("ps2: " + ps2)*/

        // Valida se o usuário é válido
        /*if (!hashService.checkBcrypt(payloadUsername, user.password)) {
            throw ForBiddenException(USER_MESSAGE_FORBIDDEN)
        }*/
        return APIResponse(data = tokenService.createToken(user))
    }
}