package recore.graphics.shaders;

import java.util.ArrayList;

public class ShaderProgramMap {

  private ArrayList<ShaderProgram> shaderPrograms;
  private int objCount;

    public void setObjCount(int value) {
        objCount = value;
    }
    public void create() {
        shaderPrograms = new ArrayList<ShaderProgram>(objCount);
    }

    public void addObj(int index, ShaderProgram object) {
        shaderPrograms.add(index, object);
    }

    public void rmObj(int index) {
        shaderPrograms.remove(index);
    }

    public ShaderProgram getObj(int index) {
        return shaderPrograms.get(index);
    }


}
