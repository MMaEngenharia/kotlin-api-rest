package com.api.proj01.config.token

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class HashService {

    /**
     * checks whether the string matches the hash
     * @param input the string to be checked
     * @param hash the hashed string
     * @return true if hash(input) == hash, otherwise false
     */
    fun checkBcrypt(input: String, hash: String): Boolean {
        return BCrypt.checkpw(input, hash)
    }

    /**
     * hashes the string
     * @param input the string to be hashed
     * @return the hashed string
     */
    fun hashBcrypt(input: String): String {
        return BCrypt.hashpw(input, BCrypt.gensalt(10))
    }
}