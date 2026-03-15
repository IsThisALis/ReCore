package org.gfs.recore.graphics.textures;

  // Java imports
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

    // Int
    // id
  public int getID() {
    return id;
  }

  public void setID(int value) {
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
  public int getUniformID() {
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
  public void setUniform(String value, int uniformID) {
    uniformName = value;
    uniformId = uniformID;
  }
}
