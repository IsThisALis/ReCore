package recore.graphics.shaders;

  // OpenGL imports
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

  // Java util imports
import java.util.concurrent.ConcurrentHashMap;

import recore.util.NIO;

public class ShaderProgram {

      // Program identifier given by OpenGL
    private int id;

      // Stores shader we are working with
    private Shader attachedShader;

      // Stores shaders
    private final ConcurrentHashMap<String, Shader> shaders = new ConcurrentHashMap<>();
    
      // Cache path 
    private String cachePath;

    /**
     * Creates new OpenGL shader program
     */
    public ShaderProgram() {
        id = glCreateProgram();
    }

    
      /**
       * Attaches shader to the program and context (attachedShader)
       * @param shader Shader to be attached
       */
    public void attachShader(Shader shader) {
        glAttachShader(id, shader.getId());
        attachedShader = shader;
    }
    

    /**
     * Links ShaderProgram and checks link status
     */
    public void link() {
        glLinkProgram(id);
        if(checkStatus()) {
          System.out.println("ReCore: Linked shader program");
        } 
        if(!checkStatus()) {
          System.out.println("ReCore: Not linked shader program");
        }
    }


    /**
     * Call OpenGL to use shader program 
     */
    public void use() {
        glUseProgram(id);
    }

    
      /**
       * Deletes shader program 
       */
    public void deleteShaderProgram() {
        glDeleteProgram(id);
    }


      /**
       * Deletes attached shader
       */
    public void deleteShader() {
        if (attachedShader != null) {
            glDeleteShader(attachedShader.getId());
        }
    }
    

     /**
      * Getter for program identifier
      * @return Shader program id
      */
    public int getId() {
        return id;
    }

    
      /**
       * Checks program link status
       * @return Link status 
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
       * @param key keyword to store shader under
       * @param shader shader to store
       */
    public void putShader(String key, Shader shader) {
      shaders.put(key, shader);
    }


    /**
     * Returns shader from storage.
     * @param key keyword to get shader.
     * @return Shader class instance.
     */
    public Shader getShader(String key) {
      return shaders.get(key);
    }


      /**
       * Sets path to cachefile.
       * Format: folder/to/file/filename.
       * @param value New shader cachefile path.
       */
    public void setCachePath(String value) {
      cachePath = value + ".bin";
    }


      /**
       * Getter for cachefile path.
       * @return Path to cachefile as String.
       */
    public String getCachePath() {
      return cachePath;
    }


       /**
        * Getter for cachefile status.
        * False - not exists.
        * True - saved on path.
        */
    public boolean hasCache() {
      return NIO.isFile(NIO.makePath(cachePath));
    }


      /**
       * Caches shader program in cachefile with path.
       */
    public void cache() {
      ShaderCache.writeShaderCache(this); 
    }


      /**
       * Loads cache from cachefile.
       */
    public void loadCache() {
      if (hasCache()) ShaderCache.loadCache(this);
    }
}
