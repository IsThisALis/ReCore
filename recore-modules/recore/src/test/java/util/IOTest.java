package util;

import org.junit.jupiter.api.Test;
import recore.util.IO;

public class IOTest {

    @Test
  void loadString() {
    String text = IO.loadTextFile("tests/text.txt");
    System.out.println("TEST/IO: Loaded text: "+text);
  }


    @Test
  void loadImage() {
    IO io = new IO();
    System.out.println("TEST/IO: Loaded GLFWImage: "+io.loadImage("tests/banana.png"));
  }
}
