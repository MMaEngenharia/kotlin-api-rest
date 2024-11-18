package com.api.proj01.controller

import com.api.proj01.model.User
import com.api.proj01.service.implementation.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(userService: UserService) : BaseController<User>(userService)