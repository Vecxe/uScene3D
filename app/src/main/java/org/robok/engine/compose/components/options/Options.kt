package org.robok.engine.compose.components.options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberOptions() = remember {
  listOf(
    OptionModel(icon = Icons.Outlined.Android, text = "Option 1"),
    OptionModel(icon = Icons.Outlined.Android, text = "Option 2"),
    OptionModel(icon = Icons.Outlined.Android, text = "Option 3"),
    OptionModel(icon = Icons.Outlined.Android, text = "Option 4"),
    OptionModel(icon = Icons.Outlined.Android, text = "Option 5"),
    OptionModel(icon = Icons.Outlined.Android, text = "Option 6"),
  )
}
