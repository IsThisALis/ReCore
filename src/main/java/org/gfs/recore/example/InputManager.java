package org.gfs.recore.example;

import org.gfs.recore.example.graphic.Render;

import org.gfs.recore.graphics.camera.Camera;

import org.gfs.recore.util.KeyboardInput;
import org.gfs.recore.util.Time;

public class InputManager {

  Camera camera;

  KeyboardInput keyboardInput = new KeyboardInput();

  public Time time = new Time();

  float x, y;

  public void init() {
    camera = Render.getCamera();

    keyboardInput.keyMapCreate();
    keyboardInput.scrollCallback();
  }

  public void input() {

    if(keyboardInput.keyPressed(keyboardInput.getKey("A"))) {
      x-=0.1f;
    }

    if(keyboardInput.keyPressed(keyboardInput.getKey("D"))) {
      x+=0.1f;
    }

    if(keyboardInput.keyPressed(keyboardInput.getKey("W"))) {
      y+=0.1f;
    }

    if(keyboardInput.keyPressed(keyboardInput.getKey("S"))) {
      y-=0.1f;
    } 

    if(keyboardInput.getScrollActionY() > 0) {
      camera.addZoom(0.05f);
    }

    if(keyboardInput.getScrollActionY() < 0) {
      if(camera.getZoom() > 0.1f) {
        camera.subZoom(0.05f);
      }
    }

    keyboardInput.resetScrollActionY();

    camera.move(x, y, 5f, time.getDelta());
    x=0.0f;
    y=0.0f;
  }
}
