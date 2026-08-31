package com.isthisalis.recore.graphics.window;

import lombok.Builder;
import lombok.Value;
import lombok.Builder.Default;

/**
 * Window configuration data-class.
 */
@Value
@Builder
public class Configuration {

    /**
     * Window width in pixels.
     */
    @Default int width = 360;

    /**
     * Window height in pixels. 
     */
    @Default int height = 240;

    /**
     * Window title.
     */
    @Default String title = "ReCore";

    /**
     * VSync on/off value.
     */
    @Default boolean vsync = true;

    /**
     * FPS limit.
     */
    @Default int fpsLimit = 30;

    /**
     * Window appearance.
     */
    @Default WindowMode windowMode = WindowMode.FULLSCREEN;
}
