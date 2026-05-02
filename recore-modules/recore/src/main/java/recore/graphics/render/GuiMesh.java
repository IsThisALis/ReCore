package recore.graphics.render;

import recore.core.Renderable;

import recore.graphics.shaders.ShaderProgram;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class GuiMesh implements Renderable {

  ShaderProgram shaderProgram;

  VertexArrayObject VAO;
  VertexBufferObject VBO;
  ElementBufferObject EBO;

  float[] vertices;
  int[] indices;

  int location;
  int indicesNumber;

      /**
       * Basic constructor for creating mesh
       * Renders with colors 
       * @param vao VertexArrayObject to attach 
       * @param vbo VertexBufferObject to attach 
       * @param ebo ElementBufferObject to attach 
       * @param shaderProgramUnit ShaderProgram to attach 
       */
    public GuiMesh(VertexArrayObject vao, VertexBufferObject vbo, ElementBufferObject ebo, ShaderProgram shaderProgramUnit) {
          // Attaches buffers
      VBO = vbo;
      VAO = vao;
      EBO = ebo;
          // Attaches ShaderProgram
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
          // Binds VAO
      VAO.bind();
          // Draws object from triangles and indicesNumber (pointer)
      glDrawElements(GL_TRIANGLES, indicesNumber, GL_UNSIGNED_INT, 0L); 
    } 
}
