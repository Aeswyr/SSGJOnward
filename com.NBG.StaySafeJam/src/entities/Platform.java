package entities;

import entity.Entity;
import entity.Hitbox;
import gfx.DrawGraphics;
import gfx.Sprite;
import runtime.Handler;

public class Platform extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3674638256465273811L;
	int length = 0;
	Sprite tile;

	public Platform(int length, Sprite tile) {
		this.length = length;
		this.tile = tile;

		this.hitbox = new Hitbox(0, 0, new int[][] { { 0, 0 }, { 16 * length, 0 }, { 16 * length, 16 }, { 0, 16 } },
				this);
		hitbox.setCollisionType(Hitbox.COLLISION_ENVIRONMENT);
	}

	@Override
	public void render(DrawGraphics g) {
		for (int i = 0; i < length; i++) {
			tile.render((int) (x + 16 * i - Handler.getCamera().xOffset()), (int) (y - Handler.getCamera().yOffset()),
					g);
		}

	}

	@Override
	public void update() {
		if (this.x + length * 16 + 128 < Handler.getCamera().xOffset() ) Handler.getEntityManager().removeEntity(this);
	}

}
