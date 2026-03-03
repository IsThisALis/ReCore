package org.gfs.recore.graphics.textures;

import java.nio.ByteBuffer;

public class Params {

  private String path;
  private String uniformName;

  private static Params params = new Params();

  private int id;
  private int width;
  private int height;
  private int uniformId;
  
  private ByteBuffer image;

    //Int
  public int getID() {
    return id;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getUniformID() {
    return uniformId;
  }
    //ByteBuffer
  public ByteBuffer getImage() {
    return image;
  }
    //String
    //path
  public String getPath() {
    return path;
  }

  public void setPath(String value) {
    path = value;
  }

  public String getUniformName() {
    return uniformName;
  }

  public void setUniform(String value, int uniformID) {
    uniformName = value;
    uniformId = uniformID;
  }

  public void setImage(ByteBuffer value) {
    image = value;
  }

  public void setHeight(int value) {
    height = value;
  }

  public void setWidth(int value) {
    width = value;
  }

  public void setID(int value) {
    id = value;
  }
}
