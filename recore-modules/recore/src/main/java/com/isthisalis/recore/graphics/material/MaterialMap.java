package com.isthisalis.recore.graphics.material;

  // Java imports
import java.util.HashMap;

public class MaterialMap {

    // HashMap with Materials
  private HashMap<String, Material> materials;

  public MaterialMap() {
      materials = new HashMap<>();
  }

      /**
       * Adds object to HashMap
       * @param key Store object with acces from this key 
       */
    public void addObj(String key, Material object) {
        materials.put(key, object);
    }


      /**
       * Removes object from MaterialMap
       * @param key Remove texture by key
       */
    public void rmObj(String key) {
        materials.remove(key);
    }


      /**
       * Getter for MaterialMap objects 
       * @param key Get object by key
       * @return Material from ShaderProgramMap
       */
    public Material getObj(String key) {
        return materials.get(key);
    }


  }
