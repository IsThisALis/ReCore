package com.isthisalis.recore.util;

import java.nio.file.Path;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.io.IOException;

/**
 * New IO implementation made to work with external file system.
 */
public class NIO {

  private static Logging log = new Logging(NIO.class.getName());


  public static Path makePath(String path) {
    return Path.of(path);
  }


   public static void write(Path path, String source) {
    try {
      Files.writeString(path, source);
    } catch (IOException e) {
      log.error("Error in NIO write ", e);
    }
  }


  public static void write(Path path, ByteBuffer bytes) {
    try {
      if (path.getParent() != null) Files.createDirectories(path.getParent());

      byte[] arr = new byte[bytes.remaining()];
      bytes.duplicate().get(arr);
      
      Files.write(path, arr);
    } catch (IOException e) {
      log.error("Error in NIO write ", e);
    }
  }


  public static String load(Path path) {
    try {
      return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    } catch (IOException e) {
      log.error("in NIO while loading file ", e);
      return null;
    }
  }


  public static ByteBuffer loadByteBuffer(Path path) {
     try {
      byte[] bytes;
      if (Files.exists(path)) bytes = Files.readAllBytes(path);
      else bytes = NIO.class.getClassLoader().getResourceAsStream(path.toString()).readAllBytes();

      ByteBuffer bytedata = ByteBuffer.allocateDirect(bytes.length);

      bytedata.put(bytes);
      bytedata.flip();

      return bytedata;
     } catch (IOException e) {
        log.error("Error in loadByteBuffer(): ", e);
        return ByteBuffer.allocate(1);
     }
  }


  public static GLFWImage loadGlfwImage(String path) {

      MemoryStack stack = MemoryStack.stackPush();

      // Loads and pushes icon image data to buffer.
      ByteBuffer bytes = loadByteBuffer(Path.of(path));
      
      // Puts image data into IntBuffers
      IntBuffer w = stack.mallocInt(1);
      IntBuffer h = stack.mallocInt(1);
      IntBuffer comp = stack.mallocInt(1);


      ByteBuffer pixels = STBImage.stbi_load_from_memory(bytes, w, h, comp, 4);

      if (pixels == null) {
        throw new RuntimeException("Unable to load icon: " + STBImage.stbi_failure_reason());
      }

      GLFWImage image = GLFWImage.malloc().set(w.get(0), h.get(0), pixels);

      STBImage.stbi_image_free(pixels);
      stack.close();

      return image;
  }
}
