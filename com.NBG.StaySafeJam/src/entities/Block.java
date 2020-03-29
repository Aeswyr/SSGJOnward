package entities;

import entity.Entity;
import entity.Hitbox;
import gfx.DrawGraphics;
import gfx.Sprite;
import runtime.Handler;

public class Block extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2063867202174843992L;
	int height, width;
	Sprite tile;

	public Block(int width, int height, Sprite tile) {
		this.width = width;
		this.height = height;
		this.tile = tile;
		
		this.hitbox = new Hitbox(0, 0, new int[][] {{0, 0}, {16 * width, 0}, {16 * width, 16 * height}, {0, 16 * height}}, this);
		hitbox.setCollisionType(Hitbox.COLLISION_ENVIRONMENT);
		
	}

	@Override
	public void update() {
		if (this.x + width * 16 + 128 < Handler.getCamera().xOffset())
			Handler.getEntityManager().removeEntity(this);
	}

	@Override
	public void render(DrawGraphics g) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tile.render((int) (x + 16 * j - Handler.getCamera().xOffset()), (int) (y + 16 * i - Handler.getCamera().yOffset()),
						g);
			}
		}

	}

}
