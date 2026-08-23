package com.isthisalis.recore.util;

import com.isthisalis.recore.graphics.textures.*;

// Java imports
// IO imports
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

// NIO imports
import java.nio.charset.StandardCharsets;

// LWJGL imports
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.stb.STBImage;
import org.lwjgl.glfw.GLFWImage;
import static org.lwjgl.glfw.GLFW.*;

@Deprecated
public class IO {


    /**
    * Used to load files to String.
    * @param path path to file need to be loaded.
    * @return File source in String.
    */ 
    @Deprecated
    public static String loadTextFile(String path) {
    
    try (InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);) {

      if (stream == null) {
          throw new IOException("ReCore: cannot find file " + "[ " +path+ " ]");
      }

      return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    } catch(IOException e) {
        throw new RuntimeException("ReCore: Encountered unknown error while loading file " + "[ " + path + " ]" + "[ " + e + " ]");
    }
  }


  /**
   * Loads image as texture and sets parameters, loads content from resources folder
   * @param path Path to your image file like assets/textures/image.png
   * @return GLFWImage ata directly to the texture parameters
   */
  @Deprecated
  public Texture loadTexture(String path) {
      ByteBuffer imageBuffer;
      try (MemoryStack stack = MemoryStack.stackPush()) {

          // Reads image from file
        InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);
        if(stream == null) {
          throw new IOException("IO Error! Check file path: "+path);
        }
        byte[] fileBytes = stream.readAllBytes();
        stream.close(); 

          // Puts raw image data in ByteBuffer
        imageBuffer = BufferUtils.createByteBuffer(fileBytes.length);
        imageBuffer.put(fileBytes);
        imageBuffer.position(0);
            

          // Puts image data into IntBuffers
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer comp = stack.mallocInt(1);
      

          // Loads image with STB
        STBImage.stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 4);

      
          // Transfers texture parameters to int variables
        int height = h.get();
        int width = w.get();


          // Throws exception if texture image not loaded
        if (image == null) {
          throw new RuntimeException("Error while loading texture: "+STBImage.nstbi_failure_reason());
        }

        return new Texture(width, height, image);

      } catch(IOException e) {
          System.out.println("ReCore: Encountered IO exception while loading file " + "[ " + path + " ] " + "[ " + e + " ]");
          return new Texture(0, 0, null);
        }
    }
  

  @Deprecated
  public void loadIcon(long window, String path) {
      ByteBuffer imageBuffer = null;

      try (MemoryStack stack = MemoryStack.stackPush()) {


        InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);
        if(stream == null) {
          throw new IOException("ReCore: IO Error! Check file path please: "+path);
        }


          // Loads and pushes icon image data to buffer.
        byte[] fileBytes = stream.readAllBytes();
        imageBuffer = BufferUtils.createByteBuffer(fileBytes.length);
        imageBuffer.put(fileBytes);
        imageBuffer.position(0);
        
      
          // Puts image data into IntBuffers
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer comp = stack.mallocInt(1);


        ByteBuffer pixels = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 4);

        if (pixels == null) {
          throw new RuntimeException("Unable to load icon: " + STBImage.stbi_failure_reason());
        }

        GLFWImage.Buffer icons = GLFWImage.malloc(1);
        GLFWImage icon = GLFWImage.malloc().set(w.get(0), h.get(0), pixels);

        icons.put(0, icon); 
        glfwSetWindowIcon(window, icons);
        icon.free();

        STBImage.stbi_image_free(pixels);
        icons.free();

        } catch(IOException e) {
          System.out.println("Encountered IO exception: "+e);
        }
    }


  @Deprecated
  public GLFWImage loadImage(String path) {
      ByteBuffer imageBuffer = null;
      try (MemoryStack stack = MemoryStack.stackPush()) {

        //Reads image from file
        InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);
        if(stream == null) {
          throw new IOException("IO Error! Check file path please: "+path);
        }


        byte[] fileBytes = stream.readAllBytes();
        stream.close();

        //Puts image in ByteBuffer
        imageBuffer = BufferUtils.createByteBuffer(fileBytes.length);
        imageBuffer.put(fileBytes);
        imageBuffer.position(0);
        
      
        //Puts image data into IntBuffers
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer comp = stack.mallocInt(1);


        ByteBuffer pixels = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, 4);
        if (pixels == null) {
          throw new RuntimeException("Unable to load icon: " + STBImage.stbi_failure_reason());
        }


        GLFWImage image = GLFWImage.malloc().set(w.get(0), h.get(0), pixels);
        STBImage.stbi_image_free(pixels);


        return image;

    } catch(IOException e) {
          throw new RuntimeException("Encountered IO exception: "+e);
        }
    }
}
