package org.gfs.recore.graphics.render;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.*;

public class VertexBufferObject {
    private final int id;
    
    public VertexBufferObject() {
        id = glGenBuffers();
    }
    
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }
    
    public void uploadData(float[] vertices) {
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }
    
    public void delete() {
        glDeleteBuffers(id);
    }
    
    public int getID() {
        return id;
    }
}

