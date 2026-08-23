package com.isthisalis.recore.input;

import com.isthisalis.recore.graphics.window.*;
import com.isthisalis.recore.util.NIO;

import lombok.Getter;
import lombok.NonNull;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

  private static @Getter double scrollValueX, scrollValueY;
  public static float mouseX, mouseY;

  private static long cursor;

  private long windowHandle;


    public Input init(Window window) {
      windowHandle = window.getWindowHandle();
      GLFWScrollCallbackI scrollCallback = (windowHandle, xOffset, yOffset) -> {
        scrollValueX = xOffset;
        scrollValueY = yOffset;
      };

      glfwSetScrollCallback(windowHandle, scrollCallback);

      return this;
    }


    public void setCursor(@NonNull String pathToCursorImage) {

      cursor = glfwCreateCursor(NIO.loadGlfwImage(pathToCursorImage), 0, 0);
      glfwSetCursor(windowHandle, cursor);


      GLFWCursorPosCallbackI curorPosCallback = (window, xpos, ypos) -> {
        mouseX = (float) xpos;
        mouseY = (float) ypos;
      };

      glfwSetCursorPosCallback(windowHandle, curorPosCallback);
    }
    
   
     public boolean mouseKeyPressed(int key) {
       if(glfwGetMouseButton(windowHandle, key) == GLFW_PRESS) { return true; } 
       else { return false; }
    }

    public boolean mouseKeyReleased(int key) {
       if(glfwGetMouseButton(windowHandle, key) == GLFW_RELEASE) { return true; } 
       else { return false; }
    }

    public boolean mouseKeyRepeated(int key) {
       if(glfwGetMouseButton(windowHandle, key) == GLFW_REPEAT) { return true; } 
       else { return false; }
    }



    public boolean keyPressed(KeyboardKeys key) {
       if(glfwGetKey(windowHandle, key.getCode()) == GLFW_PRESS) { return true; } 
       else { return false; }
    }

    public boolean keyReleased(KeyboardKeys key) {
       if(glfwGetKey(windowHandle, key.getCode()) == GLFW_RELEASE) { return true; } 
       else { return false; }
    }

    public boolean keyRepeated(KeyboardKeys key) {
       if(glfwGetKey(windowHandle, key.getCode()) == GLFW_REPEAT) { return true; } 
       else { return false; }
    }

    
    public static void resetScrollX() {
      scrollValueX = 0;
    }

    public static void resetScrollY() {
      scrollValueY = 0;
    }
}
