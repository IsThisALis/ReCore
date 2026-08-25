package com.isthisalis.recore.graphics.render;

import com.isthisalis.recore.graphics.material.Material;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Entity {
    
    private @NonNull Mesh mesh;
    private @NonNull Transform transform;
    private @NonNull Material material;
}
