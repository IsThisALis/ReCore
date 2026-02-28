package org.gfs.recore.graphics.window;

/**
 * @param title Title of window 
 * @param height Height of window
 * @param width Width of window 
 * @param vsync Used to check and set vsync state (on/off) overrides frameRateLimit
 * @param frameRateLimit Cap of FPS, used to prevent overloading by rendering too many frames
 * @param window Used to interact with window
 */

public class Params {
		
	private String title;

  private boolean vsync;
  private boolean fullscreen;

	private int height;
	private int width;
	private int frameRateLimit;

  private long window;

  private static Params params = new Params();


      //Instance
      //Params 

    public static Params getParams() {
        return params;
    }

      //Long 
      //window

    public long getWindow() {
        return window;
    }

    public void setWindow(long value) {
        window = value;
    }
    
      //int 
      //height 

    public void setHeight(int value) {
        height = value;
    }
    
    public int getHeight() {
        return height;
    }
    
      //width

    public void setWidth(int value) {
        width = value;
    }

    public int getWidth() {
        return width;
    }
    
      //frameRateLimit

    public int getFrameRateLimit() {
      return frameRateLimit;
    }

    public void setFrameRateLimit(int value) {
      frameRateLimit = value;
    }
    
      //String
      //title 

    public void setTitle(String value) {
        title = value;
    }

    public String getTitle() {
        return title;
    }
    
      //boolean
      //vsync

    public boolean getVsyncStatus() {
        return vsync;
    }

    public void setVsyncStatus(boolean value) {
      vsync = value;
    }

      //fullscreen
    public boolean isFullscreen() {
      return fullscreen;
    }

    public void setFullscreen(boolean value) {
      fullscreen = value;
    }

}
