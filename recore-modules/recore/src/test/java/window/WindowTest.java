package window;

import com.isthisalis.recore.graphics.window.Configuration;
import com.isthisalis.recore.graphics.window.Window;

import app.Application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WindowTest {

  static Window window;

    @BeforeAll
  static void init() {
    window = new Window();
    window.init(Configuration.builder()
    .width(320)
    .height(240)
    .vsync(true)
    .title("WindowTest")
    .build(), new Application());
  }

    @Test 
  void initWindow() {
      window.update();
      window.close();
  }
}    
