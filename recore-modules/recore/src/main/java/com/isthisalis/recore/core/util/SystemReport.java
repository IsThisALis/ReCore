package com.isthisalis.recore.core.util;

import java.lang.management.ManagementFactory;

import org.lwjgl.Version;

/**
 * Class logging system properties like  OS, GPU.
 */
public class SystemReport {
    
    private static final long MB = 1024L * 1024L;

    public static String collect() {
        Runtime rnt = Runtime.getRuntime();

        StringBuilder sb = new StringBuilder();

        sb.append("===  System info  ===");
        sb.append("OS: ")
            .append(prop("os.name")).append(" ")
            .append(prop("os.version")).append(" ")
            .append(prop("os.arch")).append(" ")
            .append("\n");
        sb.append("CPU arch: ")
        .append(prop("os.arch"))
        .append("\n");

        sb.append("CPU cores: ")
          .append(rnt.availableProcessors())
          .append("\n");

        sb.append("Java: ")
          .append(prop("java.version"))
          .append(" (")
          .append(prop("java.vendor"))
          .append(")\n");

        sb.append("Java runtime: ")
          .append(prop("java.runtime.version"))
          .append("\n");

        sb.append("VM: ")
          .append(prop("java.vm.name"))
          .append(" ")
          .append(prop("java.vm.version"))
          .append("\n");

        sb.append("LWJGL: ")
          .append(Version.getVersion())
          .append("\n");

        sb.append("Max heap: ")
          .append(rnt.maxMemory() / MB)
          .append(" MB\n");

        sb.append("JVM args: ")
          .append(ManagementFactory.getRuntimeMXBean().getInputArguments())
          .append("\n");

        var osBean = ManagementFactory.getOperatingSystemMXBean();

        sb.append("System load average: ")
          .append(osBean.getSystemLoadAverage())
          .append("\n");

        if (osBean instanceof com.sun.management.OperatingSystemMXBean sunOs) {
            sb.append("Total physical memory: ")
              .append(sunOs.getTotalMemorySize() / MB)
              .append(" MB\n");

            sb.append("Free physical memory: ")
              .append(sunOs.getFreeMemorySize() / MB)
              .append(" MB\n");
        }

        return sb.toString();
    }

    private static String prop(String key) {
        return System.getProperty(key, "unknown");
    }
}