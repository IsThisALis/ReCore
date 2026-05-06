package recore.graphics.render;

  // OpenGL imports
import static org.lwjgl.opengl.GL15.*;

public class VertexBufferObject {
        // Buffer identifier
    private final int id;
    

    /**
     * Generates new buffer
     */
    public VertexBufferObject() {
        id = glGenBuffers();
    }

    
    /**
     * Binds buffer
     */
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }


    /**
     * Unbinds buffer
     */
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    
    /**
     * Uploads object data to buffer
     * @param vertices Mesh data to upload 
     */
    public void uploadData(float[] vertices) {
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
    }
    

    /**
     * Deletes buffer
     */
    public void delete() {
        glDeleteBuffers(id);
    }
    

    /**
     * Returns buffer identifier
     * @return Buffer id 
     */
    public int getId() {
        return id;
    }
}

