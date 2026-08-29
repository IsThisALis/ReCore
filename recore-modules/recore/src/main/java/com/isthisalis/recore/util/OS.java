package com.isthisalis.recore.util;

/**
 * Util class to set OS-based tweaks.
 */
public class OS {

  /**
   * OS name in String.
   */
  private static final String OS = System.getProperty("os.name").toLowerCase();

  /**
   * Checks if OS == Windows.
   */
  public static boolean isWindows() {
    return OS.contains("win");
  }

  /**
   * Checks if OS == Mac.
   */
  public static boolean isMac() {
    return OS.contains("mac");
  }

  /**
   * Checks if OS == Linux.
   */
  public static boolean isLinux() {
    return OS.contains("linux");
  }
}
