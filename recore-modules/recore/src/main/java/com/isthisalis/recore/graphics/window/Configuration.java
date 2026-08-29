package com.isthisalis.recore.graphics.window;

import lombok.Builder;
import lombok.Value;
import lombok.Builder.Default;

/**
 * Window configuration data-class.
 * @see {@link com.isthisalis.recore.graphics.window.Window}
 */
@Value
@Builder
public class Configuration {

    /**
     * Window size.
     */
    int width, height;

    /**
     * Window title.
     */
    String title;

    /**
     * VSync on/off value.
     */
    @Default boolean vsync = true;

    /**
     * FPS limit.
     */
    @Default int fpsLimit = 30;
}
