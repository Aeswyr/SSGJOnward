package entities;

import java.awt.event.KeyEvent;

import core.Assets;
import entity.Entity;
import entity.Hitbox;
import entity.Vector;
import gfx.DrawGraphics;
import gfx.Sprite;
import input.Controller;
import runtime.Handler;
import utility.Utility;

public class Player extends Entity {

	Sprite activeSprite;

	public Player() {
		activeSprite = Assets.getSprite("idle");

		this.hitbox = new Hitbox(4, 4, new int[][] { { 0, 0 }, { 16, 0 }, { 16, 18 }, { 0, 18 } }, this);
		this.hitbox.setCollisionType(Hitbox.COLLISION_ENTITY);
		this.vector = new Vector(this, 0);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1176432062650406680L;

	@Override
	public void render(DrawGraphics g) {
		activeSprite.render((int) (x - Handler.getCamera().xOffset()), (int) (y - Handler.getCamera().yOffset()), g);
	}

	@Override
	public void update() {
		controls();
	}

	private void controls() {

		boolean sh = false;
		double sx = 1.5, sy = -4;
		if (Controller.getKeyPressed((char) (KeyEvent.VK_SHIFT)))
			sh = true;

		if (Controller.getKeyPressed('a') || Controller.getKeyPressed('d')) {
			if (Controller.getKeyPressed('a')) {
				if (sh)
					sx *= 2;

				if (vector.Vy() != 0) {
					vector.adjAccelX(-sx / 4.0);
				} else {
					vector.setVelocityX(-sx);
				}
			}

			if (Controller.getKeyPressed('d')) {
				if (sh)
					sx *= 2;

				if (vector.Vy() != 0) {
					vector.adjAccelX(sx / 4.0);
					if (Math.abs(vector.Vx()) > 8 && Utility.getSign(vector.Vx()) == Utility.getSign(vector.Ax())) {
						vector.setAccelX(0);
					}
				} else { 
					vector.setVelocityX(sx);
				}
			}
		} else {
			vector.setVelocityX(vector.Vx() / 1.25);
		}

		if (Controller.getKeyPressed((char) (KeyEvent.VK_SPACE)) && vector.Vy() == 0) {
			if (sh) {
				sy = (int) (sy / 1.25);
			}
			vector.setVelocityY(sy);
		}

	}

}
