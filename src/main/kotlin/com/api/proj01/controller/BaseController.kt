package com.api.proj01.controller

import com.api.proj01.model.dto.APIResponse
import com.api.proj01.model.search.Search
import com.api.proj01.service.BaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

open class BaseController<T : Any>(private val baseService: BaseService<T, Search>) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = APIResponse(data = baseService.getById(id))

    @PostMapping("/search")
    fun getAllBySearch(@RequestBody search: Search) = baseService.getAllBySearch(search)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody user: T) = APIResponse(data = baseService.add(user))

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestBody users: List<T>) = baseService.delete(users)
}