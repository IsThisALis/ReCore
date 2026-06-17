package recore.util;

import java.util.HashMap;

import recore.graphics.window.*;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

  private static final HashMap<String, Integer> keyMap = new HashMap<>();
  private static final HashMap<String, Integer> mouseMap = new HashMap<>();
  private static final Params params = Params.getParams();

  private static double scrollValueX, scrollValueY;
  public static float mouseX, mouseY;
  private static double[] mxpos = new double[1];
  private static double[] mypos = new double[1];

  private static long cursor;

    static {
        keyMap.put("SPACE", 32);
        keyMap.put("APOSTROPHE", 39);
        keyMap.put("COMMA", 44);
        keyMap.put("MINUS", 45);
        keyMap.put("PERIOD", 46);
        keyMap.put("SLASH", 47);
        keyMap.put("0", 48);
        keyMap.put("1", 49);
        keyMap.put("2", 50);
        keyMap.put("3", 51);
        keyMap.put("4", 52);
        keyMap.put("5", 53);
        keyMap.put("6", 54);
        keyMap.put("7", 55);
        keyMap.put("8", 56);
        keyMap.put("9", 57);
        keyMap.put("SEMICOLON", 59);
        keyMap.put("EQUAL", 61);
        keyMap.put("A", 65);
        keyMap.put("B", 66);
        keyMap.put("C", 67);
        keyMap.put("D", 68);
        keyMap.put("E", 69);
        keyMap.put("F", 70);
        keyMap.put("G", 71);
        keyMap.put("H", 72);
        keyMap.put("I", 73);
        keyMap.put("J", 74);
        keyMap.put("K", 75);
        keyMap.put("L", 76);
        keyMap.put("M", 77);
        keyMap.put("N", 78);
        keyMap.put("O", 79);
        keyMap.put("P", 80);
        keyMap.put("Q", 81);
        keyMap.put("R", 82);
        keyMap.put("S", 83);
        keyMap.put("T", 84);
        keyMap.put("U", 85);
        keyMap.put("V", 86);
        keyMap.put("W", 87);
        keyMap.put("X", 88);
        keyMap.put("Y", 89);
        keyMap.put("Z", 90);
        keyMap.put("LEFT_BRACKET", 91);
        keyMap.put("BACKSLASH", 92);
        keyMap.put("RIGHT_BRACKET", 93);
        keyMap.put("GRAVE_ACCENT", 96);
        keyMap.put("WORLD_1", 161);
        keyMap.put("WORLD_2", 162);
        keyMap.put("ESCAPE", 256);
        keyMap.put("ENTER", 257);
        keyMap.put("TAB", 258);
        keyMap.put("BACKSPACE", 259);
        keyMap.put("INSERT", 260);
        keyMap.put("DELETE", 261);
        keyMap.put("RIGHT", 262);
        keyMap.put("LEFT", 263);
        keyMap.put("DOWN", 264);
        keyMap.put("UP", 265);
        keyMap.put("PAGE_UP", 266);
        keyMap.put("PAGE_DOWN", 267);
        keyMap.put("HOME", 268);
        keyMap.put("END", 269);
        keyMap.put("CAPS_LOCK", 280);
        keyMap.put("SCROLL_LOCK", 281);
        keyMap.put("NUM_LOCK", 282);
        keyMap.put("PRINT_SCREEN", 283);
        keyMap.put("PAUSE", 284);
        keyMap.put("F1", 290);
        keyMap.put("F2", 291);
        keyMap.put("F3",292);
        keyMap.put("F4", 293);
        keyMap.put("F5", 294);
        keyMap.put("F6", 295);
        keyMap.put("F7", 296);
        keyMap.put("F8", 297);
        keyMap.put("F9", 298);
        keyMap.put("F10", 299);
        keyMap.put("F11", 300);
        keyMap.put("F12", 301);
        keyMap.put("F13", 302);
        keyMap.put("F14", 303);
        keyMap.put("F15", 304);
        keyMap.put("F16", 305);
        keyMap.put("F17", 306);
        keyMap.put("F18", 307);
        keyMap.put("F19", 308);
        keyMap.put("F20", 309);
        keyMap.put("F21", 310);
        keyMap.put("F22", 311);
        keyMap.put("F23", 312);
        keyMap.put("F24", 313);
        keyMap.put("F25", 314);
        keyMap.put("KP_0", 320);
        keyMap.put("KP_1", 321);
        keyMap.put("KP_2", 322);
        keyMap.put("KP_3", 323);
        keyMap.put("KP_4", 324);
        keyMap.put("KP_5", 325);
        keyMap.put("KP_6", 326);
        keyMap.put("KP_7", 327);
        keyMap.put("KP_8", 328);
        keyMap.put("KP_9", 329);
        keyMap.put("KP_DECIMAL", 330);
        keyMap.put("KP_DIVIDE", 331);
        keyMap.put("KP_MULTIPLY", 332);
        keyMap.put("KP_SUBTRACT", 333);
        keyMap.put("KP_ADD", 334);
        keyMap.put("KP_ENTER", 335);
        keyMap.put("KP_EQUAL", 336);
        keyMap.put("LEFT_SHIFT", 340);
        keyMap.put("LEFT_CONTROL", 341);
        keyMap.put("LEFT_ALT", 342);
        keyMap.put("LEFT_SUPER", 343);
        keyMap.put("RIGHT_SHIFT", 344);
        keyMap.put("RIGHT_CONTROL", 345);
        keyMap.put("RIGHT_ALT", 346);
        keyMap.put("RIGHT_SUPER", 347);
        keyMap.put("MENU", 348);

        mouseMap.put("MOUSE_1", 0);
        mouseMap.put("MOUSE_2", 1);
        mouseMap.put("MOUSE_3", 2);
        mouseMap.put("MOUSE_4", 3);
        mouseMap.put("MOUSE_5", 4);
        mouseMap.put("MOUSE_6", 5);
        mouseMap.put("MOUSE_7", 6);
        mouseMap.put("MOUSE_8", 7);

        mouseMap.put("MOUSE_LEFT", 0);
        mouseMap.put("MOUSE_RIGHT", 1);
        mouseMap.put("MOUSE_MIDDLE", 2);
        mouseMap.put("MOUSE_LAST", 7);
    }

    static {
      GLFWScrollCallbackI scrollCallback = (window, xOffset, yOffset) -> {
        scrollValueX = xOffset;
        scrollValueY = yOffset;
      };

      glfwSetScrollCallback(params.getWindow(), scrollCallback);
    }


    public static void setCursor(String pathToCursorImage) {
      IO io = new IO();
      if(pathToCursorImage == null) {
        cursor = glfwCreateStandardCursor(0x00036001);
      }


      cursor = glfwCreateCursor(io.loadImage(pathToCursorImage), 0, 0);
      glfwSetCursor(params.getWindow(), cursor);


      GLFWCursorPosCallbackI curorPosCallback = (window, xpos, ypos) -> {
        mouseX = (float) xpos;
        mouseY = (float) ypos;
      };

      glfwSetCursorPosCallback(params.getWindow(), curorPosCallback);
    }

    
    public static int getKey(String key) {
        return keyMap.get(key);
    }

    public static int getMouseKey(String key) {
      return mouseMap.get(key);
    }

    
    public static boolean keyContain(String key) {
        return keyMap.containsKey(key);
    }

    public static boolean mouseKeyContain(String key) {
      return mouseMap.containsKey(key);
    }

    
    public static int keyMapSize() {
        return keyMap.size();
    }

    public static int mouseMapSize() {
      return mouseMap.size();
    }

   
     public static boolean mouseKeyPressed(int key) {
       if(glfwGetMouseButton(params.getWindow(), key) == GLFW_PRESS) { return true; } 
       else { return false; }
    }

    public static  boolean mouseKeyReleased(int key) {
       if(glfwGetMouseButton(params.getWindow(), key) == GLFW_RELEASE) { return true; } 
       else { return false; }
    }

    public static boolean mouseKeyRepeated(int key) {
       if(glfwGetMouseButton(params.getWindow(), key) == GLFW_REPEAT) { return true; } 
       else { return false; }
    }



     public static boolean keyPressed(int key) {
       if(glfwGetKey(params.getWindow(), key) == GLFW_PRESS) { return true; } 
       else { return false; }
    }

    public static boolean keyReleased(int key) {
       if(glfwGetKey(params.getWindow(), key) == GLFW_RELEASE) { return true; } 
       else { return false; }
    }

    public static boolean keyRepeated(int key) {
       if(glfwGetKey(params.getWindow(), key) == GLFW_REPEAT) { return true; } 
       else { return false; }
    }

    public static void getCursorPos() {
      glfwGetCursorPos(params.getWindow(), mxpos, mypos);
      mouseX = (float) mxpos[0];
      mouseY = (float) mypos[0];
    }

    
    public static void resetScrollX() {
      scrollValueX = 0;
    }

    public static void resetScrollY() {
      scrollValueY = 0;
    }

    public static double getScrollX() {
      return scrollValueX;    
    }

    public static double getScrollY() {
      return scrollValueY;
    }
}
