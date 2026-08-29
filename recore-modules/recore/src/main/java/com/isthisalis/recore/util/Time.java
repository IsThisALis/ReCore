package com.isthisalis.recore.util;

import lombok.Getter;

/**
 * Time utility class.
 */
public class Time {

  /**
   * Memory optimisation - reusing variables instead of creating new ones.
   */
  long now, lastTime, elapsed, end, start = 0L;

  /**
   * Delta between two ticks
   * @see {@link com.isthisalis.recore.util.Time#tick()}
   */
  private @Getter float delta;

  /**
   * Used to find time delta.
   */
  public void tick() {
    now = System.nanoTime();
    delta = (now - lastTime) / 1_000_000_000f;
    lastTime = now;
  }

  /**
   * Used to count time between 2 frames.
   */
  public void startFrame() {
    start = System.nanoTime();
  }

  /**
   * Used to count time between 2 frames.
   */
  public long endFrame() {
    end = System.nanoTime();
    elapsed = end - start;
    return elapsed;
  }
}
