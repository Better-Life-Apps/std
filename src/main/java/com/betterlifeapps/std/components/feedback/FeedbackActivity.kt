package com.betterlifeapps.std.components.feedback

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.betterlifeapps.std.BaseActivity
import com.betterlifeapps.std.R
import com.betterlifeapps.std.common.UiEvent
import com.betterlifeapps.std.ui.composables.RadioGroupItem
import com.betterlifeapps.std.ui.composables.UiButton
import com.betterlifeapps.std.ui.composables.UiRadioGroup
import com.betterlifeapps.std.ui.composables.UiToolbar
import com.betterlifeapps.std.ui.composables.VSpacer
import com.betterlifeapps.std.ui.theme.UiTheme

class FeedbackActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UiTheme {
                FeedbackScreen {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, FeedbackActivity::class.java)
            context.startActivity(intent)
        }
    }
}

@Composable
fun FeedbackScreen(onBackPressed: () -> Unit) {
    val viewModel: FeedbackViewModel = viewModel()
    val context = LocalContext.current
    val event by viewModel.uiEvents.collectAsState(initial = null)
    if (event is UiEvent.StartActivity) {
        LocalContext.current.startActivity((event as UiEvent.StartActivity).intent)
    }
    Column {
        val navController = rememberNavController()
        UiToolbar(onBackButtonClick = {
            if (navController.currentDestination?.route == "feedback_issue") {
                onBackPressed()
            } else {
                navController.popBackStack()
            }
        })
        NavHost(navController = navController, startDestination = "feedback_issue") {
            composable("feedback_issue") {
                FeedbackIssuePage(
                    viewModel = viewModel,
                    onNextClicked = {
                        navController.navigate("feedback_details")
                    })
            }
            composable("feedback_details") {
                FeedbackDetailsPage(feedbackViewModel = viewModel, onNextClicked = {
                    viewModel.sendFeedback(context.packageName)
                })
            }
        }
    }
}

@Composable
fun FeedbackIssuePage(
    viewModel: FeedbackViewModel,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        VSpacer(height = 16)
        Text(
            text = stringResource(id = R.string.feedback_page_1_title),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.h5
        )
        VSpacer(height = 32)

        val items = FeedbackViewModel.FeedbackItem.values().map {
            RadioGroupItem(stringResource(id = it.textRes))
        }
        val selectedIndex =
            FeedbackViewModel.FeedbackItem.values().indexOf(viewModel.selectedFeedbackItem.value)
        val (selectedItem, onItemSelected) = remember {
            mutableStateOf<RadioGroupItem?>(
                items.getOrNull(
                    selectedIndex
                )
            )
        }
        UiRadioGroup(items = items, selected = selectedItem, onItemSelected = {
            onItemSelected(it)
            val index = items.indexOf(it)
            viewModel.onFeedbackItemSelected(FeedbackViewModel.FeedbackItem.values()[index])
        })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            UiButton(
                stringRes = R.string.next,
                onClick = { onNextClicked() },
                enabled = selectedItem != null
            )
        }
    }
}

@Composable
fun FeedbackDetailsPage(
    feedbackViewModel: FeedbackViewModel,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        VSpacer(height = 16)
        Text(
            text = stringResource(id = R.string.feedback_page_2_title),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.h5
        )

        VSpacer(height = 32)

        val text by feedbackViewModel.description.collectAsState()

        OutlinedTextField(
            value = text,
            onValueChange = feedbackViewModel::onDescriptionChanged,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        VSpacer(height = 16)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            UiButton(
                stringRes = R.string.send,
                onClick = { onNextClicked() },
                enabled = text.isNotEmpty()
            )
        }
    }
}