package com.isthisalis.recore.core;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import com.isthisalis.recore.graphics.shaders.Shader;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.shaders.ShaderTypes;
import com.isthisalis.recore.graphics.textures.Texture;
import com.isthisalis.recore.util.NIO;

import lombok.Getter;

/**
 * AssetManager class. Loads assets like textures, shaders, shader programs.
 */
public final class AssetManager {
  
    /**
     * List storing all loaded textures through AssetManager instance.
     * @see {@link com.isthisalis.recore.graphics.textures.Texture}
     */
    private @Getter List<Texture> loadedTextures = new ArrayList<>();

    /**
     * List storing all loaded shaders through AssetManager instance.
     * @see {@link com.isthisalis.recore.graphics.shaders.Shader}
     */
    private @Getter List<Shader> loadedShaders = new ArrayList<>();

    /**
     * List storing all loaded shader programs through AssetManager instance.
     * @see {@link com.isthisalis.recore.graphics.shaders.ShaderProgram}
     */
    private @Getter List<ShaderProgram> loadedShaderPrograms = new ArrayList<>();

    /**
     * Protected constructor to avoid creating AssetManager instanses.
     */
    protected AssetManager() {}


    /**
     * Loads texture from storage (Jar / external file system).
     * @param path Path to image file. 
     * @return loaded Texture.
     * @see {@link com.isthisalis.recore.graphics.textures.Texture}
     */
    public Texture loadTexture(String path) {
      ByteBuffer imageBuffer;
      try (MemoryStack stack = MemoryStack.stackPush()) {

        // Puts raw image data in ByteBuffer
        byte[] data = NIO.load(path);
        imageBuffer = ByteBuffer.allocateDirect(data.length);
        imageBuffer.put(data);
        imageBuffer.position(0);
            

        // Puts image data into IntBuffers
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer comp = stack.mallocInt(1);
      

        // Loads image with STB
        STBImage.stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 4);
        
        // Throws exception if texture image not loaded
        if (image == null) {
          throw new RuntimeException("Error while loading texture: "+STBImage.nstbi_failure_reason());
        }
      
          // Transfers texture parameters to int variables
        int height = h.get();
        int width = w.get();
        

        Texture texture = new Texture(width, height, image);
        loadedTextures.add(texture);
        return texture;
      }
    }


    /**
     * Loads shader from file with code.
     * @param path Path to shader code file.
     * @param type Shader type
     * @return Loaded shader.
     * @see {@link com.isthisalis.recore.graphics.shaders.ShaderTypes}.
     */
    public Shader loadShader(String path, ShaderTypes type) {
      String code = new String(NIO.load(path), StandardCharsets.UTF_8);

      Shader shader = new Shader(code, type);
      loadedShaders.add(shader);
      return shader;
    }

    
    public ShaderProgram loadShaderProgram(Shader... shaders) {
      ShaderProgram program = new ShaderProgram();
      if (program.hasCache()) program.loadCache();
      else {
        for (Shader shader : shaders) {
          program.attachShader(shader);
        }
        program.link();
      }

      loadedShaderPrograms.add(program);
      return program;
    }

    public void cleanup() {
      for (Texture tex : loadedTextures) {
        tex.delete();
      }
      loadedTextures.clear();

      for (ShaderProgram program : loadedShaderPrograms) {
        program.delete();
      }
      loadedShaderPrograms.clear();

      for (Shader shader : loadedShaders) {
        shader.delete();
      }
      loadedShaders.clear();

    }
}
