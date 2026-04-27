package rexample.interaction;

import recore.core.ComponentLogic;

import rephysics.collision.AABB;

import rexample.resources.Resources;

public class Collizion implements ComponentLogic {

  AABB banana;
  AABB player;
  private static boolean playerCollided = false;

  @Override
  public void init() {
    banana = new AABB();
    banana.setMin(0.5f, -1.0f);
    banana.setMax(1.0f, -0.5f);

    player = new AABB();
    player.setMin(-0.25f, -0.25f);
    player.setMax(0.25f, 0.25f);
  }

  @Override
  public void update() {
    playerCollided = AABB.AABBOverLap(banana, player, Resources.getScene().getObj(0), Resources.getScene().getObj(3));
  }

  @Override
  public void cleanup() {
    //TODO: cleanup
  }

  public static boolean getPlayerState() {
    return playerCollided;
  }
}
