package org.gfs.recore.graphics.shaders;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

      //Used to mark shader
    private int id;
    private int shType;
    
    
      //Deprecated! Uploads shader code to OpenGL
    public void uploadSource(CharSequence source) {
        glShaderSource(id, source);
    }
      //Deprecated! Use only if you made custom createShader() 
    public void compile() {
        glCompileShader(id);
        checkStatus(id);
    }
    
      //Checks shader compile status 
    public boolean checkStatus(int id) {
    int status = glGetShaderi(id, GL_COMPILE_STATUS);
    if (status != GL_TRUE) {
        return false;
      }
      return true;
    }

      //Deletes shader 
    public void deleteShader(int id) {
        glDeleteShader(this.id);
    }
      //returns shader's id 
    public int getID() {
        return id;
    }
      /**
      * Creates shader from source and type 
      * @param type shader type - vertex or fragment
      * @param source code of your shader
      * Reason to mark uploadSource() and compile() deprecated
      */
    public void createShader(String type, String source) {

        if (type.contains("vertex")) {
            shType = GL_VERTEX_SHADER;
            id = glCreateShader(shType);
        } else if (type.contains("fragment")) {
            shType = GL_FRAGMENT_SHADER;
            id = glCreateShader(shType);
        } else {
            throw new IllegalArgumentException("Unknown shader type: "+type);
        }


        if(id==0) {
          throw new RuntimeException("Unable to create shader with type: "+type); 
        }


        glShaderSource(id, source);
        glCompileShader(id);

        if(!checkStatus(id)) {
          throw new RuntimeException("Unable to compile shader. Type: "+type+"Check your shader code! Source: "+source);
        }
    }
}
