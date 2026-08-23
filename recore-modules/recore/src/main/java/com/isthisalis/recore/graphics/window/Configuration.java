package com.isthisalis.recore.graphics.window;

import lombok.Builder;
import lombok.Value;
import lombok.Builder.Default;

@Value
@Builder
public class Configuration {
    int width, height;
    String title;
    @Default boolean vsync = true;
    @Default int fpsLimit = 30;
}
