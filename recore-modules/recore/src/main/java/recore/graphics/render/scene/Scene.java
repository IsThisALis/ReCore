package recore.graphics.render.scene;

  // ReCore imports
import recore.graphics.render.Mesh;

  // Java imports 
import java.util.ArrayList;

public class Scene {

    // Scene object 
  private ArrayList<Mesh> scene;
    // Objects in scene
  private int objCount;


      /**
       * Setter for objects count 
       * @param value Objects number
       */
    public void setObjCount(int value) {
        objCount = value;
    }


      /**
       * Creates scene 
       */ 
    public void create() {
        scene = new ArrayList<Mesh>(objCount);
    }


      /**
       * Adds Mesh to scene
       * @param index Object index in scene 
       * @param object Object to add in scene 
       */
    public void addObj(int index, Mesh object) {
        scene.add(index, object);
    }


      /**
       * Removes object from scene
       * @param index Object index 
       */
    public void rmObj(int index) {
        scene.remove(index);
    }


      /**
       * Getter for object 
       * @param index Object index
       * @return Mesh object from scene 
       */
    public Mesh getObj(int index) {
        return scene.get(index);
    }


      /**
       * Draws all objects from scene 
       */
    public void draw() {
      for(int x=0; x!=scene.size(); x++) {
          getObj(x).draw();
      }
    }
}
