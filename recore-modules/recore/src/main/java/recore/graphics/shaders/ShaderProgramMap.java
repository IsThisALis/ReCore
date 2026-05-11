package recore.graphics.shaders;

  // Java imports
import java.util.HashMap;

public class ShaderProgramMap {

    // HashMap with ShaderPrograms
  private HashMap<String, ShaderProgram> shaderPrograms;

  public ShaderProgramMap() {
      shaderPrograms = new HashMap<>();
  }

      /**
       * Adds object to HashMap
       * @param key Store object with acces from this key 
       */
    public void addObj(String key, ShaderProgram object) {
        shaderPrograms.put(key, object);
    }


      /**
       * Removes object from ShaderProgramMap
       * @param key Remove texture by key
       */
    public void rmObj(String key) {
        shaderPrograms.remove(key);
    }


      /**
       * Getter for ShaderProgramMap objects 
       * @param key Get object by key
       * @return ShaderProgram from ShaderProgramMap
       */
    public ShaderProgram getObj(String key) {
        return shaderPrograms.get(key);
    }


  }
