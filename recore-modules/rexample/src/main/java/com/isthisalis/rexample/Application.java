package com.isthisalis.rexample;

import com.isthisalis.recore.core.Engine;
import com.isthisalis.recore.graphics.window.Configuration;
import com.isthisalis.recore.graphics.window.Window;

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
            .width(1920)
            .height(1080)
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
        getWindow().update();
    }
    
    /**
     * Cleans all app-specified data. Always called in the end of {@link com.isthisalis.recore.core.Engine#loop()}.
     */
    public void cleanup() {}
}
