package com.api.proj01.config

import com.api.proj01.exception.BadRequestException
import com.api.proj01.exception.ForBiddenException
import com.api.proj01.exception.NotFoundException
import com.api.proj01.model.dto.APIResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.io.IOException

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun notFound(e: NotFoundException): ResponseEntity<APIResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message?.let { APIResponse(message = it) })
    }

    @ExceptionHandler(ForBiddenException::class)
    fun forBidden(e: ForBiddenException): ResponseEntity<APIResponse> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.message?.let { APIResponse(message = it) })
    }

    @ExceptionHandler(InvalidBearerTokenException::class)
    fun invalidBearerToken(e: InvalidBearerTokenException): ResponseEntity<APIResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message?.let { APIResponse(message = it) })
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun notReadable(e: HttpMessageNotReadableException): ResponseEntity<APIResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message?.let { APIResponse(message = it) })
    }

    @ExceptionHandler(BadRequestException::class)
    fun badRequest(e: BadRequestException): ResponseEntity<APIResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message?.let { APIResponse(message = it) })
    }

    @ExceptionHandler(IOException::class)
    fun iOException(e: IOException): ResponseEntity<APIResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message?.let { APIResponse(message = it) })
    }
}