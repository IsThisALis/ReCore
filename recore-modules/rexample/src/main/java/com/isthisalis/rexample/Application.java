package com.isthisalis.rexample;

import com.isthisalis.recore.core.Engine;
import com.isthisalis.recore.graphics.window.Configuration;
import com.isthisalis.recore.graphics.window.Window;
import com.isthisalis.recore.graphics.window.WindowMode;

/**
 * Application class. Engine implementation.
 * @see {@link com.isthisalis.recore.core.Engine}.
 */
public class Application extends Engine {

    /**
     * Initializes application. Sets window for Engine.
     */
    public void init() {
        Window window = new Window();
        Configuration config = Configuration.builder()
            .windowMode(WindowMode.FULLSCREEN)
            .title("RExample")
            .vsync(true)
            .build();

        window.init(config, this);
        setWindow(window);
    }


    /**
     * Calls all updates. Used in {@link com.isthisalis.recore.core.Engine#loop()}.
     */
    public void update() {
        // Some update logic
    }
    
    /**
     * Cleans all app-specified data. Always called in the end of {@link com.isthisalis.recore.core.Engine#loop()}.
     */
    public void cleanup() {
        // Some cleanup logic
    }
}
