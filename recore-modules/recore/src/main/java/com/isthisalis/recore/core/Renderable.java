package com.isthisalis.recore.core;

import com.isthisalis.recore.graphics.render.Entity;

import lombok.Getter;

/**
 * Renderable object example.
 * @apiNote WiP class.
 */
public abstract class Renderable {

    /**
     * Object entity, storing all needed data (Material, Transform, Mesh).
     */
    private @Getter Entity entity;
}