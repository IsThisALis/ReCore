package recore.graphics.camera;

import recore.graphics.shaders.ShaderProgram;
import recore.graphics.window.Params;

import org.joml.Vector3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL20.*;

public class Camera {

  Vector3f position;
  Vector2f screenSize;

  float screenWidth, screenHeight;
  float zoom = 1.0f;
  float zoomEditValue = 0.0f;

  int location;

  ShaderProgram attachedProgram;

  public Camera(float x, float y, float z, ShaderProgram program) {
    position = new Vector3f(x, y, z);
    screenWidth = (float) Params.getParams().getWidth();
    screenHeight = (float) Params.getParams().getHeight();
    attachedProgram = program;
    location = glGetUniformLocation(attachedProgram.getID(), "uVPMatrix");
  }
  
  public void setScreenSize(int width, int height) {
 
    float x = (float) width;
    float y = (float) height;

    screenSize = new Vector2f();
    screenSize.set(x, y);
  }

  public void move(float x, float y, float speed, float timeDelta) { 

    position.y += y*speed*timeDelta;
    position.x += x*speed*timeDelta;

  }

  public void setPosition(float x, float y) {
    position.y = y;
    position.x = x;
  }

  public Vector3f getPosition() {
    return position;
  }

  public Matrix4f getViewMatrix() {
    return new Matrix4f().translate(-position.x, -position.y, -position.z);
  }

  public Matrix4f getProjectionMatrix() {
    float w = screenWidth / 2f / zoom;
    float h = screenHeight / 2f / zoom;
    return new Matrix4f().ortho2D(-w, w, -h, h);
}

  public Matrix4f getVPMatrix() {
    return getProjectionMatrix().mul(getViewMatrix());
  }

  public void update() {
    attachedProgram.use();
    Matrix4f vp = getVPMatrix();
    float data[] = new float[16];
    vp.get(data);
    glUniformMatrix4fv(location, false, data);
  }

  public void addZoom(float value) {
    zoomEditValue = value;
    zoom *= zoomEditValue;
  }

  public void subZoom(float value) {
    zoomEditValue = -value;
    zoom /= zoomEditValue;
  }

  public float getZoom() {
    return zoom;
  }

  public void checkZoom() {
    System.out.println("zoom: "+zoom+" zoomEditValue: "+zoomEditValue);
  }
}
