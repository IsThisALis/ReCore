package com.isthisalis.recore.core.util.buffers;

import static org.lwjgl.opengl.GL15.*;

/**
 * OpenGL vertex buffer object. Implements Buffer.
 * @see {@link com.isthisalis.recore.core.util.buffers.Buffer}
 */
public class VertexBufferObject extends Buffer {

    
    /**
     * Binds buffer, preparing to work.
     */
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, getId());
    }


    /**
     * Unbinds buffer.
     */
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    
    /**
     * Uploads data to buffer.
     * @param vertices Vertices to load in buffer. 
     */
    public void uploadData(float[] vertices) {
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
    }
    

    /**
     * Deletes buffer.
     */
    public void delete() {
        glDeleteBuffers(getId());
    }
}