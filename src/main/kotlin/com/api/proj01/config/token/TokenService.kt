package com.api.proj01.config.token

import com.api.proj01.model.User
import com.api.proj01.service.implementation.UserService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: UserService,
) {
    fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("userId", user.id)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): User? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as Long
            userService.getById(userId)
        } catch (e: Exception) {
            null
        }
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as Long
            val user = userService.getById(userId)
            user.username.equals(userDetails.username) && !isTokenExpired(token)
        } catch (e: Exception) {
            null
        }
    }

    // Verifica se o token expirou
    private fun isTokenExpired(token: String): Boolean {
        return try {
            val expiration = jwtDecoder.decode(token).expiresAt
            expiration?.isBefore(Date().toInstant())
            true
        } catch (e: Exception) {
            false
        }
    }

    // Verifica se o token JWT é válido (implementação básica)
    fun isValidToken(token: String): Boolean {
        return try {
            jwtDecoder.decode(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(token: String, userDetails: UserDetails): Authentication {
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }
}