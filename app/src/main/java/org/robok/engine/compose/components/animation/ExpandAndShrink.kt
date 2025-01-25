package org.robok.engine.compose.components.animation

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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Animates the appearance and disappearance of [content] via an expanding and shrinking animation,
 * respectively.
 *
 * @param visible Defines whether the content should be visible
 * @param content Content to appear or disappear based on the value of [visible]
 */
@Composable
fun ExpandAndShrink(
  visible: Boolean,
  modifier: Modifier = Modifier,
  vertically: Boolean = true,
  content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
  AnimatedVisibility(
    visible = visible,
    enter =
      if (vertically) {
        expandVertically() + fadeIn()
      } else {
        expandHorizontally() + fadeIn()
      },
    exit =
      if (vertically) {
        shrinkVertically() + fadeOut()
      } else {
        shrinkHorizontally() + fadeOut()
      },
    content = content,
    modifier = modifier,
  )
}
