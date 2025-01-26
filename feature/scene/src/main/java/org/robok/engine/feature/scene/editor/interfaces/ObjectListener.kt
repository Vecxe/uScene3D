package org.robok.engine.feature.scene.editor.interfaces

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

import com.badlogic.gdx.math.Vector2
import org.robok.engine.feature.scene.editor.objects.SceneObject

interface ObjectListener {
  fun onTap(x: Float, y: Float, count: Int, button: Int) = Unit
  fun onObjectClick(sceneObject: SceneObject, x: Float, y: Float)
  fun onZoom(initialDistance: Float, distance: Float)
  fun onPinch(initialPointer1: Vector2, initialPointer2: Vector2, pointer1: Vector2, pointer2: Vector2)
}