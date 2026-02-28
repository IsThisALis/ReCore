package org.gfs.recore.graphics.textures;

import org.gfs.recore.graphics.shaders.ShaderProgram;

import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.stb.STBImage;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {

  Params params = Params.getParams(); 

  public Texture() {
    params.setID(glGenTextures());
  }

  public void bind() {
    glBindTexture(GL_TEXTURE_2D, params.getID());
  }

  public void delete() {
    glDeleteTextures(params.getID());
  }

  public void uploadData() {
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, params.getWidth(), params.getHeight(), 0, GL_RGB, GL_UNSIGNED_BYTE, params.getImage());
  }

  public void createTexture(Texture texture, ShaderProgram shaderProgram) {
    glActiveTexture(GL_TEXTURE0);
    texture.bind();

      glTexParameteri(params.getID(), GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
      glTexParameteri(params.getID(), GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
      glTexParameteri(params.getID(), GL_TEXTURE_MIN_FILTER, GL_LINEAR);
      glTexParameteri(params.getID(), GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    
    texture.uploadData();
    glGenerateMipmap(params.getID());

    System.out.println("uniform name: "+params.getUniformName());

    int texCoord = glGetUniformLocation(shaderProgram.getID(), params.getUniformName());
    glUniform1i(texCoord, 0);

    if(texCoord==-1) {
      throw new RuntimeException("Unable to get uniform location");
    }
    
    STBImage.stbi_image_free(params.getImage());

  }


}
