package com.isthisalis.recore.graphics.shaders;

import com.isthisalis.recore.util.NIO;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL41.*;

import org.lwjgl.system.MemoryStack;

/**
 * ShaderCache
 */
public class ShaderCache {


  public static boolean writeShaderCache(ShaderProgram program) {
    try (MemoryStack stack = MemoryStack.stackPush()) {
      IntBuffer len = stack.mallocInt(1);
      glGetProgramiv(program.getId(), GL_PROGRAM_BINARY_LENGTH, len);

      if (len.get(0) == 0) throw new RuntimeException("No shader cache found in program");

      ByteBuffer bin = stack.malloc(len.get(0));
      IntBuffer format = stack.mallocInt(1);

      glGetProgramBinary(program.getId(), (IntBuffer) null, format, bin);

      ByteBuffer data = ByteBuffer.allocateDirect(4 + bin.remaining());
      data.putInt(format.get(0));
      data.put(bin.duplicate());
      data.flip();
      
      NIO.write(NIO.makePath(program.getCachePath()), data);
      return true;
    } catch (Exception e) {
        System.out.println("ReCore: Error in writing shader cache " + e.getMessage());
        return false;
    }
  }


  public static boolean loadCache(ShaderProgram program) {
    ByteBuffer data = NIO.loadByteBuffer(NIO.makePath(program.getCachePath()));
    if (data == null || data.remaining() < 4 ) return false;

    try (MemoryStack stack = MemoryStack.stackPush()) {
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

}
