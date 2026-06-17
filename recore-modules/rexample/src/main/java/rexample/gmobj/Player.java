package rexample.gmobj;

import rexample.resources.Resources;
import rexample.interaction.Collizion;
import recore.graphics.camera.Camera;
import recore.graphics.render.Mesh;
import recore.graphics.render.Renderer;

import recore.util.Input;
import recore.util.Time;

import rephysics.shapes.Square;

public class Player {

  private static Camera camera;
  private Mesh mesh;

  private Time time = new Time();

  private float speed = 5f;
  private float velocity = 5f;
  private float newY;

  private boolean jumping;
  private boolean healable;
  
  private int hp = 50;

  public void init() {

    mesh = Resources.getScene().getObj(3);
    mesh.init(Square.getVertices(), Square.getIndices(), true);
    mesh.setPosition(0f, 0f);
    mesh.setScale(0.75f, 0.75f);

    camera = new Camera(0f, 0f, Resources.getShaderProgramMap().getObj("ct1"));
    camera.addZoom(3f);
    camera.addZoom(5f);
  }

  public void draw() {
    time.tick();
    Input();
    interact();
    camera.update();
    Renderer.draw(mesh, camera);
    
  }

  public void Input() {
    float x = 0f;

    if(Input.keyPressed(Input.getKey("A"))) {
      x=-0.25f;
    }

    if(Input.keyPressed(Input.getKey("D"))) {
      x=0.25f;
    }

    if(Input.keyPressed(Input.getKey("E"))) {
      mesh.setScale(1.25f, 1.25f);
      speed = 2.5f;
      if(!jumping) {
        velocity = 2.5f;
      }
    }

    if(Input.keyPressed(Input.getKey("C"))) {
      mesh.setScale(0.25f, 0.25f);
      speed = 7.25f;
      if(!jumping) {
        velocity = 7.5f;
      }
    } 

    if(Input.keyPressed(Input.getKey("SPACE"))) {
      jumping = true;
    }

    if(Input.getScrollY()  == 1.0) {
      camera.addZoom(1.1f);
    }

    if(Input.getScrollY() == -1.0) {
      if(camera.getZoom() >= 1.1f) {
        camera.subZoom(-1.1f);
      }
    }

    if(Input.keyReleased(Input.getKey("SPACE")) && jumping == false) {
      velocity = 5f;
    }


    if(Input.keyReleased(Input.getKey("C")) && Input.keyReleased(Input.getKey("E"))) {
      mesh.setScale(0.75f, 0.75f);
      speed = 5f;
    }

    camera.move(x, 0f, speed, time.getDelta()); 
    mesh.setPosition(camera.getPosition().x, camera.getPosition().y);

    Input.resetScrollY();
  }

  public void interact() {

    if(jumping==true) {  

      velocity -= 9.8f * time.getDelta();
      newY = mesh.getPosition().y + velocity * time.getDelta();

      if(newY <= 0f) {
        newY = 0f;
        jumping = false;
      }

      camera.setPosition(camera.getPosition().x, newY);
    }

    if(hp<100) {
      healable = true;
    }

    if(hp==100) {
      healable = false;
    }

    if(healable && Collizion.getPlayerState()) {
        hp = 100;
    }
  }

  public static Camera getCamera() {
    return camera;
  }
}
