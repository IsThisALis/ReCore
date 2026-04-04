package recore.example.gmobj;

import recore.example.resources.Resources;

import recore.graphics.camera.Camera;
import recore.graphics.render.Mesh;
import recore.util.Input;
import recore.util.Time;

public class Player {

  Camera camera;
  Mesh mesh;

  Time time = new Time();
  Input input = new Input();

  float speed = 2.5f;
  boolean jumping;

    float[] vertices = {
    // x     y     z    u     v
-0.25f, 0.25f, 0.0f, 0.0f, 1.0f,
0.25f, 0.25f, 0.0f, 1.0f, 1.0f,
0.25f,-0.25f, 0.0f, 1.0f, 0.0f,
-0.25f,-0.25f, 0.0f, 0.0f, 0.0f
  };

  int[] indices = {
     0, 1, 2,
     2, 3, 0
  };

  public void init() {
    input.keyMapCreate();
    input.scrollCallback();

    mesh = Resources.getScene().getObj(3);
    mesh.init(vertices, indices, 6, true);
    mesh.setPosition(0.0f, 0.0f);
    mesh.setScale(0.75f, 0.75f, 0.75f);

    camera = new Camera(0.0f, 0.0f, 0.0f, Resources.getShaderProgramMap().getObj(0));
    camera.addZoom(3.0f);
  }

  public void draw() {
    time.tick();
    input();

    camera.update();
    mesh.draw(); 
  }

  public void input() {
    float x = 0.0f;
    float y = 0.0f;

    if(input.keyPressed(input.getKey("W"))) {
      y=0.25f;
    }

    if(input.keyPressed(input.getKey("S"))) {
      y=-0.25f;
    }

    if(input.keyPressed(input.getKey("A"))) {
      x=-0.25f;
    }

    if(input.keyPressed(input.getKey("D"))) {
      x=0.25f;
    }

    if(input.keyPressed(input.getKey("E"))) {
      mesh.setScale(1.25f, 1.25f, 1.25f);
    }

    /*if(input.keyReleased(input.getKey("E"))) {
      mesh.setScale(0.75f, 0.75f, 0.75f);
    }*/

    if(input.keyPressed(input.getKey("C"))) {
      mesh.setScale(0.25f, 0.25f, 0.25f);
    } 

    if(input.keyReleased(input.getKey("C")) && input.keyReleased(input.getKey("E"))) {
      mesh.setScale(0.75f, 0.75f, 0.75f);
    }

    if(input.getScrollActionY() == 1) {
      camera.addZoom(1.1f);
    }

    if(input.getScrollActionY() == -1) {
      if(camera.getZoom() >= 1.1f) {
        camera.subZoom(-1.1f);
      }
    }

    //mesh.setPosition(camera.getPosition().x, camera.getPosition().y);
    camera.move(x, y, speed, time.getDelta()); 
    mesh.setPosition(camera.getPosition().x, camera.getPosition().y);

    input.resetScrollActionY();
  }
}
