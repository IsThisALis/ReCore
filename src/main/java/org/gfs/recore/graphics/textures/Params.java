package org.gfs.recore.graphics.textures;

import java.nio.ByteBuffer;

public class Params {

  private String path;
  private String uniformName;

  private static Params params = new Params();

  private int id;
  private int width;
  private int height;
  
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

  public void setUniformName(String value) {
    uniformName = value;
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

  public static Params getParams() {
    return params;
  }
  
}
