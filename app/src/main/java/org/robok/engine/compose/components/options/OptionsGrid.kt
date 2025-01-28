package org.robok.engine.compose.components.options

/*
 *  This file is part of Robok © 2024.
 *
 *  Robok is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Robok is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with Robok.  If not, see <https://www.gnu.org/licenses/>.
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsGrid(
  modifier: Modifier = Modifier,
  options: List<OptionModel>,
  onOptionClick: (OptionModel) -> Unit
) {
  val rows = options.chunked(2)

  Column(modifier = modifier) {
    rows.forEach { row ->
      Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        row.forEach { option -> OptionItem(option = option, onOptionClick = onOptionClick) }
      }
    }
  }
}
