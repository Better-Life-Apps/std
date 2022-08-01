package com.betterlifeapps.std.components.rating

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.betterlifeapps.std.R
import com.betterlifeapps.std.common.GooglePlayUtil
import com.betterlifeapps.std.components.feedback.FeedbackActivity
import com.betterlifeapps.std.components.settings.Settings
import com.betterlifeapps.std.components.settings.SettingsHolder
import com.betterlifeapps.std.ui.composables.UiButton
import com.betterlifeapps.std.ui.composables.VSpacer
import com.betterlifeapps.std.ui.theme.UiTheme
import com.betterlifeapps.std.ui.theme.Yellow_Star
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RatingDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                RatingDialogScreen {
                    val settings = SettingsHolder.get(requireContext())
                    settings.setBool(Settings.KEY_RATING_SUBMITTED, true)
                    dismiss()
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val bundle = bundleOf(TAG_RATING_DIALOG_RESULT to RatingDialogResult.DISMISSED)
        parentFragmentManager.setFragmentResult(TAG_RATING_DIALOG_RESULT, bundle)
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    enum class RatingDialogResult {
        DISMISSED
    }

    companion object {
        const val TAG_RATING_DIALOG_RESULT = "rating_dialog_result"
        const val TAG_RATING_DIALOG_FRAGMENT = "rating_dialog_fragment"

        fun newInstance() = RatingDialogFragment()
    }
}

@Composable
fun RatingDialogScreen(onRateClicked: () -> Unit) {
    UiTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var rating by remember { mutableStateOf(0) }
            val context = LocalContext.current
            Text(
                text = stringResource(id = R.string.please_rate_us),
                style = MaterialTheme.typography.h5,
            )
            VSpacer(height = 8)
            val ratingDescription = when (rating) {
                1 -> stringResource(id = R.string.rating_1)
                2 -> stringResource(id = R.string.rating_2)
                3 -> stringResource(id = R.string.rating_3)
                4 -> stringResource(id = R.string.rating_4)
                5 -> stringResource(id = R.string.rating_5)
                else -> " "
            }
            Text(text = ratingDescription, style = MaterialTheme.typography.h6)
            VSpacer(height = 8)
            LazyRow {
                items(5) { index ->
                    val resourceId = if (rating >= index + 1) {
                        R.drawable.ic_star_filled
                    } else {
                        R.drawable.ic_star_outlined
                    }

                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Yellow_Star),
                        modifier = Modifier
                            .clickable(
                                remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                rating = index + 1
                            }
                            .size(48.dp)
                            .padding(horizontal = 4.dp)
                    )
                }
            }

            VSpacer(height = 16)

            UiButton(stringRes = R.string.rate, onClick = {
                if (rating == 5) {
                    GooglePlayUtil.openAppInGooglePlay(context)
                } else {
                    FeedbackActivity.start(context)
                }
                onRateClicked()
            }, enabled = rating > 0)
        }
    }
}

@Preview
@Composable
fun RatingScreenPreview() {
    RatingDialogScreen {}
}