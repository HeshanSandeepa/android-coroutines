package com.techyourchance.coroutines.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

object TestUtils {

    fun CoroutineScope.printCoroutineScopeInfo() {
        println()
        println("Scope: $this")
        println("Context: ${this.coroutineContext}")
        println("Job: ${this.coroutineContext[Job]}")
        println()
    }

    fun printJobsHierarchy(job: Job, nestLevel: Int = 0) {
        val indent = "    ".repeat(nestLevel)
        println("$indent- $job")
        for (childJob in job.children) {
            printJobsHierarchy(childJob, nestLevel + 1)
        }
        if (nestLevel == 0) {
            println()
        }
    }

}