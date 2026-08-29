package com.isthisalis.recore.util;

import java.nio.file.Path;
import java.util.logging.Logger;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;

import java.io.IOException;

/**
 * New IO implementation made to work with all file systems.
 */
public class NIO {

  private static Logger logger = Logger.getLogger(NIO.class.getName());

  /**
   * Loads data from file in byte array.
   * @param path Path to file.
   * @return Data from file as byte array
   */
  public static byte[] load(String path) {
      Path file = Path.of(path);
      try {
        if (Files.exists(file)) return Files.readAllBytes(file);
        else return NIO.class.getClassLoader().getResourceAsStream(path).readAllBytes();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
  }


  /**
   * Makes java.nio.file.Path from String.
   * @deprecated Scheduled for removal in 2.0.0
   * @param path String path to file.
   * @return java.nio.file.Path object.
   */
  @Deprecated(since = "1.0.0", forRemoval = true)
  public static Path makePath(String path) {
    return Path.of(path);
  }

  /**
   * Writes String to file.
   * @deprecated Scheduled for removal in 2.0.0
   * @param path Path to file you want to write into.
   * @param source String to write in file.
   */
  @Deprecated(since = "1.0.0", forRemoval = true)
  public static void write(String path, String source) {
    try {
      Path file = Path.of(path);
      Files.writeString(file, source);
    } catch (IOException e) {
      logger.warning("Error in NIO while writing: " + e);
    }
  }


  /**
   * Writes bytes from array to file.
   * @param path Path to file you want to write into.
   * @param bytes Data array to write.
   */
  public static void write(String path, byte[] bytes) {
    try {
      Path file = Path.of(path);
      if (file.getParent() != null) Files.createDirectories(file.getParent());
      
      Files.write(file, bytes);
    } catch (IOException e) {
      logger.warning("Error in NIO while writing: " + e);
    }
  }


  /**
   * Loads GLFW image from image on disk.
   * @param path Path to image file.
   * @return image as GLFWImage.
   */
  public static GLFWImage loadGlfwImage(String path) {
    GLFWImage image;
    ByteBuffer bytes;
    try (MemoryStack stack = MemoryStack.stackPush()) {

      // Loads and pushes icon image data to buffer.
        bytes = ByteBuffer.wrap(load(path));
      
      // Puts image data into IntBuffers
      IntBuffer w = stack.mallocInt(1);
      IntBuffer h = stack.mallocInt(1);
      IntBuffer comp = stack.mallocInt(1);


      ByteBuffer pixels = STBImage.stbi_load_from_memory(bytes, w, h, comp, 4);

      if (pixels == null) {
        throw new RuntimeException("Unable to load icon: " + STBImage.stbi_failure_reason());
      }

      image = GLFWImage.malloc().set(w.get(0), h.get(0), pixels);

      STBImage.stbi_image_free(pixels);
    }
      return image;
  }
}
