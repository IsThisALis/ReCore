package com.isthisalis.recore.graphics.textures;

  // Java imports
import java.nio.ByteBuffer;

/**
 * Texture parameters class. 
 * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
 */
@Deprecated(since = "1.0.0", forRemoval = true)
public class Params {

  private String path;
  private String uniformName;

  private int id;
  private int width;
  private int height;
  private int uniformId;
  
  private ByteBuffer image;

    // Int
    // id
  public int getId() {
    return id;
  }

  public void setId(int value) {
    id = value;
  }

    // width
  public int getWidth() {
    return width;
  }

  public void setWidth(int value) {
    width = value;
  }

    // height
  public int getHeight() {
    return height;
  }

  public void setHeight(int value) {
    height = value;
  }

    // uniformId
  public int getUniformId() {
    return uniformId;
  }
    // ByteBuffer
    // image
  public ByteBuffer getImage() {
    return image;
  }

  public void setImage(ByteBuffer value) {
    image = value;
  }
    // String
    // path
  public String getPath() {
    return path;
  }

  public void setPath(String value) {
    path = value;
  }
    // uniformName
  public String getUniformName() {
    return uniformName;
  }
    //  Uniform
  public void setUniform(String uniformName, int uniformId) {
    this.uniformName = uniformName;
    this.uniformId = uniformId;
  }
}
