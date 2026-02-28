package org.gfs.recore.util;

import org.gfs.recore.graphics.textures.Params;
//IO imports
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

//NIO imports
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//Util imports
import java.util.Stack;
import java.util.stream.Stream;

//LWJGL imports
import org.lwjgl.system.MemoryStack;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBImage.*;

public class IO {
    ByteBuffer imageBuffer;
    MemoryStack stack = MemoryStack.stackPush();

  Params params = Params.getParams();
   /**
   * used to load files with text, do not use for loading images or something!
   * @param path path to your file
   * returns String 
   */ 
    public static String loadTextFile(String path) {
    InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);
    try {
      if (stream == null) {
          throw new RuntimeException("Content not found");
      }
      return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    } catch(IOException e) {
        throw new RuntimeException("encountered unknown error while reading file with path: "+path);
    }
  }

  public void loadImage(String path) {
    try {
      InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);
      byte[] fileBytes = stream.readAllBytes();

        imageBuffer = stack.malloc(fileBytes.length);
        imageBuffer.put(fileBytes);
        imageBuffer.flip();
      } catch(IOException e) {
        System.out.println("Encountered IO exception");
      }
      
       IntBuffer w = stack.mallocInt(1);
       IntBuffer h = stack.mallocInt(1);
       IntBuffer comp = stack.mallocInt(1);
        
        STBImage.stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 4);

        

        int height = h.get();
        int width = w.get();

        if (image == null) {
          throw new RuntimeException("Ошибка загрузки текстуры: " + STBImage.nstbi_failure_reason());
        }

        params.setHeight(height);
        params.setWidth(width);
        params.setImage(image);

    }
}
