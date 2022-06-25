package com.betterlifeapps.std.components.feedback

import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import com.betterlifeapps.std.BaseViewModel
import com.betterlifeapps.std.R
import com.betterlifeapps.std.common.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow

class FeedbackViewModel : BaseViewModel() {
    private val selectedFeedbackItem = MutableStateFlow<FeedbackItem?>(null)

    val description = MutableStateFlow("")

    fun onFeedbackItemSelected(item: FeedbackItem) {
        selectedFeedbackItem.value = item
    }

    fun onDescriptionChanged(text: String) {
        description.value = text
    }

    fun sendFeedback(packageName: String) {
        val subject = "App feedback"
        val body = createHeader(packageName) + SEPARATOR + description.value
        val data = Uri.parse("mailto:?subject=$subject&body=$body&to=$FEEDBACK_EMAIL")
        val intent = Intent(Intent.ACTION_VIEW, data)
        postUiEvent(UiEvent.StartActivity(intent))
    }

    private fun createHeader(packageName: String): String {
        return "[$packageName][${selectedFeedbackItem.value?.name}]"
    }

    enum class FeedbackItem(@StringRes val textRes: Int) {
        BAD_DESIGN(R.string.feedback_item_1),
        CRASHES_OR_MISBEHAVIOR(R.string.feedback_item_2),
        MISSING_FUNCTIONS(R.string.feedback_item_3),
        LOCALIZATIONS(R.string.feedback_item_4),
        OTHER(R.string.feedback_item_5)
    }

    companion object {
        private const val FEEDBACK_EMAIL = "betterlifeappsofficial+feedback@gmail.com"
        private const val SEPARATOR = "PLEASE DON'T DELETE THIS\\n ******************** \\n"
    }
}