package recore.core;

import recore.util.Time;

public class GameLoop {

  private static Time time = new Time();

  public static Time getGameLoop() {
    return time;
  }
}
