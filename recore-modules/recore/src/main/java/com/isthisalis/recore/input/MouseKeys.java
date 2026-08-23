package com.isthisalis.recore.input;

public enum MouseKeys {
    MOUSE_1(0),
    MOUSE_2(1),
    MOUSE_3(2),
    MOUSE_4(3),
    MOUSE_5(4),
    MOUSE_6(5),
    MOUSE_7(6),
    MOUSE_8(7);

    private final int code;

    MouseKeys(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}