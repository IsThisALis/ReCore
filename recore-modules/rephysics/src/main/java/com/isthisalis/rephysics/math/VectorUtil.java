package com.isthisalis.rephysics.math;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class VectorUtil {


    /**
     * Pre-initialized Vectors to optimize memory usage in operations.
     */
    private static Vector2f v2f = new Vector2f();
    private static Vector2f scalar = new Vector2f();


    /**
     * Copies values from source vector to target vector
     * @param target Target vector
     * @param source Source vector
     */
    public static void copy(Vector2f target, Vector2f source) {
        target.x = source.x;
        target.y = source.y;
    }


    /**
     * Copies values from Vector3f to Vector2f
     * @param source Source vector
     * @return New vector with values from source
     */
    public static Vector2f to2f(Vector3f source) {
        v2f.x = source.x;
        v2f.y = source.y;

        return v2f;
    }


    /**
     * Adds values from Vector3f to Vector2f
     * @param target Target vector
     * @param source Source vector
     */
    public static void add(Vector2f target, Vector3f source) {
        target.x += source.x;
        target.y += source.y;
    }


    /**
     * Adds values from Vector2f to Vector3f
     * @param target Target vector
     * @param source Source vector
     */
    public static void add(Vector3f target, Vector2f source) {
        target.x += source.x;
        target.y += source.y;
    }


    public static float crossProduct(Vector2f v1, Vector2f v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }


    public static Vector2f scalar(Vector2f target, float a) {
        return scalar.set(target.x * a, target.y * a);
    }
}
