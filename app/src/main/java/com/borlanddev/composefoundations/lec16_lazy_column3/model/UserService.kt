package com.borlanddev.composefoundations.lec16_lazy_column3.model

import com.github.javafaker.Faker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.Random

/**
 * Represents information about users and also provides methods for mapping users.
 */
interface UserService {

    /**
     * Get the list of users and listen for all further changes in the user list.
     */
    fun getUsers(): StateFlow<List<User>>

    /**
     * Remove the specified user. As a result the flow returned by [getUsers] will
     * automatically emit an updated list.
     */
    fun removeUser(user: User)

    companion object {
        fun get(): UserService = UserServiceImpl
    }
}

private object UserServiceImpl : UserService {

    private const val count = 100
    private val faker = Faker.instance(Random(0))
    private val images = mutableListOf(
        "https://unsplash.com/photos/rDEOVtE7vOs/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8Mnx8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/c_GmwfHBDzk/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8M3x8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/QXevDflbl8A/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8NHx8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/jmURdhtm7Ng/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8N3x8cGVyc29ufGVufDB8fHx8MTcwNzIzMzU0OHww&force=true",
        "https://unsplash.com/photos/pg_WCHWSdT8/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MTF8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM1NDh8MA&force=true",
        "https://unsplash.com/photos/t3zrEm88ehc/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MTh8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM1NDh8MA&force=true",
        "https://unsplash.com/photos/IF9TK5Uy-KI/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MjN8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2Mjl8MA&force=true",
        "https://unsplash.com/photos/jgSAuqMmJUE/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8Mjh8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2Mjl8MA&force=true",
        "https://unsplash.com/photos/15Vb4B_ma_s/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8MzZ8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2Mjl8MA&force=true",
        "https://unsplash.com/photos/hh3ViD0r0Rc/download?ixid=M3wxMjA3fDB8MXxzZWFyY2h8NDN8fHBlcnNvbnxlbnwwfHx8fDE3MDcyMzM2NDJ8MA&force=true"
    )

    private val usersMutableStateFlow = MutableStateFlow(generateUsers())
    override fun getUsers(): StateFlow<List<User>> = usersMutableStateFlow

    override fun removeUser(user: User) {
        usersMutableStateFlow.update { oldUsers -> oldUsers - user }
    }

    private fun generateUsers() = List(count) { index ->
        val id = index + 1L
        User(
            id = id,
            photoUrl = images[index % images.size],
            name = faker.name().fullName(),
            status = faker.shakespeare().hamletQuote()
        )
    }

}