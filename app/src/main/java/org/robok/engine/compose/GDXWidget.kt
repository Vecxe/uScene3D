package org.robok.engine.compose

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
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.robok.engine.feature.scene.editor.fragment.LibGDXFragment
import org.robok.engine.feature.scene.editor.interfaces.ObjectActionListener
import org.robok.engine.feature.scene.editor.interfaces.ObjectListener

@Composable
fun GDXWidget(modifier: Modifier = Modifier, state: GDXState) {
  val context = LocalContext.current
  val gdxFactory = remember { setGDXFactory(context = context, state = state) }
  
  AndroidView(factory = { gdxFactory }, modifier = modifier)
}

private fun setGDXFactory(context: Context, state: GDXState): FrameLayout {
  val frame = FrameLayout(context)
  frame.id = View.generateViewId()
    
  frame.post {
    val fragment = LibGDXFragment(state.objectListener)
    state.fragment = fragment

    val fragmentManager = (context as AppCompatActivity).supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    fragmentTransaction.replace(frame.id, fragment)
    fragmentTransaction.commit()
  }

  return frame
}

@Composable fun rememberGDXState() = remember { GDXState() }

data class GDXState(
  var fragment: LibGDXFragment? = null,
  var objectListener: ObjectListener? = null,
  var objectActionListener: ObjectActionListener? = null,
)
