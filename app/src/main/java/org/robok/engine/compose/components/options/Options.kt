package org.robok.engine.compose.components.options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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