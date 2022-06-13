package com.betterlifeapps.std

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betterlifeapps.std.common.UiEvent
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents: Flow<UiEvent> = _uiEvents

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        if (BuildConfig.DEBUG) {
            println(throwable.printStackTrace())
        }
    }

    protected fun runCoroutine(
        context: CoroutineContext = EmptyCoroutineContext + exceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, block = block)

    protected fun postUiEvent(event: UiEvent) {
        runCoroutine(
            Dispatchers.Main.immediate
        ) {
            _uiEvents.emit(event)
        }
    }

    /**
     * Terminal flow operator that launches the collection of the given flow with a provided [action]
     */
    protected fun <T> Flow<T>.collectFlow(action: suspend (value: T) -> Unit): Job =
        viewModelScope.launch { collect(action) }
}