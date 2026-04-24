package recore.graphics.render;

// OpenGL imports
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayObject {
        // Buffer identifier
    private final int id;
    
    /**
     * Generates new buffer
     */
    public VertexArrayObject() {
        id = glGenVertexArrays();
    }
    
    /**
     * Binds buffer
     */
    public void bind() {
        glBindVertexArray(id);
    }
    
    /**
     * Unbinds buffer
     */
    public void unbind() {
        glBindVertexArray(0);
    }
    
    /**
     * Deletes buffer
     */
    public void delete() {
        glDeleteVertexArrays(id);
    }
    
    /**
     * Returns buffer identifier
     */
     public int getID() {
        return id;
    }   
}
