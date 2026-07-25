package com.isthisalis.rexample.gmobj;

import com.isthisalis.rexample.gui.GUIManager;
import com.isthisalis.rexample.resources.Resources;

import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.window.Params;
import com.isthisalis.recore.graphics.window.Window;

public class ObjectManager {

  Floor floor = new Floor();
  Banana banana = new Banana();
  GrassBlock grassBlock = new GrassBlock();
  Player player = new Player();

  Window window = Params.getWindowInst();
  ShaderProgram program;

  public void init() {
    floor.create();
    banana.create();
    grassBlock.create();
    player.init();

    GUIManager.init();

    program = Resources.getShaderProgramMap().getObj("ct1");
  }

  public void draw() {
    window.cleanWindow();
    program.use();
 
    floor.draw();
    banana.draw();

    grassBlock.draw();
    player.draw();

    GUIManager.update();
    GUIManager.render();
  }
}
