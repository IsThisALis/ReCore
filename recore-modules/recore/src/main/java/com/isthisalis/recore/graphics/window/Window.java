package com.isthisalis.recore.graphics.window;

// ReCore imports
  // Core 
import com.isthisalis.recore.core.ComponentLogic;
import com.isthisalis.recore.core.Engine;

  // Util
import com.isthisalis.recore.util.IO;
import com.isthisalis.recore.util.OS;
import com.isthisalis.recore.util.Time;

// Lombok imports
import lombok.Getter;
import lombok.NonNull;

// GLFW imports
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;

// OpenGL imports
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

public class Window implements ComponentLogic {
  
  private long remainTime;
  private long frameDelayTime;

  private @Getter long windowHandle;
  private @Getter Configuration configuration;

  private Time time;

     /**
     * Initializing GLFW, OpenGL, window 
     * All parameters is setting from init method in main class through setters
     * Also sets window and operates with it through getter
     * @param width Width of creating window
     * @param height Height of creating window
     * @param title Title of creating window 
     */
    public void init(@NonNull Configuration configuration, Engine app) {
      this.configuration = configuration;
      if (app != null) time = app.getTime();
      else time = new Time();
      
    // GLFW hint to use X11 on linux
      if(OS.isLinux()) {
        glfwInitHint(GLFW.GLFW_PLATFORM, GLFW.GLFW_PLATFORM_WAYLAND);
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
      windowHandle = glfwCreateWindow(configuration.getWidth(), configuration.getHeight(), configuration.getTitle(), 0L, 0L);

      if(windowHandle == 0L) {
        System.out.println("ReCore: Error in window creating process!");
      }

      // Making OpenGL context current   
        glfwMakeContextCurrent(windowHandle);
        GL.createCapabilities();

        glfwSwapInterval(0);
      // Disabling depth 
        glDisable(GL_DEPTH_TEST);

      // Makes window visible
        glfwShowWindow(windowHandle);

        if (configuration.isVsync()) {
            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            frameDelayTime = 1_000_000_000L / vidmode.refreshRate();
        } else {
            frameDelayTime = 1_000_000_000L / configuration.getFpsLimit();
        }   
    }


      /**
       * Cleans data, use after window closing
       */
    @Override
    public void cleanup() {
      Callbacks.glfwFreeCallbacks(windowHandle);

      // Deleting window and GLFW
      glfwDestroyWindow(windowHandle);
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
      remainTime = frameDelayTime - time.endFrame();
      if (remainTime > 0) {
            glfwWaitEventsTimeout(remainTime / 1_000_000_000.0);
        } else {
            glfwPollEvents();
        }
      glfwSwapBuffers(windowHandle);
    }


      /**
       * Applies new window size
       * Use once to avoid bugs with window resizing
       */
    public void resize(int width, int height) {
      glViewport(0, 0, width, height);
    }


      /**
       * Getter for window state 
       * @return Should window close or not
       */
    public boolean isWindowShouldClose() {
      if(!glfwWindowShouldClose(windowHandle)) {
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
        io.loadIcon(windowHandle, path);
      } catch(Exception e) {
        System.out.println("ReCore: FAIL - setIcon: "+e);
      }
    }


      /**
       * Sends signal to close window 
       */
    public void close() {
      glfwSetWindowShouldClose(windowHandle, true);
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
    public void blend(boolean state) {
      if(state) {
          glEnable(GL_BLEND);
          glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
      }

      if(!state){
          glDisable(GL_BLEND);
      }
  }
}
