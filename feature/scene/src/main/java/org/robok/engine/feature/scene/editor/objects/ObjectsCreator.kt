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
import com.badlogic.gdx.graphics.VertexAttribute
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.math.Vector3
import org.robok.engine.feature.scene.editor.controller.CameraInputController2
import org.robok.engine.feature.scene.editor.view.SceneEditorView

class ObjectsCreator(
    private val controller: CameraInputController2,
    private val sceneObjects: MutableList<SceneObject>
) {
    private val modelBuilder = ModelBuilder()
    private val size = Vector3(4f, 4f, 4f)

    fun get(): MutableList<SceneObject> = sceneObjects

    fun createCube() {
        size.set(4f, 4f, 4f)
        modelBuilder.begin()
        modelBuilder.node().id = "cubee"
        modelBuilder
            .part(
                "cubee",
                GL20.GL_TRIANGLES,
                VertexAttributes(
                    VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                    VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
                ),
                Material(ColorAttribute.createDiffuse(Color.CYAN))
            )
            .box(size.x, size.y, size.z)
        val model = modelBuilder.end()
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(controller.target.cpy())
        SceneEditorView.sceneState.objects.add(SceneObject(model, size, instance))
    }

    fun createTriangle() {
        size.set(2f, 6f, 4f)
        modelBuilder.begin()
        modelBuilder.node().id = "capsule"
        modelBuilder
            .part(
                "capsule",
                GL20.GL_TRIANGLES,
                VertexAttributes(
                    VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                    VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
                ),
                Material(ColorAttribute.createDiffuse(Color.BLUE))
            )
            .capsule(size.x, size.y, 20)
        val model = modelBuilder.end()
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(controller.target.cpy())
        sceneObjects.add(SceneObject(model, size, instance))
    }

    fun createTriangle2D() {
        modelBuilder.begin()
        val meshPartBuilder = modelBuilder.part(
            "triangle",
            GL20.GL_TRIANGLES,
            VertexAttributes(
                VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
            ),
            Material(ColorAttribute.createDiffuse(Color.GREEN))
        )

        val v1 = Vector3(-1f, 0f, 0f)
        val v2 = Vector3(1f, 0f, 0f)
        val v3 = Vector3(0f, 1f, 0f)
        val normal = Vector3(0f, 0f, 1f)

        meshPartBuilder.vertex(v1, normal, null, null)
        meshPartBuilder.vertex(v2, normal, null, null)
        meshPartBuilder.vertex(v3, normal, null, null)
        meshPartBuilder.index(0, 1, 2)

        val model = modelBuilder.end()
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(controller.target.cpy())
        sceneObjects.add(SceneObject(model, size, instance))
    }

    fun createSphere() {
        size.set(4f, 4f, 4f)
        modelBuilder.begin()
        modelBuilder.node().id = "sphere"
        modelBuilder
            .part(
                "sphere",
                GL20.GL_TRIANGLES,
                VertexAttributes(
                    VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                    VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
                ),
                Material(ColorAttribute.createDiffuse(Color.RED))
            )
            .sphere(size.x, size.y, size.z, 20, 20)
        val model = modelBuilder.end()
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(controller.target.cpy())
        sceneObjects.add(SceneObject(model, size, instance))
    }

    fun createCylinder() {
        size.set(2f, 6f, 2f)
        modelBuilder.begin()
        modelBuilder.node().id = "cylinder"
        modelBuilder
            .part(
                "cylinder",
                GL20.GL_TRIANGLES,
                VertexAttributes(
                    VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                    VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
                ),
                Material(ColorAttribute.createDiffuse(Color.BLUE))
            )
            .cylinder(size.x, size.y, size.z, 20)
        val model = modelBuilder.end()
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(controller.target.cpy())
        sceneObjects.add(SceneObject(model, size, instance))
    }

    fun createCone() {
        size.set(4f, 6f, 4f)
        modelBuilder.begin()
        modelBuilder.node().id = "cone"
        modelBuilder
            .part(
                "cone",
                GL20.GL_TRIANGLES,
                VertexAttributes(
                    VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                    VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
                ),
                Material(ColorAttribute.createDiffuse(Color.YELLOW))
            )
            .cone(size.x, size.y, size.z, 20)
        val model = modelBuilder.end()
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(controller.target.cpy())
        sceneObjects.add(SceneObject(model, size, instance))
    }

    fun createPlane() {
        modelBuilder.begin()
        modelBuilder.node().id = "plane"
        modelBuilder
            .part(
                "plane",
                GL20.GL_TRIANGLES,
                VertexAttributes(
                    VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                    VertexAttribute(VertexAttributes.Usage.Normal, 3, "a_normal")
                ),
                Material(ColorAttribute.createDiffuse(Color.MAGENTA))
            )
            .rect(
                Vector3(-5f, 0f, -5f),
                Vector3(5f, 0f, -5f),
                Vector3(5f, 0f, 5f),
                Vector3(-5f, 0f, 5f),
                Vector3(0f, 1f, 0f)
            )
        val model = modelBuilder.end()
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(controller.target.cpy())
        sceneObjects.add(SceneObject(model, size, instance))
    }

    fun createFinalModel(): Model {
        createCube()
        createTriangle()
        createTriangle2D()
        createSphere()
        createCylinder()
        createCone()
        createPlane()
        return modelBuilder.end()
    }
}