package scenes;

import Levels.PlatBuilder;
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
	PlatBuilder platforms;

	int scroll = 0;
	
	@Override
	public void init(String arg0) {
		p.setX(32);
		platforms = new PlatBuilder();
		start();
		
	}

	@Override
	public void render(DrawGraphics g) {

	}

	@Override
	public void update() {
		Handler.getCamera().center(scroll++, 0);
		platforms.floorMaker();
	}

	@Override
	public void start() {
		Handler.getEntityManager().addEntity(p);
		Vector.setGlobalGravity(-9.8);
	}

	@Override
	public void stop() {
		Handler.getEntityManager().removeEntity(p);
		Handler.getEntityManager().removeEntity(floor);

	}

}
