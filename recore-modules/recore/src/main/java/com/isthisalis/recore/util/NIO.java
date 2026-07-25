package com.isthisalis.recore.util;

import java.nio.file.Path;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.io.IOException;

/**
 * New IO implementation made to work with external file system.
 */
public class NIO {

  private static Logging log = new Logging("ReCore");


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
      byte[] bytes = Files.readAllBytes(path);
      ByteBuffer bytedata = ByteBuffer.allocateDirect(bytes.length);

      bytedata.put(bytes);
      bytedata.flip();

      return bytedata;
     } catch (IOException e) {
        log.error("Error in loadByteBuffer(): ", e);
        return null;
     }
  }


  public static boolean isDir(Path path) {
    return Files.isDirectory(path);
  }


  public static boolean isFile(Path path) {
    return Files.isRegularFile(path);
  }
}
