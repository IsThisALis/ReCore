package com.isthisalis.recore.graphics.textures;

  // graphic imports
import com.isthisalis.recore.graphics.shaders.ShaderProgram;

import lombok.RequiredArgsConstructor;

// OpenGL imports
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.nio.ByteBuffer;

// STB imports
import org.lwjgl.stb.STBImage;

  //@RequiredArgsConstructor
public class Texture {

    /**
     * Number storing textures number.
     */
  private static int textures = 0;
      // Used to set uniform
  private int location;
      // Parameters instance
  private Params params;

  private int width;
  private int height;
  private ByteBuffer image; 


  public Texture(int width, int height, ByteBuffer image) {
    this.width = width;
    this.height = height;
    this.image = image;
  }


    @Deprecated
  public Texture() {
    params = new Params();
  }
 

  /** 
   * Prepares texture to use and binds
   */
  public void bind() {
    glActiveTexture(GL_TEXTURE0 + textures);
    glBindTexture(GL_TEXTURE_2D, params.getId());
    textures++;
  }


  /**
   * Deletes texture, full cleanup
   */
  public void delete() {
    glDeleteTextures(params.getId());
    params = null;
  }


  /**
   * Custom. Uploads setted and loaded data into texture
   */
  public void uploadData() {
     params.getImage().position(0);
     glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, params.getWidth(), params.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, params.getImage());
  }


  /**
   * Fully creates texture if image loaded
   * @param program ShaderProgram with shaders to use with texture
   */
  public void createTexture(ShaderProgram program) {
    params.setId(glGenTextures());

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
    location = glGetUniformLocation(program.getId(), params.getUniformName());
    glUniform1i(location, 0);
    
      // Cleans image buffer
    STBImage.stbi_image_free(params.getImage());
  }


    /**
     * Updates texture uniform
     */
  public void update() {
    glUniform1i(location, 0);
  }


    /**
     * Getter for texture parameters 
     * @return Parameters class instance 
     */
  public Params getParams() {
    return params;
  }
}
