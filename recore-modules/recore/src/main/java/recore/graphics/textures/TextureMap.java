package recore.graphics.textures;

  // Java imports
import java.util.HashMap;

public class TextureMap {

    // Store with Texture objects
  private HashMap<String, Texture> textures = new HashMap<>();;


      /**
       * Adds object to HashMap
       * @param key Store object with acces from this key 
       */
    public void addObj(String key, Texture object) {
        textures.put(key, object);
    }


      /**
       * Removes object from TextureMap
       * @param key Remove texture by key
       */
    public void rmObj(String key) {
        textures.remove(key);
    }


      /**
       * Getter for TextureMap objects 
       * @param key Get object by key
       * @return Texture from TextureMap
       */
    public Texture getObj(String key) {
        return textures.get(key);
    }


}
