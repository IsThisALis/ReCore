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
     * Initializing GLFW, OpenGL context, window.
     * @param configuration Window configuration @see {@link com.isthisalis.recore.graphics.window.Configuration}
     * @param app 
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
      windowHandle = glfwCreateWindow(configuration.getWidth(),
        configuration.getHeight(),
        configuration.getTitle(),
        0L, 
        0L
      );

      if(windowHandle == 0L) {
        throw new IllegalStateException("ReCore: GLFW unable to create window for some reason");
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
        glfwSetWindowPos(windowHandle, 
          (vidmode.width() - configuration.getWidth()) / 2,
          (vidmode.height() - configuration.getHeight()) / 2
        );
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
       * Updates window.
       * Automatically called in {@link com.isthisalis.recore.core.Engine#loop()}
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
       * Applies new window size.
       * Use once to avoid bugs with window resizing.
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
       * Sends signal to close window 
       */
    public void close() {
      glfwSetWindowShouldClose(windowHandle, true);
    }

    
      /**
       * Clears window, used in render cycle
       */
    public void clean() {
      glClear(GL_COLOR_BUFFER_BIT);
    }
}