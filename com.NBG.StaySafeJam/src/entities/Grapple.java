package entities;

import java.util.List;

import entity.Entity;
import entity.Hitbox;
import entity.Vector;
import gfx.DrawGraphics;
import input.Controller;
import runtime.Handler;

public class Grapple extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2033561959619488344L;

	Entity source;

	public Grapple(Entity e) {
		this.hitbox = new Hitbox(0, 0, new int[][] { { 0, 0 }, { 4, 0 }, { 4, 4 }, { 0, 4 } }, this);
		hitbox.setCollisionType(Hitbox.COLLISION_NONE);
		this.vector = new Vector(this, 0);
		
		int delx = source.getX() - Controller.getAdjX() - Handler.getCamera().xOffsetAdj();
		int dely = source.getY() - Controller.getAdjY() - Handler.getCamera().yOffsetAdj();
		
		double cos = 1.0 * delx * delx / (delx * delx* + dely * dely);
		double sin = 1.0 * dely * dely / (delx * delx* + dely * dely);
		
		source = e;
	}

	@Override
	public void update() {
		List<Entity> loc = hitbox.localCollisions();
		for (Entity e : loc) {
			if (e != this) {
				if (e.hasHitbox() && e instanceof Platform || e instanceof Block)
					vector = null;
				// TODO grapple to solid surfaces
				else if (e instanceof Player && e != source)
					vector = null;
				// TODO launch players towards each other, with the latched player being
				// launched with more force. If the source player is grounded, only pulls the
				// latched player
			}
		}

	}

	@Override
	public void render(DrawGraphics g) {
		g.drawLine(source.getX() - Handler.getCamera().xOffset(), source.getY() - Handler.getCamera().yOffset(),
				(int) x - Handler.getCamera().xOffset(), (int) y - Handler.getCamera().yOffset(), 0xAAAAAA);

	}

}
