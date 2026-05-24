package rexample.interaction;

import recore.core.ComponentLogic;

import rephysics.collision.AABB;

import rexample.resources.Resources;

public class Collizion implements ComponentLogic {
    private AABB banana = new AABB();
    private AABB player = new AABB();

    private static boolean playerState = false;

    @Override // recore.core.ComponentLogic
    public void init() {
        this.banana.setMin(0.5f, -1.0f);
        this.banana.setMax(1.0f, -0.5f);

        this.player.setMin(0.5f, -1f);
        this.player.setMax(1f, -0.5f);
    }

    @Override // recore.core.ComponentLogic
    public void update() {
        playerState = AABB.AABBOverLap(this.banana, this.player, Resources.getScene().getObj(0), Resources.getScene().getObj(3));
    }

    @Override // recore.core.ComponentLogic
    public void cleanup() {
        this.banana = null;
        this.player = null;
    }

    public static boolean getPlayerState() {
        return playerState;
    }
}
