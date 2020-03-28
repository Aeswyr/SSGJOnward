package scenes;

import core.Assets;
import entities.Platform;
import entities.Player;
import entity.Vector;
import gfx.DrawGraphics;
import runtime.Handler;
import runtime.Scene;

public class Game extends Scene {

	Player p = new Player();
	Platform floor;

	@Override
	public void init(String arg0) {
		floor = new Platform(3, Assets.getSprite("brick"));
		floor.setY(64);
		start();

	}

	@Override
	public void render(DrawGraphics g) {

	}

	@Override
	public void update() {

	}

	@Override
	public void start() {
		Handler.getEntityManager().addEntity(p);
		Handler.getEntityManager().addEntity(floor);
		Handler.getCamera().centerOnEntity(p);
		Vector.setGlobalGravity(-9.8);
	}

	@Override
	public void stop() {
		Handler.getEntityManager().removeEntity(p);
		Handler.getEntityManager().removeEntity(floor);

	}

}
