package com.isthisalis.recore.graphics.shaders;

    // OpenGL imports
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

      // Shader identifier
    private int id;
      // String representing shader type 
    private String shaderType;
    
    
      /**
       * Custom. Uploads shader code to OpenGL
       * @param source Shader code
       */
    public void uploadSource(CharSequence source) {
        glShaderSource(id, source);
    }


      /**
       * Custom. Use only if you made custom method to create shader 
       * @param id Shader to compile 
       */
    public void compile(int id) {
        glCompileShader(id);
        if(!checkStatus()) {
          throw new RuntimeException("ReCore: Unable to compile shader with id: "+id);
        }
        System.out.println("ReCore: Compiled shader! ID: "+id);
    }

    
      /**
       * Checks shader compile status 
       * @return Shader compile state 
       */
    public boolean checkStatus() {
    int status = glGetShaderi(id, GL_COMPILE_STATUS);
    if (status != GL_TRUE && id == 0) {
        return false;
      }
      return true;
    }


      /**
       * Transforms string to OpenGL shader type 
       * @param type Text shader type (vertex/fragment)
       * @return OpenGL shader type (Int)
       */
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
    public void deleteShader() {
        glDeleteShader(id);
    }


      /**
       * Getter for shader identifier 
       * @return Shader id
       */
    public int getId() {
        return id;
    }


      /**
      * Creates shader from source and type 
      * @param source code of your shader
      */
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
