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
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import org.robok.engine.viewmodel.GDXViewModel
import org.robok.engine.compose.components.gdx.GDXState
import org.robok.engine.compose.components.gdx.GDXWidget
import org.robok.engine.compose.components.gdx.rememberGDXState
import org.robok.engine.feature.scene.editor.interfaces.EmptyObjectActionListener
import org.robok.engine.feature.scene.editor.interfaces.ObjectListener
import org.robok.engine.feature.scene.editor.objects.SceneObject

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

  private val viewModel: GDXViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent { MaterialTheme { Screen(savedInstanceState) } }
  }

  @Composable
  fun Screen(savedInstanceState: Bundle?) {
    val state = rememberGDXState()
    GDXScreen(state = state)

    state.objectListener =
      object : ObjectListener {
        override fun onObjectClick(sceneObject: SceneObject, x: Float, y: Float) {
          // do someting when user click in object
        }
      }
    state.objectActionListener = state.fragment?.sceneEditorView ?: EmptyObjectActionListener()
  }

  @Composable
  fun GDXScreen(state: GDXState) {
    Box(modifier = Modifier.fillMaxSize()) {
      GDXWidget(modifier = Modifier.fillMaxSize(), state = state)

      IconButton(
        onClick = { viewModel.setOptionsOpen(true) },
        modifier = Modifier.size(64.dp).align(Alignment.TopEnd),
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_launcher_foreground),
          contentDescription = "img",
        )
      }

      if (viewModel.isOptionsOpen) GDXBox()
    }
  }

  @Composable fun GDXBox() {}

  override fun exit() {
    finish()
  }
}
