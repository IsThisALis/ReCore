package org.gfs.recore.graphics.render;

import static org.lwjgl.opengl.GL15.*;


public class ElementBufferObject {
    private final int id;
    
    public ElementBufferObject() {
        id = glGenBuffers();
    }
    
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }
    
    public void uploadData(int[] indices) {
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
    }

    public void delete() {
      glDeleteBuffers(id);
    }
    
    public int getID() {
        return id;
    }
}
