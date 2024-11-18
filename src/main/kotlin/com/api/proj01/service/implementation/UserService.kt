package com.api.proj01.service.implementation

import com.api.proj01.exception.BadRequestException
import com.api.proj01.exception.ForBiddenException
import com.api.proj01.exception.NotFoundException
import com.api.proj01.model.User
import com.api.proj01.model.dto.APIResponse
import com.api.proj01.model.search.Search
import com.api.proj01.repository.UserRepository
import com.api.proj01.service.BaseService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.Calendar.getInstance


@Service
class UserService(private val userRepository: UserRepository) : BaseService<User, Search>, Base() {

    private val NOT_FOUND = "Registro não encontrado"
    private val USER_MESSAGE_FORBIDDEN = "Falha no Login"

    @Transactional(readOnly = true)
    override fun getById(id: Long): User {
        return userRepository.findById(id).orElseThrow { throw NotFoundException(NOT_FOUND) }
    }

    @Transactional(readOnly = true)
    fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username)
            .orElseThrow {
                throw ForBiddenException(USER_MESSAGE_FORBIDDEN)
            }
    }

    @Transactional(readOnly = true)
    override fun getAllBySearch(search: Search): APIResponse {
        val pagination = getPageable(search)
        val response = getApiResponse(userRepository.findAll(pagination))
        return response
    }

    @Transactional
    override fun add(user: User): User {
        val newUser = userRepository.findByUsername(user.username);
        if (Objects.nonNull(newUser))
            throw BadRequestException("Usuário já está cadastrado")
        return userRepository.save(user)
    }

    @Transactional
    override fun update(user: User): User {
        val userFromDB = getById(user.id)
        // Fazer o merge do user com o userFromDB

        // Salvar
        userRepository.save(userFromDB)
        return userFromDB
    }

    @Transactional
    override fun delete(users: List<User>) {
        //val usersFromDB: List<User> = mutableListOf()
        users.forEach {
            val userFromDB = getById(it.id)
            userFromDB.deleted = getInstance()
            userRepository.save(userFromDB)
            //usersFromDB.plus(userFromDB)
        }
        //userRepository.saveAll(usersFromDB)
    }
}