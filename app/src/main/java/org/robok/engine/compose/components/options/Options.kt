package org.robok.engine.compose.components.options

import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import org.robok.engine.R

val options = remember {
  listOf(
    OptionModel(icon = painterResource(id = R.drawable.ic_launcher_foreground), text = "Option 1"),
    OptionModel(icon = painterResource(id = R.drawable.ic_launcher_foreground), text = "Option 2"),
    OptionModel(icon = painterResource(id = R.drawable.ic_launcher_foreground), text = "Option 3"),
    OptionModel(icon = painterResource(id = R.drawable.ic_launcher_foreground), text = "Option 4"),
    OptionModel(icon = painterResource(id = R.drawable.ic_launcher_foreground), text = "Option 5"),
    OptionModel(icon = painterResource(id = R.drawable.ic_launcher_foreground), text = "Option 6"),
  )
}
