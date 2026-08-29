package com.isthisalis.recore.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * GLFW mouse buttons keys storage.
 */
@RequiredArgsConstructor
public enum MouseKeys {
    MOUSE_1(0),
    MOUSE_2(1),
    MOUSE_3(2),
    MOUSE_4(3),
    MOUSE_5(4),
    MOUSE_6(5),
    MOUSE_7(6),
    MOUSE_8(7);

    /**
     * GLFW mouse button key.
     */
    private final @Getter int code;
}