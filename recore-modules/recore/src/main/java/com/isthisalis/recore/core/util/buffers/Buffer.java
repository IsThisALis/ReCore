package com.isthisalis.recore.core.util.buffers;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import lombok.Getter;

/**
 * Buffer object class. Used in OpenGL buffers implementations.
 */
public abstract class Buffer {
    
    /**
     * OpenGL buffer ID.
     */
    private final @Getter int id;

    /**
     * Generates buffer and storing buffer id in id.
     */
    Buffer() {
        id = glGenBuffers();
    }

    /**
     * Deletes buffer. Should be called when buffer is no longer needed.
     */
    public void delete() {
        glDeleteBuffers(id);
    }

    /**
     * Binds buffer, preparing to work.
     */
    abstract void bind();

    /**
     * Unbinds buffer.
     */
    abstract void unbind();

    /**
     * Deletes multiple buffers.
     * @param buffers Buffers IDs to delete.
     */
    public void delete(int... buffers) {
        glDeleteBuffers(buffers);
    }
}