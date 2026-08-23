package com.isthisalis.recore.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import com.isthisalis.recore.graphics.shaders.Shader;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.textures.Texture;

import lombok.Getter;

/**
 * AssetManager. WiP.
 */
public class AssetManager {
    private @Getter List<Texture> loadedTextures = new ArrayList<>();
    private @Getter List<Shader> loadedShaders;
    private @Getter List<ShaderProgram> loadedShaderPrograms;

    
    public Texture loadTexture(String path) {
        Path file = Path.of(path);

        ByteBuffer imageBuffer;
      try (MemoryStack stack = MemoryStack.stackPush()) {

        if (!path.endsWith("png")) throw new IOException(new Throwable("Not a valid image!"));

          // Puts raw image data in ByteBuffer
        imageBuffer = NIO.loadByteBuffer(file);
        imageBuffer.position(0);
            

          // Puts image data into IntBuffers
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer comp = stack.mallocInt(1);
      

          // Loads image with STB
        STBImage.stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 4);

      
          // Transfers texture parameters to int variables
        int height = h.get();
        int width = w.get();


          // Throws exception if texture image not loaded
        if (image == null) {
          throw new RuntimeException("Error while loading texture: "+STBImage.nstbi_failure_reason());
        }
        Texture texture = new Texture(width, height, image);
        loadedTextures.add(texture);
        return texture;

      } catch(IOException e) {
          System.out.println("ReCore: Encountered IO exception while loading file " + "[ " + path + " ] " + "[ " + e + " ]");
          return new Texture(0, 0, null);
        }
    }

    public void cleanup() {
      
    }
}
