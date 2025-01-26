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

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.badlogic.gdx.math.Vector2
import org.robok.engine.compose.components.animation.ExpandAndShrink
import org.robok.engine.compose.components.gdx.GDXState
import org.robok.engine.compose.components.gdx.GDXWidget
import org.robok.engine.compose.components.gdx.rememberGDXState
import org.robok.engine.compose.components.options.OptionsGrid
import org.robok.engine.compose.components.options.rememberOptions
import org.robok.engine.compose.theme.AppTheme
import org.robok.engine.feature.scene.editor.interfaces.EmptyObjectActionListener
import org.robok.engine.feature.scene.editor.interfaces.ObjectListener
import org.robok.engine.feature.scene.editor.objects.SceneObject
import org.robok.engine.viewmodel.GDXViewModel

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

  private val viewModel: GDXViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent { AppTheme { Screen(savedInstanceState) } }
  }

  /** Hide phone ui to better experience */
  private fun hideSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      window.setDecorFitsSystemWindows(false)
      window.insetsController?.let { controller ->
        controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
      }
    } else {
      @Suppress("DEPRECATION")
      window.decorView.systemUiVisibility =
        (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
  }

  @Composable
  fun Screen(savedInstanceState: Bundle?) {
    SideEffect { hideSystemUI() }
    val state = rememberGDXState()
    GDXScreen(state = state)

    state.objectListener =
      object : ObjectListener {
        override fun onTap(x: Float, y: Float, count: Int, button: Int) {}

        override fun onObjectClick(sceneObject: SceneObject, x: Float, y: Float) {}

        override fun onZoom(initialDistance: Float, distance: Float) {}

        override fun onPinch(
          initialPointer1: Vector2,
          initialPointer2: Vector2,
          pointer1: Vector2,
          pointer2: Vector2,
        ) {}

        override fun onTouchDown(x: Float, y: Float, count: Int, button: Int) {
          viewModel.setOptionsOpen(false)
        }
      }
    state.objectActionListener = state.fragment?.sceneEditorView ?: EmptyObjectActionListener()
  }

  @Composable
  fun GDXScreen(state: GDXState) {
    Box(modifier = Modifier.fillMaxSize()) {
      GDXWidget(modifier = Modifier.fillMaxSize(), state = state)

      ExpandAndShrink(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 20.dp), visible = viewModel.isOptionsOpen, vertically = false) {
        OptionsBox(modifier = Modifier.padding(16.dp))
      }

      IconButton(
        onClick = { viewModel.setOptionsOpen(!viewModel.isOptionsOpen) },
        modifier = Modifier.size(70.dp).align(Alignment.TopEnd),
      ) {
        Image(
          imageVector = Icons.Rounded.MoreVert,
          contentDescription = stringResource(R.string.common_word_more),
          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
      }
    }
  }

  @Composable
  fun OptionsBox(modifier: Modifier = Modifier) {
    val options = rememberOptions()
    Surface(
      modifier = modifier,
      shape = RoundedCornerShape(20.dp),
      color = MaterialTheme.colorScheme.surfaceContainerHigh,
    ) {
      OptionsGrid(options = options)
    }
  }

  override fun exit() {
    finish()
  }
}
