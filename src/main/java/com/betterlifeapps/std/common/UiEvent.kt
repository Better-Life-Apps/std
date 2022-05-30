package com.betterlifeapps.std.common

import androidx.annotation.StringRes

abstract class UiEvent {
    data class ShowShortToast(val text: String) : UiEvent()
    data class ShowShortToastRes(@StringRes val textRes: Int) : UiEvent()
}