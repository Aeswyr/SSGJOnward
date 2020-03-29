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

	Entity source, attach;
	double xspe, yspe;

	public Grapple(Entity e) {
		this.hitbox = new Hitbox(0, 0, new int[][] { { 0, 0 }, { 4, 0 }, { 4, 4 }, { 0, 4 } }, this);
		hitbox.setCollisionType(Hitbox.COLLISION_NONE);
		this.vector = new Vector(this, 0);

		source = e;

		double delx = Controller.getAdjX() + Handler.getCamera().xOffset() - source.getX();
		double dely = Controller.getAdjY() + Handler.getCamera().yOffset() - source.getY();

		double cos = 1.0 * delx / Math.sqrt(delx * delx + dely * dely);
		double sin = 1.0 * dely / Math.sqrt(delx * delx + dely * dely);

		double speed = 12;

		xspe = cos * speed;
		yspe = sin * speed;

	}

	public boolean enviro = false, entity = false;
	public boolean grappled = false;

	@Override
	public void update() {
		if (vector != null) {
			vector.setAccelY(-9.8);
			vector.setVelocityX(xspe);
			vector.setVelocityY(yspe);

			List<Entity> loc = hitbox.localCollisions();
			for (Entity e : loc) {
				if (e != this && e != source) {
					if (e.hasHitbox() && e instanceof Platform || e instanceof Block) {
						vector = null;

						grappled = true;
						enviro = true;

					} else if (e.isMob()) {
						vector = null;

						grappled = true;
						entity = true;

						attach = e;

						double dx = source.getX() - attach.getX();
						double dy = source.getY() - attach.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);
						double cos = dx / dist;
						double sin = dy / dist;

						attach.getVector().setVelocityX(8 * cos);
						attach.getVector().setVelocityY(8 * sin);

						if (source.getVector().Vy() != 0) {
							source.getVector().setVelocityX(-3 * cos);
							source.getVector().setVelocityY(-3 * sin);
						}
					}
				}
			}
		}

		if (enviro) {
			int snapDist = 96;
			int x0 = (int) x - source.getX();
			int y0 = (int) y - source.getY();
			double dist = x0 * x0 + y0 * y0;
			if (dist > snapDist * snapDist) {
				dist = Math.sqrt(dist);
				Vector ve = source.getVector();
				double k = 0.25;
				double ax = k * Math.abs(dist - snapDist) * x0 / dist;
				double ay = k * Math.abs(dist - snapDist) * y0 / dist;
				ve.adjAccelX(ax);
				ve.adjAccelY(ay);
			}
		} else if (entity) {
			this.x = attach.getX() - 12;
			this.y = attach.getY() - 12;
		}
	}

	@Override
	public void render(DrawGraphics g) {
		g.drawLine(source.getX() - Handler.getCamera().xOffset() + 12,
				source.getY() - Handler.getCamera().yOffset() + 12, (int) x - Handler.getCamera().xOffset(),
				(int) y - Handler.getCamera().yOffset(), 0xFFFFFFFF, 9);

	}

}
