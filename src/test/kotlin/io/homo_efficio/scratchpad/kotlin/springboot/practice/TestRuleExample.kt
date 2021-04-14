package io.homo_efficio.scratchpad.kotlin.springboot.practice

import kotlinx.coroutines.*
import kotlinx.coroutines.debug.junit4.CoroutinesTimeout
import org.junit.jupiter.api.Test

class TestRuleExample {

    private suspend fun someFunctionDeepInTheStack() {
        withContext(Dispatchers.IO) {
            delay(Long.MAX_VALUE) // Hang method
        }
    }

    @Test
    fun hangingTest() = runBlocking {
//        val job = launch {
        val job = withTimeout(1000L) {
            someFunctionDeepInTheStack()
        }
//        job.join() // Join will hang
    }
}
