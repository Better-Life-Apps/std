package com.betterlifeapps.std.ext

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Deferred<T>.getCompletedOrDefault(default: T): T {
    return try {
        getCompleted()
    } catch (e: IllegalStateException) {
        e.printStackTrace()
        default
    }
}