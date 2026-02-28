package org.gfs.recore.graphics.shaders;

//OpenGL imports
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

//Java util imports
import java.util.concurrent.ConcurrentHashMap;

public class ShaderProgram {
    
    private int id;
    
    private Shader attachedShader;

    private final ConcurrentHashMap<String, Shader> shaderCache = new ConcurrentHashMap<>();
    
    public ShaderProgram() {
        id = glCreateProgram();
    }
    
    public void attachShader(Shader shader) {
        glAttachShader(id, shader.getID());
        attachedShader = shader; 
    }
    
    public void link() {

        glLinkProgram(id);
        if(!checkStatus()) {
        System.out.println("Not linked");
        }
        System.out.println("Linked");
        
    }
    
    public void use() {
        glUseProgram(id);
    }
    
    public void deleteShaderProgram() {
        deleteShader();
        glDeleteProgram(id);
    }
    
    public void deleteShader() {
        if (attachedShader != null) {
            glDeleteShader(attachedShader.getID());
        }
    }
    
    public int getID() {
            return id;
    }
    
    public boolean checkStatus() {
        int status = glGetProgrami(id, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            return false;
        }
        return true;
    }
      //Uploads shader
    public void putShader(String key, Shader shader) {
      shaderCache.put(key, shader);
    }

    public Shader getShader(String key) {
      return shaderCache.get(key);
    }
}
