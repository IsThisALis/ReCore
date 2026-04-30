package recore.graphics.window;

  // ReCore imports
import recore.core.ComponentLogic;
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
  
  //Gets parameters instance
  Params params;

	@Override
     /**
     * 
     * Initializing GLFW, OpenGL, window
     * @param width Width of creating window
     * @param height Height of creating window
     * @param title Title of creating window 
     * All parameters is setting from init method in main class through setters
     * Also sets window and operates with it through getter
     *
     */
    public void init() {

      params = Params.getParams();
      
    //GLFW hint to use X11 on linux
      if(OS.isLinux()) {
        glfwInitHint(GLFW.GLFW_PLATFORM, GLFW.GLFW_PLATFORM_X11);
      }

    // MacOS hints
      if(OS.isMac()) {
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE); 
      }

      //Initializing GLFW
      if(!glfwInit()) {
        throw new IllegalStateException("GLFW is not initialized");
      }

      // Setting up GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_DEPTH_BITS, 24);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        
      // Creating window
        params.setWindow(glfwCreateWindow(params.getWidth(), params.getHeight(), params.getTitle(), 0L, 0L));
      if(params.getWindow() == 0L) {
        params.setWindow(glfwCreateWindow(params.getWidth(), params.getHeight(), params.getTitle(), 0L, 0L));
      }

      // Making OpenGL context current   
        glfwMakeContextCurrent(params.getWindow());
        GL.createCapabilities();

        glfwSwapInterval(1);
        glDisable(GL_DEPTH_TEST);

        glfwShowWindow(params.getWindow());
    }


  @Override
  // Cleans glfw and window through getter
    public void cleanup() {
      Callbacks.glfwFreeCallbacks(params.getWindow());

      //Deleting window and GLFW
      glfwDestroyWindow(params.getWindow());
      glfwTerminate();
        
        //set GLFWErrorCallback null and checks
        GLFWErrorCallback callback = glfwSetErrorCallback(null);
        if (callback != null) {
            callback.free();
        }

    }

  @Override 
    /**
    * 
    * Updates window data, call it from your update method
    * Operates with window through getter
    *
    */ 
    public void update() {
      glfwSwapBuffers(params.getWindow());
    }

    // Resizes window using width and height in parameters
    public void resize() {
      glViewport(0, 0, params.getWidth(), params.getHeight());
    }

    // Window cycle
    public void loop() {
            update();
            glfwPollEvents();
          }

    public boolean isWindowShouldClose() {
      if(!glfwWindowShouldClose(params.getWindow())) {
        return false;
      }
      return true;
    }

    public void setIcon(String path) {
      IO io = new IO();
      try {
        glfwSetWindowIcon(params.getWindow(), io.loadIcon(path));
      } catch(Exception e) {
        System.out.println("ReCore: FAIL - setIcon: "+e);
      }
    }

    
    public void cleanWindow() {
      glClear(GL_COLOR_BUFFER_BIT);
    }

    public void blend(boolean onOff) {
      if(onOff) {
          glEnable(GL_BLEND);
          glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
      } else {
          glDisable(GL_BLEND);
        }
  }
}
