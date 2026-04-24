package recore.graphics.render.scene;

import recore.graphics.render.Mesh;
import java.util.ArrayList;

public class Scene {

  private ArrayList<Mesh> scene;
  private int objCount;

    public void setObjCount(int value) {
        objCount = value;
    }
    public void create() {
        scene = new ArrayList<Mesh>(objCount);
    }

    public void addObj(int index, Mesh object) {
        scene.add(index, object);
    }

    public void rmObj(int index) {
        scene.remove(index);
    }

    public Mesh getObj(int index) {
        return scene.get(index);
    }

    public void draw() {
      for(int x=0; x!=scene.size(); x++) {
          getObj(x).draw();
      }
    }
}
