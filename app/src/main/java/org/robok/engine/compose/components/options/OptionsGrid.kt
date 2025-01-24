package org.robok.engine.compose.components.options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsGrid(options: List<OptionModel>) {
  val rows = options.chunked(2)

  Column {
    rows.forEach { row ->
      Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
        row.forEach { option -> OptionItem(option = option) }
      }
    }
  }
}
