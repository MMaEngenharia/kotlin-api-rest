package com.api.proj01.repository

import com.api.proj01.model.User
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface UserRepository : PagingAndSortingRepository<User, Long> {
    fun findById(id: Long): Optional<User>
    fun findByUsername(username: String): Optional<User>
    fun save(user: User): User
}
