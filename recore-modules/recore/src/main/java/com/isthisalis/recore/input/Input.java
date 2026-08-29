package com.isthisalis.recore.input;

import com.isthisalis.recore.graphics.window.*;
import com.isthisalis.recore.util.NIO;

import lombok.Getter;
import lombok.NonNull;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import static org.lwjgl.glfw.GLFW.*;

/**
 * User input processing class.
 */
public class Input {

  /**
   * Mouse wheel scroll value.
   */
  private static @Getter double scrollValueX, scrollValueY;

  /**
   * Mouse pointer position.
   */
  public static @Getter float mouseX, mouseY;

  /**
   * GLFW cursor.
   */
  private long cursor;

  /**
   * GLFW window, attached to instance.
   */
  private long windowHandle;

  /**
   * Initializes input. Attaches to window and sets mouse wheel scroll callback.
   * @param window Window to attach to instance.
   */
  public void init(Window window) {
    windowHandle = window.getWindowHandle();
    GLFWScrollCallbackI scrollCallback = (windowHandle, xOffset, yOffset) -> {
      scrollValueX = xOffset;
      scrollValueY = yOffset;
    };
    glfwSetScrollCallback(windowHandle, scrollCallback);
  }

  /**
   * Sets custom cursor image.
   * @param pathToCursorImage Path to new cursor image.
   */
  public void setCursor(@NonNull String pathToCursorImage) {

    cursor = glfwCreateCursor(NIO.loadGlfwImage(pathToCursorImage), 0, 0);
    glfwSetCursor(windowHandle, cursor);

    GLFWCursorPosCallbackI curorPosCallback = (window, xpos, ypos) -> {
      mouseX = (float) xpos;
      mouseY = (float) ypos;
    };

      glfwSetCursorPosCallback(windowHandle, curorPosCallback);
    }
    
  /**
   * Checks if mouse button pressed at this moment.
   * @param key Mouse button key {@link com.isthisalis.recore.input.MouseKeys}
   * @return Pressed or not value in boolean type.
   */
  public boolean mouseKeyPressed(MouseKeys key) {
    if(glfwGetMouseButton(windowHandle, key.getCode()) == GLFW_PRESS) { return true; } 
    else { return false; }
  }

  /**
   * Checks if mouse button released at this moment.
   * @param key Mouse button key {@link com.isthisalis.recore.input.MouseKeys}
   * @return Released or not value in boolean type.
   */
  public boolean mouseKeyReleased(MouseKeys key) {
    if(glfwGetMouseButton(windowHandle, key.getCode()) == GLFW_RELEASE) { return true; } 
    else { return false; }
  }

  /**
   * Checks if mouse button repeated at this moment.
   * @param key Mouse button key {@link com.isthisalis.recore.input.MouseKeys}
   * @return Repeated or not value in boolean type.
   */
  public boolean mouseKeyRepeated(MouseKeys key) {
    if(glfwGetMouseButton(windowHandle, key.getCode()) == GLFW_REPEAT) { return true; } 
    else { return false; }
  }


  /**
   * Checks if keyboard key pressed at this moment.
   * @param key Keyboard button key {@link com.isthisalis.recore.input.KeyboardKeys}
   * @return Pressed or not value in boolean type.
   */
  public boolean keyPressed(KeyboardKeys key) {
    if(glfwGetKey(windowHandle, key.getCode()) == GLFW_PRESS) { return true; } 
    else { return false; }
  }

  /**
   * Checks if keyboard key released at this moment.
   * @param key Keyboard button key {@link com.isthisalis.recore.input.KeyboardKeys}
   * @return Released or not value in boolean type.
   */
  public boolean keyReleased(KeyboardKeys key) {
    if(glfwGetKey(windowHandle, key.getCode()) == GLFW_RELEASE) { return true; } 
    else { return false; }
  }

  /**
   * Checks if keyboard key repeated at this moment.
   * @param key Keyboard button key {@link com.isthisalis.recore.input.KeyboardKeys}
   * @return Repeated or not value in boolean type.
   */
  public boolean keyRepeated(KeyboardKeys key) {
    if(glfwGetKey(windowHandle, key.getCode()) == GLFW_REPEAT) { return true; } 
    else { return false; }
  }

  /**
   * Resets mouse wheel scroll value.
   */
  public static void resetScrollX() {
    scrollValueX = 0;
  }

  /**
   * Resets mouse wheel scroll value.
   */
  public static void resetScrollY() {
    scrollValueY = 0;
  }
}
