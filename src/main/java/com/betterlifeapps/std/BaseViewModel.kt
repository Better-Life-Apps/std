package com.betterlifeapps.std

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun runCoroutine(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, block = block)

    /**
     * Terminal flow operator that launches the collection of the given flow with a provided [action]
     */
    protected fun <T> Flow<T>.collectFlow(action: suspend (value: T) -> Unit): Job =
        viewModelScope.launch { collect(action) }
}