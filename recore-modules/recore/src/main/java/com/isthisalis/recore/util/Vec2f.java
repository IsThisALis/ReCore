package com.isthisalis.recore.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
/**
 * Simple data class to store 2 position values.
*/
public class Vec2f {
    
    /**
     * X position value.
     */
    private @Setter float x;

    /**
     * Y position value.
     */
    private @Setter float y;
}
