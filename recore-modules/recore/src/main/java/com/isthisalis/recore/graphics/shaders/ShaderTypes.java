package com.isthisalis.recore.graphics.shaders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * OpenGL shader types supported by ReCore.
 */
@RequiredArgsConstructor
public enum ShaderTypes {
    FRAGMENT(35632),
    VERTEX(35633);

    /**
     * OpenGL constant value.
     */
    private final @Getter int code;
}