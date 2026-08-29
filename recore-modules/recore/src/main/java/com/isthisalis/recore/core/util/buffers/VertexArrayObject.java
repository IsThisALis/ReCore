package com.isthisalis.recore.core.util.buffers;

import static org.lwjgl.opengl.GL30.*;

import lombok.Getter;

/**
 * OpenGl vertex array object. 
 */
public class VertexArrayObject {

    /**
     * OpenGL array ID.
     */
    private final @Getter int id;
    

    /**
     * Generates new array.
     */
    public VertexArrayObject() {
        id = glGenVertexArrays();
    }
    

    /**
     * Binds array, preparing to work.
     */
    public void bind() {
        glBindVertexArray(id);
    }
    

    /**
     * Unbinds array.
     */
    public void unbind() {
        glBindVertexArray(0);
    }
    

    /**
     * Deletes array.
     */
    public void delete() {
        glDeleteVertexArrays(id);
    }
}
