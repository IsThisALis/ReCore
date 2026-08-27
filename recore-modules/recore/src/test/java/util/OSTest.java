package util;

import org.junit.jupiter.api.Test;

import com.isthisalis.recore.util.OS;

public class OSTest {
 
    @Test
    void createOS() {
        OS os = new OS();
    }

    @Test
    void getOs() {
        System.out.println("Linux: " + OS.isLinux());
        System.out.println("Windows: " + OS.isWindows());
        System.out.println("Mac: " + OS.isMac());
    }
}
