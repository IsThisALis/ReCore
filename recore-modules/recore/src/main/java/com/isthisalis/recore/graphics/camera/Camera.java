package com.isthisalis.recore.graphics.camera;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

// JOML imports
import org.joml.Vector3f;
import org.joml.Vector2f;
import org.joml.Matrix4f;

  // OpenGL imports
import static org.lwjgl.opengl.GL20.*;

public class Camera {

    // Vector3f values
  private @Getter Vector3f position;
  private Vector2f FOV;
  private Vector2f cache;

    // float values
  private float screenWidth, screenHeight;
  private @Getter @Setter float zoom = 1.0f;
  private float w, h;
  private float wX, wY;

    // Array values
  private float[] uniformData = new float[16];

    // Instances values
  private final @Getter Configuration config;

    // Matrix4f values 
  private Matrix4f projectionMatrix = new Matrix4f();
  private Matrix4f viewMatrix = new Matrix4f();
  private Matrix4f VPMatrix = new Matrix4f();


    /**
     * Constructor to initialize camera.
     * Requires OpenGL context.
     * @param config Camera configuration.
     */
  public Camera(@NonNull Configuration config) {
    this.config = config;
    position = new Vector3f(config.getPos().getX(), config.getPos().getY(), 0f);
    FOV = new Vector2f();
    cache = new Vector2f();

    screenWidth = (float) config.getWindow().getWidth();
    screenHeight = (float) config.getWindow().getHeight();
  }
  

    /**
     * Moving camera by x and y.
     * Uses speed and time delta between frames to get smooth moving.
     * @param x Value adding to x.
     * @param y Value adding to y.
     * @param speed Camera speed value.
     * @param timeDelta Time delta between past and now frames.
     */
  public void move(float x, float y, float speed, float timeDelta) { 
    position.y += y*speed*timeDelta;
    position.x += x*speed*timeDelta;

  }


    /**
     * Setter for camera position.
     * @param x Camera x coordinate.
     * @param y Camera y coordinate.
     */
  public void setPosition(float x, float y) {
    position.y = y;
    position.x = x;
  }

    /**
     * Setter for camera position.
     * @param position New position values in vector.
     */
  public void setPosition(Vector2f position) {
    this.position.x = position.x;
    this.position.y = position.y;
  }


    /**
     * Getter for view matrix.
     * @return View matrix (Matrix4f).
     */
  public Matrix4f getViewMatrix() {
    return viewMatrix.identity().translate(-position.x, -position.y, -position.z);
  }


     /**
      * Getter to calculate projection matrix.
      * @return Projection matrix (Matrix4f).
      */
  public Matrix4f getProjectionMatrix() {
    w = screenWidth / 2f / zoom;
    h = screenHeight / 2f / zoom;
    return projectionMatrix.identity().ortho(-w, w, -h, h, -1f, 1f);
  }


    /**
     * Getter to calculate view projection matrix. 
     * @return View projection matrix (Matrix4f).
     */
  public Matrix4f getVPMatrix() {
    return getProjectionMatrix().mul(getViewMatrix());
  }


    /**
     * Updates data in shaders.
     * Always call this method at the end of frame.
     */
  public void update() {
    config.getShaderProgram().use();
    VPMatrix = getVPMatrix();
    VPMatrix.get(uniformData);
    glUniformMatrix4fv(config.getUniform().location(), false, uniformData);
  }


    /**
     * Method to increase zoom value.
     * @param value Multiplicates zoom by this value.
     */
  public void addZoom(float value) {
    zoom *= value;
  }


    /**
     * Method to reduce zoom value.
     * @param value Divides zoom by this value.
     */
  public void subZoom(float value) {
    zoom /= -value;
  }


    /**
     * Getter for camera field of view (FOV).
     * @return Vector2f with camera field of view in height and width.
     */
  public Vector2f getFOV() {
    return FOV.set(w, h);
  }


  public Vector2f pixelsToWorld(float screenX, float screenY) {
    wX = screenX - screenWidth / 2f;
    wY = screenHeight / 2f - screenY;
    cache.set(wX, wY);
    return cache;
  }
}
