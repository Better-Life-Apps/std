package com.betterlifeapps.std.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UiRadioGroup(
    items: List<RadioGroupItem>,
    selected: RadioGroupItem,
    onItemSelected: (RadioGroupItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        items.forEach { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (item == selected),
                        onClick = { onItemSelected(item) }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (item == selected),
                    modifier = Modifier.padding(all = Dp(value = 8F)),
                    onClick = {
                        onItemSelected(item)
                    }
                )
                Text(
                    text = item.text,
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

data class RadioGroupItem(val text: String)