package com.isthisalis.recore.graphics.render.scene;

import java.util.ArrayList;
import java.util.List;

import com.isthisalis.recore.graphics.camera.Camera;
import com.isthisalis.recore.graphics.render.Entity;
import com.isthisalis.recore.graphics.render.Renderer;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Scene WiP pre-built.
 */
@RequiredArgsConstructor
public class Scene {

  private @Getter List<Entity> entities = new ArrayList<>();
  private @NonNull Camera camera;

  public void render() {
    for (Entity entity : entities) {
      Renderer.render(entity, camera);
    }
  }
}