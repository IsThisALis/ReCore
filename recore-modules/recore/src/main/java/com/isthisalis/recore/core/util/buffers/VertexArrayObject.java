package com.isthisalis.recore.core.util.buffers;

  // OpenGL imports
import static org.lwjgl.opengl.GL30.*;

import lombok.Getter;

public class VertexArrayObject {
        // Buffer identifier
    private final @Getter int id;
    

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
}
