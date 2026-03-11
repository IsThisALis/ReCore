package org.gfs.recore.graphics.render;

// OpenGL imports
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.*;

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
     */
    public void uploadData(float[] vertices) {
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
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

