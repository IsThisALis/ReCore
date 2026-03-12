package org.gfs.recore.graphics.shaders;

  // OpenGL imports
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

  // Java util imports
import java.util.concurrent.ConcurrentHashMap;

public class ShaderProgram {
      // Program identifier
    private int id;
      // Stores shader we are working with
    private Shader attachedShader;
      // Stores shaders
    private final ConcurrentHashMap<String, Shader> shaderCache = new ConcurrentHashMap<>();
    
    /**
     * Creates new ShaderProgram
     */
    public ShaderProgram() {
        id = glCreateProgram();
    }
    
    /**
     * Attaches shader to the program and context (attachedShader)
     * @param shader Shader to be attached
     */
    public void attachShader(Shader shader) {
        glAttachShader(id, shader.getID());
        attachedShader = shader; 
    }
    /**
     * Links ShaderProgram and checks link status
     */
    public void link() {
        glLinkProgram(id);
        if(!checkStatus()) {
        System.out.println("Not linked shader program");
        }
        System.out.println("Linked shader program");
        
    }
    /**
     * Uses ShaderProgram 
     */
    public void use() {
        glUseProgram(id);
    }
    
    public void deleteShaderProgram() {
        glDeleteProgram(id);
    }

        /**
         * Deletes attachedShader
         */
    public void deleteShader() {
        if (attachedShader != null) {
            glDeleteShader(attachedShader.getID());
        }
    }
    
    /**
     * Returns program identifier
     */
    public int getID() {
            return id;
    }
    
    /**
     * Checks program link status
     */
    public boolean checkStatus() {
        int status = glGetProgrami(id, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            return false;
        }
        return true;
    }
      /**
       * Puts shader in storage
       */
    public void putShader(String key, Shader shader) {
      shaderCache.put(key, shader);
    }

    /**
     * Returns
     */
    public Shader getShader(String key) {
      return shaderCache.get(key);
    }
}
