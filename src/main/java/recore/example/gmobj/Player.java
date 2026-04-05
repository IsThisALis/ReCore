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

  float speed = 5f;
  boolean jumping;
  float velocity = 5f;

    float[] vertices = {
    // x     y     z    u   v
    -0.25f, 0.25f, 0f, 0f, 1f,
    0.25f, 0.25f, 0f, 1f, 1f,
    0.25f,-0.25f, 0f, 1f, 0f,
    -0.25f,-0.25f, 0f, 0f, 0f
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
    float x = 0f;

    if(input.keyPressed(input.getKey("A"))) {
      x=-0.25f;
    }

    if(input.keyPressed(input.getKey("D"))) {
      x=0.25f;
    }

    if(input.keyPressed(input.getKey("E"))) {
      mesh.setScale(1.25f, 1.25f, 1.25f);
      speed = 2.5f;
      //velocity = 2.5f;
    }

    if(input.keyPressed(input.getKey("C"))) {
      mesh.setScale(0.25f, 0.25f, 0.25f);
      speed = 7.25f;
      //velocity = 7.5f;
    } 

    if(input.keyReleased(input.getKey("C")) && input.keyReleased(input.getKey("E"))) {
      mesh.setScale(0.75f, 0.75f, 0.75f);
      speed = 5f;
    }

    if(input.keyPressed(input.getKey("SPACE"))) {
      jumping = true;
    }

    if(input.keyReleased(input.getKey("SPACE")) && jumping == false) {
      velocity = 5f;
    }

    if(input.getScrollActionY() == 1) {
      camera.addZoom(1.1f);
    }

    if(input.getScrollActionY() == -1) {
      if(camera.getZoom() >= 1.1f) {
        camera.subZoom(-1.1f);
      }
    }

    if(jumping==true) {  

      velocity -= 9.8f * time.getDelta();
      float newY = mesh.getPosition().y + velocity * time.getDelta();

      if(newY <= 0f) {
        newY = 0f;
        jumping = false;
      }

      camera.setPosition(camera.getPosition().x, newY);
    }

    camera.move(x, 0f, speed, time.getDelta()); 
    mesh.setPosition(camera.getPosition().x, camera.getPosition().y);

    input.resetScrollActionY();
  }
}
