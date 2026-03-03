package org.gfs.recore.graphics.textures;

import org.gfs.recore.graphics.shaders.ShaderProgram;

import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.stb.STBImage;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {
  int texCoord;
  public Params params = new Params();

  public void bind() {
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, params.getID());
  }

  public void delete() {
    glDeleteTextures(params.getID());
  }

  public void uploadData() {
     params.getImage().position(0);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, params.getWidth(), params.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, params.getImage());
  }

  public void createTexture(ShaderProgram shaderProgram) {
    params.setID(glGenTextures());

    bind();

      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    
      glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

    uploadData();
    glGenerateMipmap(GL_TEXTURE_2D);

    System.out.println("uniform name: "+params.getUniformName());

    texCoord = glGetUniformLocation(shaderProgram.getID(), params.getUniformName());
    glUniform1i(texCoord, params.getUniformID());
    
    STBImage.stbi_image_free(params.getImage());
  }

  public void update() {
    glUniform1i(texCoord, params.getUniformID());
  }

  public Params getParams() {
    return params;
  }

}
