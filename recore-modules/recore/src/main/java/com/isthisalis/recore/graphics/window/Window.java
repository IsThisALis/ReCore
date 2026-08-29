package com.isthisalis.recore.graphics.window;

// ReCore imports
  // Core 
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
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

// OpenGL imports
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

public class Window {
  
  /**
   * Memory optimisation - reusing variables instead of creating new ones.
   */
  private long remainTime, frameDelayTime;

  /**
   * GLFW window.
   */
  private @Getter long windowHandle;

  /**
   * Window properties.
   */
  private @Getter Configuration configuration;

  /**
   * Window time cycle.
   */
  private Time time;

     /**
     * Initializing GLFW, OpenGL context, window.
     * @param configuration Window configuration @see {@link com.isthisalis.recore.graphics.window.Configuration}
     * @param app Application to attach time cycle.
     */
    public void init(@NonNull Configuration configuration, Engine app) {
      this.configuration = configuration;

      if (app != null) time = app.getTime();
      else throw new IllegalStateException("ReCore: Can't attach window to null application");

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
      glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
      glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        
      // Creating window
      if (configuration.getWindowMode().equals(WindowMode.FULLSCREEN)) {
        GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowHandle = glfwCreateWindow(
        vidmode.width(),
        vidmode.height(),
        configuration.getTitle(),
        0L, 
        0L

      ); } else {
      windowHandle = glfwCreateWindow(
        configuration.getWidth(),
        configuration.getHeight(),
        configuration.getTitle(),
        0L, 
        0L
      ); }

      if(windowHandle == 0L) {
        throw new IllegalStateException("ReCore: GLFW unable to create window for some reason");
      }

      // Making OpenGL context current   
      glfwMakeContextCurrent(windowHandle);
      GL.createCapabilities();

      
      // Disabling depth 
      glDisable(GL_DEPTH_TEST);

      // Makes window visible
      glfwShowWindow(windowHandle);

      if (configuration.isVsync()) {
        glfwSwapInterval(1);
      } else {
          frameDelayTime = 1_000_000_000L / configuration.getFpsLimit();
        }   
    }


    /**
     * Cleans data.
     */
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
     * Updates window.
     * Automatically called in {@link com.isthisalis.recore.core.Engine#loop()}
     */
    public void update() {
      if (frameDelayTime > 0) {
        remainTime = frameDelayTime - time.endFrame();
        if (remainTime > 0) {
              glfwWaitEventsTimeout(remainTime / 1_000_000_000.0);
          } else {
            glfwPollEvents();
          }
        } else {
          glfwPollEvents();
        }
      glfwSwapBuffers(windowHandle);  
    }


    /**
     * Applies new window size.
     */
    public void resize(int width, int height) {
      glViewport(0, 0, width, height);
    }


    /**
     * Getter for window state 
     * @return Should window close or not
     */
    public boolean isWindowShouldClose() {
      return glfwWindowShouldClose(windowHandle);
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
     * Sends signal for GLFW to close window.
     */
    public void close() {
      glfwSetWindowShouldClose(windowHandle, true);
    }

    
    /**
     * Clears window, used in render cycle.
     */
    public void clean() {
      glClear(GL_COLOR_BUFFER_BIT);
    }
}