package com.isthisalis.recore.core.util.buffers;

import static org.lwjgl.opengl.GL15.*;

/**
 * OpenGL element buffer object. Implements Buffer.
 * @see {@link com.isthisalis.recore.core.util.buffers.Buffer}
 */
public class ElementBufferObject extends Buffer {
    
    /**
     * Binds buffer, preparing to work.
     */
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, getId());
    }


    /**
     * Unbinds buffer.
     */
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    

    /**
     * Uploads data to buffer.
     * @param indices Indices to load in buffer.
     */
    public void uploadData(int[] indices) {
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_DYNAMIC_DRAW);
    }
}