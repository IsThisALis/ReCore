package recore.graphics.window;

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

  private long windowL;

  private static Params params = new Params();
  private static Window window = new Window();


      /**
       * Getter for window parameters class 
       * @return This class instance
       */
    public static Params getParams() {
      return params;
    }


      /**
       * Getter for Window class 
       * @return Window instance
       */ 
    public static Window getWindowInst() {
      return window;
    }
    

      /**
       * Getter for GLFW window 
       * @return Window to use in GLFW (long)
       */ 
    public long getWindow() {
        return windowL;
    }


       /**
        * Setter for GLFW window 
        * @param value GLFW window 
        */ 
    public void setWindow(long value) {
        windowL = value;
    }

    
      /**
       * Setter for window height 
       * @param value Window height
       */ 
    public void setHeight(int value) {
        height = value;
    }
    
    
      /**
       * Getter for window height 
       * @return Window height 
       */ 
    public int getHeight() {
        return height;
    }
    
      
      /**
       * Setter for window width
       * @param value Window width
       */ 
    public void setWidth(int value) {
        width = value;
    }


      /**
       * Getter for window width
       * @return Window width
       */
    public int getWidth() {
        return width;
    }
    
      
      /**
       * Getter for FPS limit (unused)
       * @return FPS limit 
       */ 
    public int getFrameRateLimit() {
      return frameRateLimit;
    }


      /**
       * Setter for FPS limit (unused)
       * @param value FPS limit
       */
    public void setFrameRateLimit(int value) {
      frameRateLimit = value;
    }
    
      
      /**
       * Setter for window title
       * @param value String to set as window title 
       */
    public void setTitle(String value) {
        title = value;
    }


      /**
       * Getter for window title 
       * @return Window title 
       */
    public String getTitle() {
        return title;
    }

    
      /**
       * Getter for vsync state (unused)
       * @return Vsync state
       */ 
    public boolean getVsyncStatus() {
        return vsync;
    }


      /**
       * Setter for vsync state
       * @param value Vsync state 
       */
    public void setVsyncStatus(boolean value) {
      vsync = value;
    }


      /**
       * Getter for fullscreen state (unused)
       * @return Fullscreen state 
       */
    public boolean isFullscreen() {
      return fullscreen;
    }


      /**
       * Setter for fullscreen state (unused)
       * @param value Fullscreen state 
       */
    public void setFullscreen(boolean value) {
      fullscreen = value;
    }

}
