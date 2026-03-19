package org.gfs.recore.graphics.render;

    // Core & Util imports
import org.gfs.recore.core.Renderable;

    // Graphic imports
import org.gfs.recore.graphics.shaders.ShaderProgram;
import org.gfs.recore.graphics.textures.Texture;

// OpenGL imports
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh implements Renderable {

      // Declares buffers for data
  VertexBufferObject VBO;
  VertexArrayObject VAO;
  ElementBufferObject EBO;

  Texture texture;

  ShaderProgram shaderProgram;
  
      //Initializes number of ints in int[] indices
  int indicesNumber;

    public Mesh(VertexArrayObject vao, VertexBufferObject vbo, ElementBufferObject ebo) {
          // Initializes buffers
      VBO = vbo;
      VAO = vao;
      EBO = ebo;
    }

   public Mesh(VertexArrayObject vao, VertexBufferObject vbo, ElementBufferObject ebo, Texture textureUnit, ShaderProgram shaderProgramUnit) {
          // Initializes buffers
      VBO = vbo;
      VAO = vao;
      EBO = ebo;
          // Initializes texture and ShaderProgram
      texture = textureUnit;
      shaderProgram = shaderProgramUnit;
    } 

    /**
     * Creates new renderable object 
     * @param vertices coordinates and color/UV of object
     * @param indices indices of object
     * @param indicesNum used to correctly draw objects with custom indices
     * @param useTexture use texture or not, if true requires to load texture and use UV in vertices instead of rgb
     */
  @Override 
    public void init(float[] vertices, int[] indices, int indicesNum, boolean useTexture) {
          // Initializes number of indices
      indicesNumber = indicesNum;

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

      System.out.println("ReCore: New mesh initialized");
    }

    /**
     * Updates data in buffers, can be used for controlling object 
     *  @param vertices new position and uv mapping attributes
     *  @param indices new indices data 
     *
     */
  @Override
    public void update(float[] vertices, int[] indices, int indicesNum) {

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
     * Draws object with ShaderProgram and its texture (if used, if not)
     * @param shaderProgram ShaderProgram with linked shaders
     * @param texture Texture for object, can be used on multiple objects
     */
  @Override
    public void draw() {
          // Binds texture to render
      if(texture !=null) {
         texture.bind();
      }
          // Binds VAO
      VAO.bind();
          // Uses ShaderProgram with attached shaders
      shaderProgram.use();
          // Draws object from triangles and indicesNumber (pointer)
      glDrawElements(GL_TRIANGLES, indicesNumber, GL_UNSIGNED_INT, 0L); 
    }
}
