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

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.robok.engine.Strings

@Composable
fun getOptions(context: Context): List<OptionModel> {
  return listOf(
    OptionModel(icon = Icons.Rounded.Add, text = stringResource(Strings.option_add_object)),
    OptionModel(icon = Icons.Rounded.Android, text = "Option 2"),
    OptionModel(icon = Icons.Rounded.Android, text = "Option 3"),
    OptionModel(icon = Icons.Rounded.Android, text = "Option 4"),
    OptionModel(icon = Icons.Rounded.Android, text = "Option 5"),
    OptionModel(icon = Icons.Rounded.Android, text = "Option 6"),
  )
}
