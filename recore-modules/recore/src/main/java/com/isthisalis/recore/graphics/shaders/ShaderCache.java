package com.isthisalis.recore.graphics.shaders;

import com.isthisalis.recore.util.NIO;

import lombok.NonNull;

import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.glGetProgramiv;
import static org.lwjgl.opengl.GL41.*;

import org.lwjgl.system.MemoryStack;

/**
 * Shader program caching mechanism.
 * @apiNote Cache dont cleans automaticaully.
 */
public class ShaderCache {

  /**
   * Writes linked shader program on disk.
   * @param program ShaderProgram to write cache for.
   * @return Cache save status.
   */
  protected static boolean writeShaderCache(ShaderProgram program) {
    try (MemoryStack stack = MemoryStack.stackPush()) {
      IntBuffer len = stack.mallocInt(1);
      glGetProgramiv(program.getId(), GL_PROGRAM_BINARY_LENGTH, len);

      if (len.get(0) == 0) throw new RuntimeException("No shader data found in program");

      ByteBuffer bin = stack.malloc(len.get(0));
      IntBuffer format = stack.mallocInt(1);

      glGetProgramBinary(program.getId(), (IntBuffer) null, format, bin);

      ByteBuffer data = ByteBuffer.allocateDirect(4 + bin.remaining());
      data.putInt(format.get(0));
      data.put(bin.duplicate());
      data.flip();
      
      NIO.write(program.getCachePath() + ".bin", data.array());
      return true;
    } catch (Exception e) {
        System.out.println("ReCore: Error in writing shader cache " + e.getMessage());
        return false;
    }
  }

  /**
   * Loads linked shader program from cache.
   * @param program Program to laad cache for.
   * @return Cache loading status.
   */
  protected static boolean loadCache(ShaderProgram program) {
    try (MemoryStack stack = MemoryStack.stackPush()) {
      ByteBuffer data = ByteBuffer.wrap(NIO.load(program.getCachePath() + ".bin"));
      if (data == null || data.remaining() < 4 ) return false;

      int format = data.getInt();
      ByteBuffer bin = ByteBuffer.allocateDirect(data.remaining());
      bin.put(data);
      bin.flip();

      glProgramBinary(program.getId(), format, bin);

      IntBuffer status = stack.mallocInt(1);
      glGetProgramiv(program.getId(), GL_LINK_STATUS, status);
      return status.get(0) == GL_TRUE;

    } catch (Exception e) {
      System.out.println("ReCore: Error in loading cache: " + e);
      return false;
    }
  }

  /**
   * Makes SHA-256 hashcode from String array.
   * @param strings Source to make hash.
   * @return SHA-256 hashcode.
   */
  public static String hash(@NonNull String... strings) {
    try {
      MessageDigest dgst = MessageDigest.getInstance("SHA-256");

      for (String string : strings) {
        if (string != null) {
          dgst.update(string.getBytes(StandardCharsets.UTF_8));
        }
        dgst.update((byte) 0);
      }

      return HexFormat.of().formatHex(dgst.digest());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}