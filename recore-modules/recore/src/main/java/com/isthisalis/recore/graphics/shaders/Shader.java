package com.isthisalis.recore.graphics.shaders;

  // OpenGL imports
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

  // Lombok import
import lombok.Getter;
import lombok.ToString;

/**
 * OpenGL shader OOP wrap.
 */
@ToString(onlyExplicitlyIncluded = true)
public class Shader {

    /**
     * OpenGL shader ID.
     */
    private @Getter int id;

    /**
     * String representing shader type.
     * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
    private String shaderType;

    @ToString.Include
    private @Getter String code;

    /**
     * OpenGL shader type.
     */
    private @Getter ShaderTypes type;
   
    /**
     * Empty shader constructor.
     * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
    public Shader() {}
   

    /**
     * Creates new shader from source code and {@link #ShaderTypes} type
     * @param code Shader source code.
     * @param type Shader type.
     * @see {@link com.isthisalis.recore.graphics.shaders.ShaderTypes}
     */
    public Shader(String code, ShaderTypes type) {
      this.code = code;
      id = glCreateShader(type.getCode());
      glShaderSource(id, code);
      glCompileShader(id);
    }


    /**
     * Uploads shader code to OpenGL.
     * @param source Shader code.
     * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
    public void uploadSource(CharSequence source) {
        glShaderSource(id, source);
    }


     /**
     * Custom. Use only if you made custom method to create shader 
     * @param id Shader to compile.
     * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
    public void compile(int id) {
        glCompileShader(id);
        if(!checkStatus()) {
          throw new RuntimeException("ReCore: Unable to compile shader with id: "+id);
        }
        System.out.println("ReCore: Compiled shader! ID: "+id);
    }

    
    /**
     * Checks shader compile status.
     * @return Shader compile state.
     */
    public boolean checkStatus() {
      if (glGetShaderi(id, GL_COMPILE_STATUS) != GL_TRUE && id == 0) {
        return false;
      }
      return true;
    }


    /**
     * Transforms string to OpenGL shader type.
     * @param type Text shader type (vertex/fragment).
     * @return OpenGL shader type (Int).
     * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
    public int getShaderType(String type) {
      shaderType = type;
        // Checks shader type
      if (type.toLowerCase().contains("vertex")) {
          return GL_VERTEX_SHADER;
      }
      if (type.toLowerCase().contains("fragment")) {
          return GL_FRAGMENT_SHADER;
      } else {
            // Throwss exception when type invalid
          throw new IllegalArgumentException("ReCore: Unknown shader type: "+type);
        }
    }


    /**
     * Deletes this shader 
     */
    public void delete() {
        glDeleteShader(id);
    }


    /**
     * Creates shader from source code and type.
     * @param source Shader code.
     * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
    public void createShader(String source) {
        id = glCreateShader(getShaderType(shaderType));
            // Check to be sure shader created
        if(id==0) {
          throw new RuntimeException("ReCore: Unable to create shader with type: "+shaderType); 
        }
            // Uploads shader code and compiles
        glShaderSource(id, source);
        glCompileShader(id);
            // Check to be sure shader compiled
        if(!checkStatus()) {
          throw new RuntimeException("ReCore: Unable to compile shader. Type: "+shaderType+" Check your shader code! Source: "+source);
        }
    }
}
