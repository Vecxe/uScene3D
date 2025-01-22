package org.robok.engine

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

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import org.robok.engine.compose.GDXWidget
import org.robok.engine.compose.rememberGDXState
import org.robok.engine.feature.scene.editor.interfaces.EmptyObjectActionListener
import org.robok.engine.feature.scene.editor.interfaces.ObjectListener
import org.robok.engine.feature.scene.editor.objects.SceneObject

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent { MaterialTheme { Screen(savedInstanceState) } }
  }

  @Composable
  fun Screen(savedInstanceState: Bundle?) {
    val state = rememberGDXState()

    GDXWidget(state = state)
    GDXScreen(state = state)

    state.objectListener =
      object : ObjectListener {
        override fun onObjectClick(sceneObject: SceneObject, x: Float, y: Float) {
          // do someting when user click in object
        }
      }
    state.objectActionListener = state.fragment?.sceneEditorView ?: EmptyObjectActionListener()
  }

  override fun exit() {
    finish()
  }
}
