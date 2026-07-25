package com.isthisalis.recore.core;

import com.isthisalis.recore.util.Time;

public class GameLoop {

  private static Time time = new Time();

  public static Time getGameLoop() {
    return time;
  }
}
