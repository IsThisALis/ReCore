package recore.example.gmobj;

import recore.example.resources.Resources;
import recore.graphics.shaders.ShaderProgram;
import recore.graphics.window.Params;
import recore.graphics.window.Window;

public class ObjectManager {

  Floor floor = new Floor();
  Banana banana = new Banana();
  Disassembler disassembler = new Disassembler();
  Player player = new Player();

  Window window = Params.getWindowInst();
  ShaderProgram program;

  public void init() {
    floor.create();
    banana.create();
    disassembler.create();
    player.init();

    program = Resources.getShaderProgramMap().getObj(0);
  }

  public void draw() {
    window.cleanWindow();
    program.use();

    player.draw();
    floor.draw();
    banana.draw();
    disassembler.draw();
  }
}
