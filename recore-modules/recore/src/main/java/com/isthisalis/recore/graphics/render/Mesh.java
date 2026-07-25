package com.isthisalis.recore.graphics.render;

    // Graphic imports
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.util.Logging;

// OpenGL imports
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

    // JOML imports
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Matrix4f;

public class Mesh {

      // Logger wrap 
  private static Logging log = new Logging("ReCore");

      // Buffers for data
  private VertexBufferObject VBO;
  private VertexArrayObject VAO;
  private ElementBufferObject EBO;

      // Instances for objects used in rendering
  private ShaderProgram shaderProgram;
  
      // Int values
  private int indicesNumber;
  private int location;

      // Array values
  private int[] indices;
  private float[] vertices;
  private float[] modelData;

      // Vector3f values
  private Vector3f position = new Vector3f(0, 0, 0);
  private Vector3f rotation = new Vector3f(0, 0, 0);
  private Vector3f scale    = new Vector3f(1, 1, 1);

      // Matrix4f values
  private Matrix4f modelMatrix = new Matrix4f();

      /**
       * Advanced constructor for creating mesh
       * Renders with texture
       * @param vao VertexArrayObject to attach 
       * @param vbo VertexBufferObject to attach 
       * @param ebo ElementBufferObject to attach 
       * @param shaderProgram ShaderProgram to attach 
       */
    public Mesh(VertexArrayObject vao, VertexBufferObject vbo, ElementBufferObject ebo, ShaderProgram shaderProgram) {
          // Sets Mesh logging to file 
      log.logToFile();
          // Attaches buffers
      VBO = vbo;
      VAO = vao;
      EBO = ebo;
          // Attaches texture and ShaderProgram
      this.shaderProgram = shaderProgram;
      log.info("New mesh initialized");
    } 


    /**
     * Imitializes mesh
     * @param vertices coordinates and color/UV of object
     * @param indices indices of object
     * @param useTexture use texture or not, if true requires to load texture and use UV in vertices instead of rgb
     */
    public void init(float[] vertices, int[] indices, boolean useTexture) {
          // Initializes number of indices
      this.indicesNumber = indices.length;
      this.indices = indices;
      this.vertices = vertices;

          // Binds VAO to set data parameters
      VAO.bind();

          // Binds VBO
      VBO.bind();
          // Uploads mesh data to VBO
      VBO.uploadData(vertices);
        
      
      if(useTexture) {
              // Position attribute (xyz - 3 floats)
        glDisableVertexAttribArray(1);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Float.BYTES, 0L);
        glEnableVertexAttribArray(0);

              // UV mapping attribute (uv - 2 floats)
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(2); } 
      else {
              // Position attribute (xyz - 3 floats)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * Float.BYTES, 0L);
        glEnableVertexAttribArray(0);

              // Color attribute (rgb - 3 floats)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1); }

          // Binds EBO
      EBO.bind();
          // Uploads render data to EBO
      EBO.uploadData(indices);
          // Unbinds VAO (no use now)
      VAO.unbind();
          // Need to use ShaderProgram when getting uniform location
      shaderProgram.use();
      location = glGetUniformLocation(shaderProgram.getId(), "uModelMatrix"); 
    }


    /**
     * Updates data in buffers, can be used for controlling object 
     *  @param vertices new position and uv mapping attributes
     *  @param indices new indices data 
     */
    public void update(float[] vertices, int[] indices) {
      this.vertices = vertices;
      this.indices = indices;
      this.indicesNumber = indices.length;

          // Binds VBO to update data
      VBO.bind();

          // Updates VBO data
      glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);

          // Binds EBO to update data
      EBO.bind();

          // Updates EBO data
      glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, 0, indices);
    }


    /**
     * Deletes all data in buffers.
     */
    public void cleanup() {
      VBO.delete();
      EBO.delete();
      VAO.delete();
    }


    /**
     * Draws object with ShaderProgram and its texture if used
     */
    public void draw() {
          // Binds VAO
      VAO.bind();

          // Building model matrix for camera
      if (modelData == null) modelData = new float[16];
      buildModelMatrix().get(modelData);
          // Move model matrix to uniform
      glUniformMatrix4fv(location, false, modelData);
          // Draws object from triangles and indicesNumber (pointer)
      glDrawElements(GL_TRIANGLES, indicesNumber, GL_UNSIGNED_INT, 0L); 
    }

  public Matrix4f buildModelMatrix() {
    /*return modelMatrix.identity()
          .translate(position.x, position.y, 0)
          .translate(scale.x * 0.5f, scale.y, 0)
          .scale(scale);*/
    return modelMatrix.identity().translate(this.position).rotateZ(this.rotation.z).scale(this.scale);
  }


      /**
       * Moves mesh to position 
       *
       * @param x New x position
       * @param y New y position
       * @param speed Speed to move object with 
       * @param deltaTime Time between past and now frames 
       */
    public void move(float x, float y, float speed, float deltaTime) {
      position.x += x*speed*deltaTime;
      position.y += y*speed*deltaTime; 
  }


  //   <---   GETTERS --->


  /**
   * Getter for mesh position
   * 
   * @return Mesh position
   */
  public Vector3f getPosition() {
    return position;
  }


  /**
   * Getter for mesh scale 
   * 
   * @return Mesh scale
   */
  public Vector3f getScale() {
    return scale;
  }


  /**
   * Getter for vertices
   *
   * @return Mesh vertices
   */
  public float[] getVertices() {
    return vertices;
  }


  /**
   * Getter for mesh indices 
   *
   * @return Mesh indices 
   */
  public int[] getIndices() {
    return indices;
  }


  //   <---  SETTERS   --->


    /**
     * Setter for object position in world
     * 
     * @param x X coordinate in world
     * @param y Y coordinate in world
     */
  public void setPosition(float x, float y) {
    position.x = x;
    position.y = y;
  }

    /**
     * Setter for object position in world.
     *
     * @param position New position.
     */
  public void setPosition(Vector2f position) {
    this.position.x = position.x;
    this.position.y = position.y;
  }

    /**
     * Setter for object position in world.
     *
     * @param position New position.
     */
  public void setPosition(Vector3f position) {
    this.position.x = position.x;
    this.position.y = position.y;
  }


    /**
     * Setter for object scale 
     * @param x Scale value in x dimension
     * @param y Scale value in y dimension 
     */
  public void setScale(float x, float y) {
    scale.x = x;
    scale.y = y;
  }

    /**
     * Setter for object rotation
     *
     * @param value Rotation value
     */
  public void setRotation(float value) {
    rotation.z = value;
  }
}
