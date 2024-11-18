package com.api.proj01.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "user")
class User() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L

    @Column(
        name = "username",
        nullable = false
    )
    lateinit var username: String

    @Column(
        name = "password",
        nullable = false
    )
    lateinit var password: String

    @Column(name = "deleted")
    var deleted: Calendar? = null
}