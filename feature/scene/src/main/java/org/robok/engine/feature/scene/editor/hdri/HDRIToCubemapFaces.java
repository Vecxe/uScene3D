package org.robok.engine.feature.scene.editor.hdri;

import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBImageWrite;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class HDRIToCubemapFaces {
    private static final int CUBEMAP_SIZE = 512; // Tamanho das faces do cubemap (pixels)

    public void convert(String hdriPath, String outputDirectory) {
       // String hdriPath = "path/to/your.hdr"; // Caminho do arquivo HDR
       // String outputDirectory = "path/to/output/"; // Diretório de saída das faces

        try {
            // Carregar o HDR
            HDRTexture hdrTexture = loadHDRI(hdriPath);

            // Gerar as faces do cubemap e salvar como imagens
            generateCubemapFaces(hdrTexture, outputDirectory);
            
            System.out.println("Faces do cubemap geradas com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Classe auxiliar para armazenar a textura HDR carregada
    private static class HDRTexture {
        int width, height;
        FloatBuffer data;

        public HDRTexture(int width, int height, FloatBuffer data) {
            this.width = width;
            this.height = height;
            this.data = data;
        }
    }

    private static HDRTexture loadHDRI(String hdriPath) throws Exception {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            STBImage.stbi_set_flip_vertically_on_load(true);
            FloatBuffer data = STBImage.stbi_loadf(hdriPath, width, height, channels, 3);
            if (data == null) {
                throw new RuntimeException("Falha ao carregar HDRI: " + STBImage.stbi_failure_reason());
            }

            return new HDRTexture(width.get(), height.get(), data);
        }
    }

    private static void generateCubemapFaces(HDRTexture hdrTexture, String outputDirectory) throws Exception {
        // Nome das faces do cubemap
        String[] faceNames = {"right", "left", "top", "bottom", "front", "back"};

        // Vértices de direção para cada face
        Vector3[] faceDirections = {
                new Vector3(1, 0, 0), // Right
                new Vector3(-1, 0, 0), // Left
                new Vector3(0, 1, 0), // Top
                new Vector3(0, -1, 0), // Bottom
                new Vector3(0, 0, 1), // Front
                new Vector3(0, 0, -1) // Back
        };

        // Up vectors para as faces
        Vector3[] upVectors = {
                new Vector3(0, -1, 0), // Right
                new Vector3(0, -1, 0), // Left
                new Vector3(0, 0, 1), // Top
                new Vector3(0, 0, -1), // Bottom
                new Vector3(0, -1, 0), // Front
                new Vector3(0, -1, 0) // Back
        };

        for (int i = 0; i < 6; i++) {
            // Gerar a face com projeção cúbica
            ByteBuffer faceData = extractFace(hdrTexture, faceDirections[i], upVectors[i]);

            // Salvar a face como imagem PNG
            String outputPath = outputDirectory + faceNames[i] + ".png";
            saveFaceAsImage(faceData, CUBEMAP_SIZE, CUBEMAP_SIZE, outputPath);

            System.out.println("Face gerada e salva: " + faceNames[i]);
        }
    }

    private static ByteBuffer extractFace(HDRTexture hdrTexture, Vector3 faceDir, Vector3 upDir) {
        ByteBuffer faceBuffer = ByteBuffer.allocateDirect(CUBEMAP_SIZE * CUBEMAP_SIZE * 3 * Float.BYTES);

        Vector3 rightDir = faceDir.cross(upDir).normalize();

        for (int y = 0; y < CUBEMAP_SIZE; y++) {
            for (int x = 0; x < CUBEMAP_SIZE; x++) {
                float u = (x + 0.5f) / CUBEMAP_SIZE * 2 - 1;
                float v = (y + 0.5f) / CUBEMAP_SIZE * 2 - 1;

                Vector3 direction = faceDir.add(rightDir.multiply(u)).add(upDir.multiply(v)).normalize();
                int hdrIndex = getHDRIndexFromDirection(hdrTexture, direction);

                float r = hdrTexture.data.get(hdrIndex);
                float g = hdrTexture.data.get(hdrIndex + 1);
                float b = hdrTexture.data.get(hdrIndex + 2);

                faceBuffer.putFloat(r).putFloat(g).putFloat(b);
            }
        }

        faceBuffer.flip();
        return faceBuffer;
    }

    private static int getHDRIndexFromDirection(HDRTexture hdrTexture, Vector3 direction) {
        float u = 0.5f + (float) (Math.atan2(direction.z, direction.x) / (2 * Math.PI));
        float v = 0.5f - (float) (Math.asin(direction.y) / Math.PI);

        int x = (int) (u * hdrTexture.width);
        int y = (int) (v * hdrTexture.height);

        return (y * hdrTexture.width + x) * 3;
    }

    private static void saveFaceAsImage(ByteBuffer faceData, int width, int height, String outputPath) {
        STBImageWrite.stbi_write_png(outputPath, width, height, 3, faceData, width * 3 * Float.BYTES);
    }

    // Classe auxiliar para cálculos vetoriais
    private static class Vector3 {
        float x, y, z;

        Vector3(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        Vector3 add(Vector3 other) {
            return new Vector3(x + other.x, y + other.y, z + other.z);
        }

        Vector3 multiply(float scalar) {
            return new Vector3(x * scalar, y * scalar, z * scalar);
        }

        Vector3 cross(Vector3 other) {
            return new Vector3(
                    y * other.z - z * other.y,
                    z * other.x - x * other.z,
                    x * other.y - y * other.x
            );
        }

        Vector3 normalize() {
            float length = (float) Math.sqrt(x * x + y * y + z * z);
            return new Vector3(x / length, y / length, z / length);
        }
    }
}