package com.isthisalis.recore.graphics.shaders;

import static org.lwjgl.opengl.GL11.GL_RENDERER;
// OpenGL imports
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.GL_VENDOR;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL20.*;

// Java imports
import java.util.concurrent.ConcurrentHashMap;
import java.nio.file.Files;

import com.isthisalis.recore.util.NIO;

import lombok.Getter;
import lombok.Setter;

public class ShaderProgram {

    /**
     * Program identifier given by OpenGL
     */
    private final @Getter int id;

    /**
     * Stores shader we are working with
     */
    private Shader attachedShader;

    /**
     * Storing shaders.
     */
    private final ConcurrentHashMap<String, Shader> shaders = new ConcurrentHashMap<>();
    
    /**
     * Shader program name. Used in caching system. Should be set before using caching
     */
    private @Getter @Setter String name;
    
    /**
    * Path to external shader program cache.
    */
    private @Getter String cachePath;

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
     * Links ShaderProgram and checks link status.
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
    public void delete() {
      deleteShader();
      shaders.clear();
      glDeleteProgram(id);
    }


      /**
       * Deletes attached shader
       */
    public void deleteShader() {
        if (attachedShader != null) {
            glDeleteShader(attachedShader.getId());
        }
        attachedShader = null;
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
        * Getter for cachefile status.
        * False - not exists.
        * True - saved on path.
        */
    public boolean hasCache() { 
      String[] strs = new String[3];
        strs[0] = glGetString(GL_VENDOR);
        strs[1] = glGetString(GL_RENDERER);
        strs[2] = name;

        cachePath = ".recore/cache/" + ShaderCache.hash(strs).substring(0, 16);
      

      return Files.exists(NIO.makePath(cachePath));
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