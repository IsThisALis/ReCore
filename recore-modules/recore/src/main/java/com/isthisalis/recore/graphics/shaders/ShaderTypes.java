package com.isthisalis.recore.graphics.shaders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ShaderTypes {
    FRAGMENT(35632),
    VERTEX(35633);

    private final @Getter int code;
}