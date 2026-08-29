package com.isthisalis.recore.core.util.buffers;

  // OpenGL imports
import static org.lwjgl.opengl.GL15.*;


public class ElementBufferObject extends Buffer {
    

    /**
     * Binds buffer
     */
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, getId());
    }


    /**
     * Unbinds buffer
     */
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    

    /**
     * Uploads object data to buffer
     * @param indices Indices to load in buffer 
     */
    public void uploadData(int[] indices) {
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_DYNAMIC_DRAW);
    }
}