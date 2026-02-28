package org.gfs.recore.core;

public class osProcessor {

  private static final String os = System.getProperty("os.name").toLowerCase();

  public static boolean isWindows() {
    return os.contains("win");
  }

  public static boolean isMac() {
    return os.contains("mac");
  }

  public static boolean isLinux() {
    return os.contains("linux");
  }
}
