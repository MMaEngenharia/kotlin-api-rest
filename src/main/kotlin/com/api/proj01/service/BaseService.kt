package com.api.proj01.service

import com.api.proj01.model.dto.APIResponse

interface BaseService<T : Any, S : Any> {
    fun getById(id: Long): T
    fun getAllBySearch(search: S): APIResponse
    fun add(entity: T): T
    fun update(entity: T): T
    fun delete(entities: List<T>)
}