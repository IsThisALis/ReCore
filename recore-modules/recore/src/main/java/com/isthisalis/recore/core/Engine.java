package com.isthisalis.recore.core;

import com.isthisalis.recore.graphics.window.Window;
import com.isthisalis.recore.util.Time;

import lombok.Getter;
import lombok.Setter;

/**
 * Engine work organizer class.
 */
public abstract class Engine {

  private @Setter @Getter Window window;
  private final @Getter AssetManager assetManager = new AssetManager();
  private final @Getter Time time = new Time();

  public abstract void init();
  public abstract void update();
  public abstract void cleanup();
  
  public void loop() {
    while (!window.isWindowShouldClose()) {
      update();
      window.clean();
      window.update();
    }
    
    assetManager.cleanup();
    window.cleanup();
    cleanup();
  }
}
