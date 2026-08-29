package com.isthisalis.recore.core.util.buffers;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import lombok.Getter;

public abstract class Buffer {
    
    private final @Getter int id;

    Buffer() {
        id = glGenBuffers();
    }

    public void delete() {
        glDeleteBuffers(id);
    }

    abstract void bind();
    abstract void unbind();

    public void delete(int... buffers) {
        glDeleteBuffers(buffers);
    }
}