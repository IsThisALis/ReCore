package com.isthisalis.recore.core;

import com.isthisalis.recore.graphics.window.Window;
import com.isthisalis.recore.input.Input;
import com.isthisalis.recore.util.Time;

import lombok.Getter;
import lombok.Setter;

/**
 * Engine work organizer class.
 */
public abstract class Engine {

  /**
   * Window attached to engine. 
   * <p> Should be set and initialized first.
   */
  private @Setter @Getter Window window;

  /**
   * Engine AssetManager instance. Used to load resources.
   * <p> Do not load assets manually if you want to auto-clean assets at end of program work.
   */
  private final @Getter AssetManager assetManager = new AssetManager();

  /**
   * Engine time cycle util.
   */
  private final @Getter Time time = new Time();

  /**
   * Engine input manager. Should be initialized at {@link #init()}.
   * @see {@link com.isthisalis.recore.input.Input#init(Window)}.
   */
  private final @Getter Input input = new Input();

  /**
   * Initializes engine, should be called first.
   */
  public abstract void init();

  /**
   * Polls all updates.
   */
  public abstract void update();

  /**
   * Cleans all data after work.
   */
  public abstract void cleanup();
  
  /**
   * Engine work loop, should be called after init().
   */
  public void loop() {
    while (!window.isWindowShouldClose()) {
      window.clean();
      time.startFrame();
      update();
      window.update();
    }
    
    assetManager.cleanup();
    window.cleanup();
    cleanup();
  }
}
