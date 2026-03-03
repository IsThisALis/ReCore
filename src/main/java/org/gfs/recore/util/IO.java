package org.gfs.recore.util;

import org.gfs.recore.graphics.textures.*;
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


   /**
   * used to load files with text, do not use for loading images or something!
   * @param path path to your file
   * returns String 
   */ 
    public static String loadTextFile(String path) {

    //
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


  /**
   * Loads image as texture and sets parameters, loads content from resources folder
   * @param path Path to your image file like assets/textures/image.png
   * returns data directly to the texture parameters
   */
  public void loadTexture(String path, Texture texture) {
      try {

        //Reads image from file
        InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);
        byte[] fileBytes = stream.readAllBytes();

        //Puts image in ByteBuffer
        imageBuffer = stack.malloc(fileBytes.length);
        imageBuffer.put(fileBytes);
        imageBuffer.position(0);
        
        //Catches IOException like file not found
      } catch(IOException e) {
          System.out.println("Encountered IO exception");
        }
      
        //Puts image data into IntBuffers
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer comp = stack.mallocInt(1);
        
        //Loads image using STB
        STBImage.stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 4);

        //Transfers texture parameters to int
        int height = h.get();
        int width = w.get();

        //Throws exception if texture not loaded
        if (image == null) {
          throw new RuntimeException("Error while loading texture: "+STBImage.nstbi_failure_reason());
        }

        //Sets texture data
        texture.getParams().setHeight(height);
        texture.getParams().setWidth(width);
        texture.getParams().setImage(image);

    }
}
