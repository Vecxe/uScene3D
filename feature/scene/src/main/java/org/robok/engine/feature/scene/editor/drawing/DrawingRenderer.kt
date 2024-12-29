package org.robok.engine.feature.scene.editor.drawing

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class DrawingRenderer() {

  var shapeRenderer = ShapeRenderer()

  fun start(camera: PerspectiveCamera) {
    // Configura a matriz de projeção do ShapeRenderer
    shapeRenderer.projectionMatrix = camera.combined

    // Inicia o ShapeRenderer
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
  }

  fun end() {
    shapeRenderer.end()
  }

  fun drawGrid3D(width: Float, depth: Float, cellSize: Float, lineThickness: Float) {

    var centerX = 0f
    var centerY = 0f
    var centerZ = 0f

    // Calcular os limites do plano
    val startX = centerX - width / 2
    val endX = centerX + width / 2
    val startZ = centerZ - depth / 2
    val endZ = centerZ + depth / 2
    shapeRenderer.color = Color.GRAY

    // Desenha linhas horizontais (ao longo do eixo X)
    var z = startZ
    while (z <= endZ) {
      if (Math.abs(z - centerZ) < cellSize / 2) {
        // Desenha a linha central mais grossa com múltiplas linhas próximas
        shapeRenderer.line(
          startX,
          centerY,
          z - lineThickness / 2,
          endX,
          centerY,
          z - lineThickness / 2,
        )
        shapeRenderer.line(
          startX,
          centerY,
          z + lineThickness / 2,
          endX,
          centerY,
          z + lineThickness / 2,
        )
      } else {
        shapeRenderer.line(startX, centerY, z, endX, centerY, z)
      }
      z += cellSize
    }

    // Desenha linhas verticais (ao longo do eixo Z)
    var x = startX
    while (x <= endX) {
      if (Math.abs(x - centerX) < cellSize / 2) {
        // Desenha a linha central mais grossa com múltiplas linhas próximas
        shapeRenderer.line(
          x - lineThickness / 2,
          centerY,
          startZ,
          x - lineThickness / 2,
          centerY,
          endZ,
        )
        shapeRenderer.line(
          x + lineThickness / 2,
          centerY,
          startZ,
          x + lineThickness / 2,
          centerY,
          endZ,
        )
      } else {
        shapeRenderer.line(x, centerY, startZ, x, centerY, endZ)
      }
      x += cellSize
    }
  }
}
