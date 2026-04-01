package recore.example.gmobj;

import recore.example.resources.Resources;

import recore.graphics.camera.Camera;
import recore.util.Input;
import recore.util.Time;

public class Player {

  Camera camera;
  Time time = new Time();
  Input input = new Input();

  float x, y;

  public void init() {
    input.keyMapCreate();
    input.scrollCallback();
    camera = new Camera(0.0f, 0.0f, 0.0f, Resources.getShaderProgramMap().getObj(0));
  }

  public void draw() {
    time.tick();
    input();
    camera.update();
  }

  public void input() {

    if(input.keyPressed(input.getKey("W"))) {
      y+=0.25f;
    }

    if(input.keyPressed(input.getKey("S"))) {
      y-=0.25f;
    }

    if(input.keyPressed(input.getKey("A"))) {
      x-=0.25f;
    }

    if(input.keyPressed(input.getKey("D"))) {
      x+=0.25f;
    }

    if(input.getScrollActionY() == 1) {
      camera.addZoom(0.05f);
    }

    if(input.getScrollActionY() == -1) {
      if(camera.getZoom() > 0.1f) {
        camera.subZoom(0.05f);
      }
    } 

    camera.move(x, y, 2.5f, time.getDelta());

    x=0.0f;
    y=0.0f;
    input.resetScrollActionY();
  }
  
}
