package rexample.interaction;

import recore.core.ComponentLogic;

import rephysics.collision.AABB;

import rexample.resources.Resources;

public class Collizion implements ComponentLogic {
    private AABB banana = new AABB();
    private AABB player = new AABB();

    private static boolean playerState = false;

      @Override
    public void init() {
        banana.setMin(0.5f, -1.0f);
        banana.setMax(1.0f, -0.5f);

        player.setMin(0.5f, -1f);
        player.setMax(1f, -0.5f);
    }

      @Override
    public void update() {
        playerState = AABB.AABBOverLap(banana, player, Resources.getScene().getObj(0), Resources.getScene().getObj(3));
    }

      @Override
    public void cleanup() {
        banana = null;
        player = null;
    }

    public static boolean getPlayerState() {
        return playerState;
    }
}
