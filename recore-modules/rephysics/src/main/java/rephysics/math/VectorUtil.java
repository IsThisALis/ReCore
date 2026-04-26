package rephysics.math;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class VectorUtil {

  /** 
   * Copies values from source vector to target vector
   *
   * @param target Target vector 
   * @param source Source vector
   */
  public void copy(Vector2f target, Vector2f source) {
    target.x = source.x;
    target.y = source.y;
  }

  /** 
   * Copies values from Vector3f to Vector2f
   *
   * @param source Source vector
   *
   * @return New vector with values from source
   */
  public Vector2f to2f(Vector3f source) {
    return new Vector2f(source.x, source.y);
  }

  /** 
   * Adds values from Vector3f to Vector2f
   *
   * @param target Target vector 
   * @param source Source vector
   */
  public void add(Vector2f target, Vector3f source) {
    target.x += source.x;
    target.y += source.y;
  }

  /** 
   * Adds values from Vector2f to Vector3f
   *
   * @param target Target vector 
   * @param source Source vector
   */
  public void add(Vector3f target, Vector2f source) {
    target.x += source.x;
    target.y += source.y;
  }
}
