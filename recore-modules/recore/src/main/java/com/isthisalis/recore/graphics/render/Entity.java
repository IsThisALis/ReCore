package com.isthisalis.recore.graphics.render;

import com.isthisalis.recore.graphics.material.Material;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Entity object class.
 */
@Data
@Builder
public class Entity {
    
    /**
     * Entity geometry object.
     */
    private @NonNull Mesh mesh;

    /**
     * Entity world data object.
     */
    private @NonNull Transform transform;

    /**
     * Entity visual data.
     */
    private @NonNull Material material;
}
