package com.techyourchance.coroutines.exercises.exercise9

import com.techyourchance.coroutines.exercises.exercise8.GetUserEndpoint
import com.techyourchance.coroutines.exercises.exercise8.User
import com.techyourchance.coroutines.exercises.exercise8.UsersDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.math.pow

class FetchAndCacheUsersUseCaseExercise9(
    private val getUserEndpoint: GetUserEndpoint,
    private val usersDao: UsersDao
) {

    suspend fun fetchAndCacheUsers(userIds: List<String>) = withContext(Dispatchers.Default) {

        userIds.map {userId ->
            async {
                val user = getUserEndpoint.getUser(userId)
                usersDao.upsertUserInfo(user)
            }
        }.awaitAll()
    }

}