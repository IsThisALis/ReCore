package rexample;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;

import java.awt.Robot;

public class GameTest {

  static Main main;
  static Robot bot;

    @BeforeAll
  static void createInst() {
    try {
        bot = new Robot();
    } catch(Exception e) {
      System.out.println(e);
    }
      main = new Main();
      main.init();
  }

    @RepeatedTest(100)
    @Order(1)
  void jump() {
      main.loop();
      try {
        Runtime.getRuntime().exec("xdotool keydown --delay 100 space keyup space");
      } catch(Exception e) {
        System.out.println(e);
      }
  }

    @RepeatedTest(100)
    @Order(2)
  void d() {
      main.loop();
      try {
        Runtime.getRuntime().exec("xdotool keydown --delay 100 d keyup d");
      } catch(Exception e) {
        System.out.println(e);
      }
  }

    @RepeatedTest(100)
    @Order(3)
  void a() {
      main.loop();
      try {
        Runtime.getRuntime().exec("xdotool keydown --delay 100 a keyup a");
      } catch(Exception e) {
        System.out.println(e);
      }
  }

    @RepeatedTest(100)
    @Order(4)
  void jumpC() {
      main.loop();
      try {
        Runtime.getRuntime().exec("xdotool key c+space");
      } catch(Exception e) {
        System.out.println(e);
      }
  }
    @RepeatedTest(100)
    @Order(5)
  void jumpD() {
      main.loop();
      try {
        Runtime.getRuntime().exec("xdotool key e+space");
      } catch(Exception e) {
        System.out.println(e);
      }
  }
}    
