package recore.graphics.textures;

import java.util.ArrayList;

public class TextureMap {

  private ArrayList<Texture> textures;
  private int objCount;

    public void setObjCount(int value) {
        objCount = value;
    }
    public void create() {
        textures = new ArrayList<Texture>(objCount);
    }

    public void addObj(int index, Texture object) {
        textures.add(index, object);
    }

    public void rmObj(int index) {
        textures.remove(index);
    }

    public Texture getObj(int index) {
        return textures.get(index);
    }


}
