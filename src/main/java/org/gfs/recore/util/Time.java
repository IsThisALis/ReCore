package org.gfs.recore.util;




public class Time {

  Long lastTime = 0L;
  float delta;
  
  public void tick() {
    long now = System.nanoTime();
    delta = (now - lastTime) / 1_000_000_000f;
    lastTime = now;
  }

  public float getDelta() {
    return delta;
  } 
}
