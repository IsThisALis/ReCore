package org.gfs.recore.graphics.render;

// OpenGL imports
import static org.lwjgl.opengl.GL15.*;

public class ElementBufferObject {
      // Buffer identifier
    private final int id;
    
    /**
     * Generates new buffer
     */
    public ElementBufferObject() {
        id = glGenBuffers();
    }
    
    /**
     * Binds buffer
     */
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    /**
     * Unbinds buffer
     */
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    
    /**
     * Uploads object data to buffer
     */
    public void uploadData(int[] indices) {
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
    }

    /**
     * Deletes buffer
     */
    public void delete() {
      glDeleteBuffers(id);
    }
    
    /**
     * Returns buffer identifier
     */
    public int getID() {
        return id;
    }
}
