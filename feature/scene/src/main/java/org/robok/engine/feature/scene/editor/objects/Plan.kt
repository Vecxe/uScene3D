package org.robok.engine.feature.scene.editor.objects

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

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Mesh
import com.badlogic.gdx.graphics.VertexAttribute
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder

class Plan(width: Float, height: Float, profundity: Float, invertSide: Boolean) {

    private val planeMesh: Mesh
    private var model: Model? = null
    private var modelBuilder: ModelBuilder? = null

    init {
        planeMesh = Mesh(
            true,
            4,
            6,
            VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
            VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
        )

        // Define os vértices e índices
        val vertices: FloatArray
        val indices: ShortArray

        if (profundity == 0f) {
            // Plano horizontal
            vertices = floatArrayOf(
                -width / 2, 0f, -height / 2, 0f, 1f, 0f, // Vértice 1
                width / 2, 0f, -height / 2, 0f, 1f, 0f,  // Vértice 2
                width / 2, 0f, height / 2, 0f, 1f, 0f,  // Vértice 3
                -width / 2, 0f, height / 2, 0f, 1f, 0f  // Vértice 4
            )
            indices = if (invertSide) shortArrayOf(0, 3, 2, 2, 1, 0) else shortArrayOf(0, 1, 2, 2, 3, 0)
        } else {
            // Plano vertical
            vertices = floatArrayOf(
                -width / 2, -height / 2, 0f, 0f, 0f, 1f, // Vértice 1
                width / 2, -height / 2, 0f, 0f, 0f, 1f,  // Vértice 2
                width / 2, height / 2, 0f, 0f, 0f, 1f,  // Vértice 3
                -width / 2, height / 2, 0f, 0f, 0f, 1f  // Vértice 4
            )
            indices = if (invertSide) shortArrayOf(0, 3, 2, 2, 1, 0) else shortArrayOf(0, 1, 2, 2, 3, 0)
        }

        planeMesh.setVertices(vertices)
        planeMesh.setIndices(indices)
    }

    fun createObject() {
        modelBuilder = ModelBuilder().apply {
            begin()
            part(
                "plane",
                planeMesh,
                GL20.GL_TRIANGLES,
                Material(ColorAttribute.createDiffuse(Color.BLUE))
            )
            model = end()
        }
    }

    fun getInstance(): ModelInstance {
        return ModelInstance(model!!)
    }

    fun getModel(): Model? {
        return model
    }
}