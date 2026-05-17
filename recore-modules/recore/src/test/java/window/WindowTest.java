package window;

import recore.graphics.window.Params;
import recore.graphics.window.Window;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WindowTest {

  static Window window = Params.getWindowInst();
  static Params params = Params.getParams();

    @BeforeAll
  static void createInst() {
    params.setTitle("WindowTest");
    params.setWidth(1080);
    params.setHeight(720);
    params.setVsyncStatus(true);
  }

    @Test 
  void initWindow() {
      window.init();
      window.update();
      window.close();
  }
}    
