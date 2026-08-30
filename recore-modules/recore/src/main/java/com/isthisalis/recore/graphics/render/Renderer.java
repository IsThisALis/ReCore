package com.isthisalis.recore.graphics.render;

import com.isthisalis.recore.graphics.camera.Camera;
import com.isthisalis.recore.graphics.camera.FOV;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;

/**
 * Render pre-built pipeline.
 */
public class Renderer {

  /**
   * FOV util class.
   */
  private static FOV fov = new FOV();

  /**
   * Renders object if in FOV. (Frustrum Culling)
   * @param entity Entity to draw.
   * @param camera Camera to display rendered.
   * @return Rendered or not state.
   */
  public static boolean render(Entity entity, Camera camera) {
    if(fov.inFov(entity, camera)) {

      entity.getMesh().bind();
      entity.getTransform().bind();
      entity.getMaterial().bind();
      
      entity.getMesh().draw();

      camera.update();
      return true;
    } else { return false; }
  }

  /**
   * Operates with blending state, useful when need to render objects with empty pixels in texture.
   * @param on Blend state.
   */
  public static void blend(boolean state) {
      if(state) {
          glEnable(GL_BLEND);
          glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
      }

      if(!state){
          glDisable(GL_BLEND);
      }
    }
}
