package com.isthisalis.recore.graphics.textures;

  // ReCore imports
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.shaders.uniforms.TextureUniform;

  // Lombok imports
import lombok.Getter;
import lombok.RequiredArgsConstructor;

  // OpenGL imports.
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.nio.ByteBuffer;

  // STB imports.
import org.lwjgl.stb.STBImage;

/**
 * OpenGL texture wrap.
 */
@RequiredArgsConstructor
public class Texture {

  /**
   * Number storing textures number.
   */
  private static @Getter int textures = 0;
  
  /**
   * OpenGL texture ID.
   */
  private @Getter int id;
  
  /**
   * Texture size parameters.
   */
  private final @Getter int width, height;

  /**
   * Raw texture image data.
   */
  private final ByteBuffer image;

  /**
   * Texture uniform. Used in shaders.
   */
  private @Getter TextureUniform uniform;
 

  /** 
   * Prepares texture to use.
   */
  public void bind() {
    glActiveTexture(GL_TEXTURE0 + textures);
    glBindTexture(GL_TEXTURE_2D, id);
  }


  /**
   * Deletes texture.
   */
  public void delete() {
    glDeleteTextures(id);
  }


  /**
   * Uploads setted and loaded data into texture.
   * @deprecated Scheduled for removal in 2.0.0.
   */
  @Deprecated(since = "1.0.0", forRemoval = true)
  public void uploadData() {
     image.position(0);
     glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
  }


  /**
   * Fully creates texture if image loaded.
   * @param program ShaderProgram with shaders to use with texture.
   * @param uniformName Texture uniform name in shader.
   */
  public void createTexture(ShaderProgram program, String uniformName) {
    id = glGenTextures();
    textures++;

    bind();
          // Texture parameters
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
      glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        // Uploads pre-configured and loaded data
    uploadData();
    glGenerateMipmap(GL_TEXTURE_2D);

      // Setting uniform
    program.use(); 
    uniform = new TextureUniform(
      glGetUniformLocation(program.getId(), uniformName),
      GL_TEXTURE0 + (textures - 1),
      uniformName);

    glUniform1i(uniform.location(), 0);
    
      // Cleans image buffer
    STBImage.stbi_image_free(image);
  }


  /**
   * Updates texture uniform.
   */
  public void update() {
    glUniform1i(uniform.location(), 0);
  }
}
