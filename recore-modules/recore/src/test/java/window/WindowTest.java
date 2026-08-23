package window;

import com.isthisalis.recore.graphics.window.Configuration;
import com.isthisalis.recore.graphics.window.Window;

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
    .title("MeshTest")
    .build(), null);
  }

    @Test 
  void initWindow() {
      window.update();
      window.close();
  }
}    
