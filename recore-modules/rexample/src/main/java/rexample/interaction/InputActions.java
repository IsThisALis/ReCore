package rexample.interaction;

import recore.graphics.window.Params;
import recore.util.Input;


public class InputActions {

  public void update() {
    if (Input.keyPressed(Input.getKey("ESCAPE"))) {
     Params.getWindowInst().close(); 
    }
  }
}
