package recore.graphics.camera;

  // ReCore imports
  // Shaders 
import recore.graphics.shaders.ShaderProgram;

  // Window parameters
import recore.graphics.window.Params;

  // JOML imports
import org.joml.Vector3f;
import org.joml.Matrix4f;

  // OpenGL imports
import static org.lwjgl.opengl.GL20.*;

public class Camera {

    // Vector with camera coordinates
  Vector3f position;

    // Screen size parameters (coming from window parameters)
  float screenWidth, screenHeight;
    // Camera default zoom value
  float zoom = 1.0f;

    // Camera uniform location in shader 
  int location;

    // ShaderProgram used by camera
  ShaderProgram attachedProgram;


    /**
     * Constructor to initialize camera 
     * Requires OpenGL context 
     * @param x Camera start x 
     * @param y Camera start y 
     * @param program ShaderProgram attaching to camera (Need shaders with camera support)
     */
  public Camera(float x, float y, ShaderProgram program) {
    position = new Vector3f(x, y, 0.0f);
    screenWidth = (float) Params.getParams().getWidth();
    screenHeight = (float) Params.getParams().getHeight();
    attachedProgram = program;
    location = glGetUniformLocation(attachedProgram.getID(), "uVPMatrix");
  }
  

    /**
     * Moving camera by x and y 
     * Uses speed and time delta between frames to get smooth moving 
     * @param x Value adding to x 
     * @param y Value adding to y 
     * @param speed Camera speed value 
     * @param timeDelta Time delta between past and now frames 
     */
  public void move(float x, float y, float speed, float timeDelta) { 
    position.y += y*speed*timeDelta;
    position.x += x*speed*timeDelta;

  }


    /**
     * Setter for camera position 
     * @param x Camera x coordinate
     * @param y Camera y coordinate 
     */
  public void setPosition(float x, float y) {
    position.y = y;
    position.x = x;
  }


    /**
     * Getter for camera position 
     * @return Position (Vector3f)
     */
  public Vector3f getPosition() {
    return position;
  }


    /**
     * Getter for view matrix 
     * @return View matrix (Matrix4f)
     */
  public Matrix4f getViewMatrix() {
    return new Matrix4f().translate(-position.x, -position.y, -position.z);
  }


     /**
      * Getter to calculate prjection matrix 
      * @return Projection matrix (Matrix4f)
      */
  public Matrix4f getProjectionMatrix() {
    float w = screenWidth / 2f / zoom;
    float h = screenHeight / 2f / zoom;
    return new Matrix4f().ortho2D(-w, w, -h, h);
  }


    /**
     * Getter to calculate view projection matrix 
     * @return View projection matrix (Matrix4f)
     */
  public Matrix4f getVPMatrix() {
    return getProjectionMatrix().mul(getViewMatrix());
  }


    /**
     * Updates data in shaders 
     */
  public void update() {
    attachedProgram.use();
    Matrix4f vp = getVPMatrix();
    float data[] = new float[16];
    vp.get(data);
    glUniformMatrix4fv(location, false, data);
  }


    /**
     * Method to increase zoom value 
     * @param value Multiplicates zoom by this value 
     */
  public void addZoom(float value) {
    zoom *= value;
  }


    /**
     * Method to reduce zoom value
     * @param value Divides zoom by this value 
     */
  public void subZoom(float value) {
    zoom /= -value;
  }


    /**
     * Getter for camera zoom value
     * @return Zoom value
     */
  public float getZoom() {
    return zoom;
  }
}
