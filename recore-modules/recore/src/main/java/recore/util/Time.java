package recore.util;

public class Time {

  long now;
  long lastTime = 0L;
  float delta;

  long start;
  long end;
  long elapsed;

  
  public void tick() {
    now = System.nanoTime();
    delta = (now - lastTime) / 1_000_000_000f;
    lastTime = now;
  }

  public float getDelta() {
    return delta;
  }


    public void startFrame() {
        start = System.nanoTime();
    }

    public long endFrame() {
        end = System.nanoTime();
        elapsed = end - start;
        return elapsed;
    }
}
