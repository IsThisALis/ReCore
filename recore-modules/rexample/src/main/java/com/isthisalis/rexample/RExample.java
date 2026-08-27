package com.isthisalis.rexample;

/**
 * RExample module main class.
 */
public class RExample {

    /**
     * creates and starts application.
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.init();
        app.loop();
    }
}
