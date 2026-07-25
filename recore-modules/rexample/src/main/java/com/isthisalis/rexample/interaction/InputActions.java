package com.isthisalis.rexample.interaction;

import com.isthisalis.recore.graphics.window.Params;
import com.isthisalis.recore.util.Input;


public class InputActions {

  public static void update() {
    if (Input.keyPressed(Input.getKey("ESCAPE"))) {
     close(); 
    }
  }

  public static void close() {
    Params.getWindowInst().close();
  }
}
