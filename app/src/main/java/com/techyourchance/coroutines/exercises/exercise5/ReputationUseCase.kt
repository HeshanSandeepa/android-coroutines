package com.techyourchance.coroutines.exercises.exercise5

import com.techyourchance.coroutines.common.ThreadInfoLogger
import com.techyourchance.coroutines.exercises.exercise1.GetReputationEndpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReputationUseCase {
     suspend fun getReputationForUser(getReputationEndpoint: GetReputationEndpoint,  userId: String): Int =
        withContext(Dispatchers.Default) {
            logThreadInfo("getReputationForUser()")
            getReputationEndpoint.getReputation(userId)
        }

    private fun logThreadInfo(message: String) {
        ThreadInfoLogger.logThreadInfo(message)
    }

}