package rexample.interaction;

import recore.graphics.window.Params;
import recore.util.Input;


public class InputActions {

  private Input input;

  public InputActions() {
    input = new Input();
  }

  public void update() {
    if (input.keyPressed(input.getKey("ESCAPE"))) {
     Params.getWindowInst().close(); 
    }
  }
}
