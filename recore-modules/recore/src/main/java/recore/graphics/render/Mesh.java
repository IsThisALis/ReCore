package recore.graphics.render;

    // Core imports
import recore.core.Renderable;

    // Graphic imports
import recore.graphics.shaders.ShaderProgram;
import recore.graphics.textures.Texture;

    // OpenGL imports
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

    // JOML imports
import org.joml.Vector3f;
import org.joml.Matrix4f;

public class Mesh implements Renderable {

      // Declares buffers for data
  VertexBufferObject VBO;
  VertexArrayObject VAO;
  ElementBufferObject EBO;

      // Instances for objects used in rendering
  Texture texture;
  ShaderProgram shaderProgram;
  
      // Int values
  int indicesNumber;
  int location;

      // Array values
  int[] indices;
  float[] vertices;

      // Vector3f values
  Vector3f position = new Vector3f(0, 0, 0);
  Vector3f rotation = new Vector3f(0, 0, 0);
  Vector3f scale    = new Vector3f(1, 1, 1);

      /**
       * Basic constructor for creating mesh
       * Renders with colors 
       * @param vao VertexArrayObject to attach 
       * @param vbo VertexBufferObject to attach 
       * @param ebo ElementBufferObject to attach 
       * @param shaderProgramUnit ShaderProgram to attach 
       */
    public Mesh(VertexArrayObject vao, VertexBufferObject vbo, ElementBufferObject ebo, ShaderProgram shaderProgramUnit) {
          // Attaches buffers
      VBO = vbo;
      VAO = vao;
      EBO = ebo;
          // Attaches ShaderProgram
      shaderProgram = shaderProgramUnit;
      System.out.println("ReCore: New mesh initialized");
    }
      /**
       * Advanced constructor for creating mesh
       * Renders with texture
       * @param vao VertexArrayObject to attach 
       * @param vbo VertexBufferObject to attach 
       * @param ebo ElementBufferObject to attach 
       * @param textureUnit Texture to attach 
       * @param shaderProgramUnit ShaderProgram to attach 
       */
    public Mesh(VertexArrayObject vao, VertexBufferObject vbo, ElementBufferObject ebo, Texture textureUnit, ShaderProgram shaderProgramUnit) {
          // Attaches buffers
      VBO = vbo;
      VAO = vao;
      EBO = ebo;
          // Attaches texture and ShaderProgram
      texture = textureUnit;
      shaderProgram = shaderProgramUnit;
      System.out.println("ReCore: New mesh initialized");
    } 

    /**
     * Imitializes mesh
     * @param vertices coordinates and color/UV of object
     * @param indices indices of object
     * @param useTexture use texture or not, if true requires to load texture and use UV in vertices instead of rgb
     */
  @Override 
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
      location = glGetUniformLocation(shaderProgram.getID(), "uModelMatrix"); 
    }

    /**
     * Updates data in buffers, can be used for controlling object 
     *  @param vertices new position and uv mapping attributes
     *  @param indices new indices data 
     *
     */
  @Override
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
     * Deletes buffers and their data
     */
  @Override
    public void cleanup() {
          // Deletes VBO and its data
      VBO.delete();
          // Deletes EBO and its data
      EBO.delete();
    }

    /**
     * Draws object with ShaderProgram and its texture if used
     */
  @Override
    public void draw() {
          // Binds texture to render if used
      if(texture !=null) {
         texture.bind();
      }
          // Binds VAO
      VAO.bind();

          // Building model matrix for camera
      float[] data = new float[16];
      buildModelMatrix().get(data);
          // Move model matrix to uniform
      glUniformMatrix4fv(location, false, data);
          // Draws object from triangles and indicesNumber (pointer)
      glDrawElements(GL_TRIANGLES, indicesNumber, GL_UNSIGNED_INT, 0L); 
    }

  public Matrix4f buildModelMatrix() {
    return new Matrix4f().translate(position).rotateZ(rotation.z).scale(scale);
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
