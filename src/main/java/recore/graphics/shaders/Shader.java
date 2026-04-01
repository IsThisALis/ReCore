package recore.graphics.shaders;

    // OpenGL imports
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

      // Shader identifier
    private int id;
      // String representing shader type 
    private int shType;
    
    
      // Custom. Uploads shader code to OpenGL
    public void uploadSource(CharSequence source) {
        glShaderSource(id, source);
    }
      // Custom. Deprecated! Use only if you made custom createShader() 
    public void compile(int id) {
        glCompileShader(id);
        checkStatus(id);
    }
    
      //Checks shader compile status 
    public boolean checkStatus(int id) {
    int status = glGetShaderi(id, GL_COMPILE_STATUS);
    if (status != GL_TRUE && id == 0) {
        return false;
      }
      return true;
    }

      //Deletes shader 
    public void deleteShader(int id) {
        glDeleteShader(this.id);
    }
      //returns shader id 
    public int getID() {
        return id;
    }
      /**
      * Creates shader from source and type 
      * @param type shader type - vertex or fragment
      * @param source code of your shader
      */
    public void createShader(String type, String source) {
            // Creates shader with type
        if (type.contains("vertex")) {
            shType = GL_VERTEX_SHADER;
            id = glCreateShader(shType);
        } else if (type.contains("fragment")) {
            shType = GL_FRAGMENT_SHADER;
            id = glCreateShader(shType);
        } else {
                // Throwss exception when type invalid
            throw new IllegalArgumentException("Unknown shader type: "+type);
        }

            // Check to be sure shader created
        if(id==0) {
          throw new RuntimeException("Unable to create shader with type: "+type); 
        }

            // Uploads shader code and compiles
        glShaderSource(id, source);
        glCompileShader(id);
            // Check to be sure shader compiled
        if(!checkStatus(id)) {
          throw new RuntimeException("Unable to compile shader. Type: "+type+" Check your shader code! Source: "+source);
        }
    }
}
