package rexample.gmobj;

import rexample.resources.Resources;
import recore.graphics.shaders.ShaderProgram;
import recore.graphics.window.Params;
import recore.graphics.window.Window;

public class ObjectManager {

  Floor floor = new Floor();
  Banana banana = new Banana();
  GrassBlock grassBlock = new GrassBlock();
  GrassBlock grassBlock1 = new GrassBlock();
  GrassBlock grassBlock2 = new GrassBlock();
  GrassBlock grassBlock3 = new GrassBlock();
  Player player = new Player();

  Window window = Params.getWindowInst();
  ShaderProgram program;

  public void init() {
    floor.create();
    banana.create();
    banana.setPosition(0.5f, 0.5f);
    grassBlock.create();
    grassBlock1.create();
    grassBlock2.create();
    grassBlock3.create();
    player.init();

    program = Resources.getShaderProgramMap().getObj(0);
  }

  public void draw() {
    window.cleanWindow();
    program.use();
 
    floor.draw();
    banana.draw();

    grassBlock.setPosition(0f, -1.15f);
    grassBlock.draw();
    grassBlock1.setPosition(0.5f, -1.15f);
    grassBlock1.draw();
    grassBlock2.setPosition(1f, -1.15f);
    grassBlock2.draw();
    grassBlock3.setPosition(1.5f, -1.15f);
    grassBlock3.draw(); 
    player.draw();
  }
}
