package com.betterlifeapps.std.common

import android.content.Intent
import androidx.annotation.RawRes
import androidx.annotation.StringRes

abstract class UiEvent {
    data class ShowShortToast(val text: String) : UiEvent()
    data class ShowShortToastRes(@StringRes val textRes: Int) : UiEvent()
    data class PlaySoundRes(@RawRes val soundRes: Int) : UiEvent()
    data class StartActivity(val intent: Intent) : UiEvent()
}