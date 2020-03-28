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

	int scroll = 0;
	
	@Override
	public void init(String arg0) {
		p.setX(32);
		floor = new Platform(10, Assets.getSprite("brick"));
		floor.setY(64);
		start();
		
	}

	@Override
	public void render(DrawGraphics g) {

	}

	@Override
	public void update() {
		Handler.getCamera().center(scroll++, 0);
	}

	@Override
	public void start() {
		Handler.getEntityManager().addEntity(p);
		Handler.getEntityManager().addEntity(floor);
		Vector.setGlobalGravity(-9.8);
	}

	@Override
	public void stop() {
		Handler.getEntityManager().removeEntity(p);
		Handler.getEntityManager().removeEntity(floor);

	}

}
