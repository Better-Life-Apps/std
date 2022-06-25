package com.betterlifeapps.std.components.rating

import android.content.Context
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.betterlifeapps.std.R
import com.betterlifeapps.std.common.GooglePlayUtil
import com.betterlifeapps.std.components.feedback.FeedbackActivity
import com.betterlifeapps.std.ui.composables.UiButton
import com.betterlifeapps.std.ui.composables.VSpacer
import com.betterlifeapps.std.ui.theme.UiTheme
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
                    dismiss()
                }
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}

@Composable
fun RatingDialogScreen(onRateClicked: () -> Unit) {
    UiTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var rating by remember { mutableStateOf(0) }
            val context = LocalContext.current
            VSpacer(height = 8)
            Text(text = stringResource(id = R.string.rate_us), style = MaterialTheme.typography.h5)
            VSpacer(height = 8)
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_face_24),
                contentDescription = null
            )
            Text(text = stringResource(id = R.string.rating_5), style = MaterialTheme.typography.h6)
            VSpacer(height = 8)
            LazyRow {
                items(5) { index ->
                    val resourceId = if (rating >= index + 1) {
                        R.drawable.ic_baseline_star_rate_24
                    } else {
                        R.drawable.ic_baseline_star_outline_24
                    }

                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = null,
                        Modifier.clickable(
                            remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            rating = index + 1
                        }
                    )
                }
            }

            VSpacer(height = 16)

            UiButton(stringRes = R.string.rate, onClick = {
                if (rating == 5) {
                    GooglePlayUtil.openAppInGooglePlay(context)
                } else {
                    openFeedbackActivity(context)
                }
                onRateClicked()
            }, enabled = rating > 0)
        }
    }
}

private fun openFeedbackActivity(context: Context) {
    val intent = Intent(context, FeedbackActivity::class.java)
    context.startActivity(intent)
}

@Preview
@Composable
fun RatingScreenPreview() {
    RatingDialogScreen() {}
}