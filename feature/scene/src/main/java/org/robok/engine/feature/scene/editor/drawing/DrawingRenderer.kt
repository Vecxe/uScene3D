package org.robok.engine.feature.scene.editor.drawing

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class DrawingRenderer() {

  var shapeRenderer = ShapeRenderer()
  var angle = 0f
  var progress = 0f
  var animation = true
  var radius = 0f
  lateinit var camera : PerspectiveCamera

  fun start(camera: PerspectiveCamera) {
    // Configura a matriz de projeção do ShapeRenderer
    this.camera = camera
    shapeRenderer.projectionMatrix = camera.combined

    // Inicia o ShapeRenderer
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
  }

  fun end() {
    shapeRenderer.end()
  }
 
  //draw direction focused
  fun drawTargetCross(size: Float) {
    // Ponto onde a câmera está focada h
    val target = camera.position.cpy().add(camera.direction.cpy().scl(camera.position.dst(0f, 0f, 0f)))

    // Frente e trás (eixo Z)
    shapeRenderer.line(target.x - size, target.y, target.z, target.x + size, target.y, target.z) // Horizontal
    shapeRenderer.line(target.x, target.y - size, target.z, target.x, target.y + size, target.z) // Vertical

    // Direita e esquerda (eixo X)
    shapeRenderer.line(target.x, target.y, target.z - size, target.x, target.y, target.z + size) // Horizontal
    shapeRenderer.line(target.x, target.y - size, target.z, target.x, target.y + size, target.z) // Vertical

    // Cima e baixo (eixo Y)
    shapeRenderer.line(target.x - size, target.y, target.z, target.x + size, target.y, target.z) // Horizontal
    shapeRenderer.line(target.x, target.y, target.z - size, target.x, target.y, target.z + size) // Vertical
}

  fun grid(width: Float, depth: Float, cellSize: Float, lineThickness: Float) {

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

  fun gridWithCircleAnimation(width: Float, depth: Float, cellSize: Float, lineThickness: Float) {

    // A variável radius deve ser controlada externamente, mas se você já estiver fazendo isso,
    // basta deixá-la aqui.
    radius += Gdx.graphics.deltaTime * 25f

    val centerX = 0f
    val centerY = 0f
    val centerZ = 0f

    val startX = centerX - width / 2
    val endX = centerX + width / 2
    val startZ = centerZ - depth / 2
    val endZ = centerZ + depth / 2

    shapeRenderer.color = Color.GRAY

    // Desenha linhas horizontais dentro do raio
    var z = startZ
    while (z <= endZ) {
      if (Math.hypot(z - centerZ.toDouble(), 0.0).toFloat() <= radius) {
        // Desenha linha horizontal
        shapeRenderer.line(startX, centerY, z, endX, centerY, z)

        // Desenha linha central mais grossa (horizontal)
        if (Math.abs(z - centerZ) < cellSize / 2) {
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
        }
      }
      z += cellSize
    }

    // Desenha linhas verticais dentro do raio
    var x = startX
    while (x <= endX) {
      if (Math.hypot(x - centerX.toDouble(), 0.0).toFloat() <= radius) {
        // Desenha linha vertical
        shapeRenderer.line(x, centerY, startZ, x, centerY, endZ)

        // Desenha linha central mais grossa (vertical)
        if (Math.abs(x - centerX) < cellSize / 2) {
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
        }
      }
      x += cellSize
    }
  }

  fun gridAndNails(
    width: Float,
    depth: Float,
    cellSize: Float,
    lineThickness: Float,
    lineThicknessVertical: Float,
    verticalHeight: Float,
  ) {
    val centerX = 0f
    val centerY = 0f
    val centerZ = 0f

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
        // Linha central mais grossa
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
        // Linha central mais grossa
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

    // Desenha linhas verticais subindo nos cantos de cada célula
    x = startX
    while (x <= endX) {
      z = startZ
      while (z <= endZ) {
        // Desenha uma linha subindo no canto de cada célula
        shapeRenderer.line(
          x - lineThicknessVertical / 2,
          centerY,
          z,
          x - lineThicknessVertical / 2,
          centerY + verticalHeight,
          z,
        )
        shapeRenderer.line(
          x + lineThicknessVertical / 2,
          centerY,
          z,
          x + lineThicknessVertical / 2,
          centerY + verticalHeight,
          z,
        )
        z += cellSize
      }
      x += cellSize
    }
  }

  fun gridAndVerticalLines(
    width: Float,
    depth: Float,
    cellSize: Float,
    lineThickness: Float,
    lineThicknessVertical: Float,
    verticalHeight: Float,
  ) {
    val centerX = 0f
    val centerY = 0f
    val centerZ = 0f

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

    // Desenha linhas verticais nos cantos do quadrado final com grossura configurável
    val corners =
      listOf(
        Pair(startX, startZ), // Canto inferior esquerdo
        Pair(startX, endZ), // Canto superior esquerdo
        Pair(endX, startZ), // Canto inferior direito
        Pair(endX, endZ), // Canto superior direito
      )

    for ((cornerX, cornerZ) in corners) {
      // Desenha uma linha grossa única no canto usando deslocamento na posição Y
      shapeRenderer.box(
        cornerX - lineThicknessVertical / 2,
        centerY,
        cornerZ - lineThicknessVertical / 2,
        lineThicknessVertical,
        verticalHeight,
        lineThicknessVertical,
      )
    }
  }

  fun drawRotatedSquare(centerX: Float, centerY: Float, size: Float, rotate: Boolean) {

    if (rotate) {
      angle += 90f * Gdx.graphics.deltaTime
    } else angle = 0f

    // Calcula os vértices do quadrado
    val halfSize = size / 2

    // Define os cantos do quadrado original (antes da rotação)
    val vertices =
      arrayOf(
        Pair(-halfSize, -halfSize), // Inferior esquerdo
        Pair(halfSize, -halfSize), // Inferior direito
        Pair(halfSize, halfSize), // Superior direito
        Pair(-halfSize, halfSize), // Superior esquerdo
      )

    // Aplica a rotação aos vértices
    val rotatedVertices =
      vertices.map { (x, y) ->
        val rotatedX =
          x * Math.cos(Math.toRadians(angle.toDouble())) -
            y * Math.sin(Math.toRadians(angle.toDouble()))
        val rotatedY =
          x * Math.sin(Math.toRadians(angle.toDouble())) +
            y * Math.cos(Math.toRadians(angle.toDouble()))
        Pair(rotatedX.toFloat() + centerX, rotatedY.toFloat() + centerY)
      }

    // Desenha o quadrado rotacionado
    // shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
    shapeRenderer.color = Color.WHITE
    for (i in rotatedVertices.indices) {
      val (x1, y1) = rotatedVertices[i]
      val (x2, y2) = rotatedVertices[(i + 1) % rotatedVertices.size]
      shapeRenderer.line(x1, y1, x2, y2)
    }
    //   shapeRenderer.end()
  }
}
