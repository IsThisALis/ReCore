package recore.graphics.textures;

  // graphic imports
import recore.graphics.shaders.ShaderProgram;

  // OpenGL imports
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

  // STB imports
import org.lwjgl.stb.STBImage;

public class Texture {

      // Used to set uniform
  int texCoord;
      // Parameters instance
  private Params params;
  public Texture() {
    params = new Params();
  }


  /** 
   * Custom. Prepares texture to use and binds
   */
  public void bind() {
    glActiveTexture(GL_TEXTURE0+params.getUniformId());
    glBindTexture(GL_TEXTURE_2D, params.getId());
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
   * @param shaderProgram ShaderProgram with shaders to use with texture
   */
  public void createTexture(ShaderProgram shaderProgram) {
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
    texCoord = glGetUniformLocation(shaderProgram.getId(), params.getUniformName());
    glUniform1i(texCoord, params.getUniformId());
    
      // Cleans image buffer
    STBImage.stbi_image_free(params.getImage());
  }


    /**
     * Updates texture uniform
     */
  public void update() {
    glUniform1i(texCoord, params.getUniformId());
  }



    /**
     * Getter for texture parameters 
     * @return Parameters class instance 
     */
  public Params getParams() {
    return params;
  }


}
