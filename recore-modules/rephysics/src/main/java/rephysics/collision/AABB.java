package rephysics.collision;

import org.joml.Vector2f;

import rephysics.math.VectorUtil;

import recore.graphics.render.Mesh;

public class AABB {

     /**
     * Lower left vertex of bounding box.
     */
    private Vector2f min;

    /**
     * Top right vertex of bounding box.
     */
    private Vector2f max;

    /**
     * 
     */ 
    private static VectorUtil vecutil = new VectorUtil(); 

    /**
     * Constructor to generate an AABB given a minimum and maximum bound in the form of two vectors.
     *
     * @param min Lower bound of AABB vertex.
     * @param max Higher bound of AABB vertex.
     */
    public AABB(Vector2f min, Vector2f max) {
        vecutil.copy(this.min, min);
        vecutil.copy(this.max, max);
    }

    /**
     * Default constructor generating an AABB with (0,0) upper and lower bounds.
     */
    public AABB() {
        this.min = new Vector2f();
        this.max = new Vector2f();
    }

    /**
     * Sets the current objects bounds equal to that of the passed AABB argument.
     *
     * @param aabb An AABB bounding box.
     */
    public final void set(final AABB aabb) {
        Vector2f v = aabb.min;
        min.x = v.x;
        min.y = v.y;
        Vector2f v1 = aabb.max;
        max.x = v1.x;
        max.y = v1.y;
    }

    /**
     * Getter for min variable for lower bound vertex.
     *
     * @return AABB min
     */
    public Vector2f getMin() {
        return min;
    }

    /**
     * Getter for max variable for upper bound vertex.
     *
     * @return AABB max
     */
    public Vector2f getMax() {
        return max;
    }

    /**
     * Method to check if an AABB is valid.
     * Makes sure the bounding volume is not; a point, has order of vertex's backwards and valid values have been used for the bounds.
     *
     * @return boolean value of the validity of the AABB.
     */
    public final boolean isValid() {
        if (max.x - min.x < 0) {
            return false;
        }
        if (max.y - min.y < 0) {
            return false;
        }
        return true;
    }

    /**
     * Method to check if a point resides inside an AABB in object space.
     *
     * @param point A point to check if its inside the AABB's object space. Point needs to also be in object space.
     * @return Boolean value whether or not the point lies inside the AABB bounds.
     */
    public boolean AABBOverLap(Vector2f point) {
        double x = point.x;
        double y = point.y;
        return x <= this.getMax().x && x >= this.getMin().x && y >= this.getMax().y && y <= this.getMin().y;
    }

    /**
     * Method to add offset to the AABB's bounds. Can be useful to convert from object to world space .
     *
     * @param offset A vector to apply to the min and max vectors to translate the bounds and therefore AABB to desired position.
     */
    public void addOffset(Vector2f offset) {
        this.min.add(offset);
        this.max.add(offset);
    }

    @Override
    public final String toString() {
        return "AABB[" + min + " . " + max + "]";
    }

    /**
     * Copy method to return a new AABB that's the same as the current object.
     *
     * @return New AABB that's the same as the current object.
     */
    public AABB copy() {
        return new AABB(this.min, this.max);
    }

    /**
     * Checks whether two body's AABB's overlap in world space.
     *
     * @param A First body to evaluate.
     * @param B Second body to evaluate.
     * @return Boolean value of whether the two bodies AABB's overlap in world space.
     */
    public static boolean AABBOverLap(AABB A, AABB B, Mesh a, Mesh b) {
        AABB aCopy = A.copy();
        AABB bCopy = B.copy();

        aCopy.addOffset(vecutil.to2f(a.getPosition()));
        bCopy.addOffset(vecutil.to2f(b.getPosition()));

        return AABB.AABBOverLap(aCopy, bCopy);
    }

    /**
     * Method to check if two AABB's overlap. Can be seen as world space.
     *
     * @param a First AABB to evaluate.
     * @param b Second AABB to evaluate.
     * @return Boolean value of whether two bounds of the AABB's overlap.
     */
    public static boolean AABBOverLap(AABB a, AABB b) {
        return a.min.x <= b.max.x &&
                a.max.x >= b.min.x &&
                a.min.y <= b.max.y &&
                a.max.y >= b.min.y;
    }
}
