package com.isthisalis.recore.graphics.material;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.textures.Texture;
import com.isthisalis.recore.graphics.window.Params;

/**
 * Material
 */
public class Material {

  private float scaleX, scaleY;
  private boolean transparent = false;

  private Texture texture;
  private ShaderProgram program;


  public Material(Texture texture, ShaderProgram program) {
    this.texture = texture;
    this.program = program;
  }


  public void setScale(float x, float y) {
    scaleX = x;
    scaleY = y;
  }


  public void setTexture(Texture texture) {
    this.texture = texture;
  }


  public void setTransparency(boolean value) {
    transparent = value;
  }


  public void bind() {
    program.use();
    texture.bind();
    if (transparent) Params.getWindowInst().blend(true);
  }


  public ShaderProgram getProgram() {
    return program;
  }


  public Texture getTexture() {
    return texture;
  }
}
