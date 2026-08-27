package com.isthisalis.recore.util;

public class OS {

  private static final String OS = System.getProperty("os.name").toLowerCase();

  public static boolean isWindows() {
    return OS.contains("win");
  }

  public static boolean isMac() {
    return OS.contains("mac");
  }

  public static boolean isLinux() {
    return OS.contains("linux");
  }
}
