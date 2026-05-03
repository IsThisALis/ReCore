package recore.graphics.window;

  // ReCore imports
  // Core 
import recore.core.ComponentLogic;

  // Util
import recore.util.IO;
import recore.util.OS;

  // GLFW imports
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.*; 

  // OpenGL imports
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL15.*;

public class Window implements ComponentLogic {
  
  // Gets parameters instance
  Params params;

     /**
     * Initializing GLFW, OpenGL, window 
     * All parameters is setting from init method in main class through setters
     * Also sets window and operates with it through getter
     * @param width Width of creating window
     * @param height Height of creating window
     * @param title Title of creating window 
     */
  @Override
    public void init() {
    // Getting parameters instance
      params = Params.getParams();
      
    // GLFW hint to use X11 on linux
      if(OS.isLinux()) {
        glfwInitHint(GLFW.GLFW_PLATFORM, GLFW.GLFW_PLATFORM_X11);
      }

    // MacOS hints
      if(OS.isMac()) {
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE); 
      }

      // Initializing GLFW
      if(!glfwInit()) {
        throw new IllegalStateException("ReCore: GLFW is not initialized!");
      }

      // Setting up GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        //glfwWindowHint(GLFW_DEPTH_BITS, 24);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        
      // Creating window
        params.setWindow(glfwCreateWindow(params.getWidth(), params.getHeight(), params.getTitle(), 0L, 0L));
      if(params.getWindow() == 0L) {
        System.out.println("ReCore: Error in window creating process!");
        params.setWindow(glfwCreateWindow(params.getWidth(), params.getHeight(), params.getTitle(), 0L, 0L));
      }

      // Making OpenGL context current   
        glfwMakeContextCurrent(params.getWindow());
        GL.createCapabilities();

        glfwSwapInterval(1);
      // Disabling depth 
        glDisable(GL_DEPTH_TEST);

      // Makes window visible
        glfwShowWindow(params.getWindow());
    }


      /**
       * Cleans data, use after window closing
       */
  @Override
    public void cleanup() {
      Callbacks.glfwFreeCallbacks(params.getWindow());

      // Deleting window and GLFW
      glfwDestroyWindow(params.getWindow());
      glfwTerminate();
        
        // Set GLFWErrorCallback null and checks
        GLFWErrorCallback callback = glfwSetErrorCallback(null);
        if (callback != null) {
            callback.free();
        }
    }


      /**
       * 
       * Updates window data, call it from your update method
       * Operates with window through getter
       */ 
  @Override
    public void update() {
      glfwSwapBuffers(params.getWindow());
      glfwPollEvents();
    }


      /**
       * Applies new window size
       * Use once to avoid bugs with window resizing
       */
    public void resize() {
      glViewport(0, 0, params.getWidth(), params.getHeight());
    }


      /**
       * Getter for window state 
       * @return Should window close or not
       */
    public boolean isWindowShouldClose() {
      if(!glfwWindowShouldClose(params.getWindow())) {
        return false;
      }
      return true;
    }


      /**
       * Loads and sets icon
       * @param path Path to imaage file
       */
    public void setIcon(String path) {
      IO io = new IO();
      try {
        io.loadIcon(params.getWindow(), path);
      } catch(Exception e) {
        System.out.println("ReCore: FAIL - setIcon: "+e);
      }
    }

    
      /**
       * Clears window, used in render cycle
       */
    public void cleanWindow() {
      glClear(GL_COLOR_BUFFER_BIT);
    }


      /**
       * Operates with blending state, useful when need to render objects with empty pixels in texture
       * @param on Blend state 
       */
    public void blend(boolean on) {
      if(on) {
          glEnable(GL_BLEND);
          glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
      }

      if(!on){
          glDisable(GL_BLEND);
      }
  }
}
