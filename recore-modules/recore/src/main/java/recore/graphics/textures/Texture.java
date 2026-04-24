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
  public Params params = new Params();

  // Custom. Prepares texture to use and binds
  public void bind() {
    glActiveTexture(GL_TEXTURE0+params.getUniformID());
    glBindTexture(GL_TEXTURE_2D, params.getID());
  }

  /**
   * Deletes texture
   */
  public void delete() {
    glDeleteTextures(params.getID());
  }

  // Custom. Uploads setted and loaded data into texture
  public void uploadData() {
     params.getImage().position(0);
     glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, params.getWidth(), params.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, params.getImage());
  }

  /**
   * Fully creates texture if image loaded
   * @param shaderProgram ShaderProgram with shaders to use with texture
   */
  public void createTexture(ShaderProgram shaderProgram) {
    params.setID(glGenTextures());

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
    texCoord = glGetUniformLocation(shaderProgram.getID(), params.getUniformName());
    glUniform1i(texCoord, params.getUniformID());
    
      // Cleans image buffer
    STBImage.stbi_image_free(params.getImage());
  }
    // Updates uniform
  public void update() {
    glUniform1i(texCoord, params.getUniformID());
  }

    // Returns texture parameters instance
  public Params getParams() {
    return params;
  }

}
