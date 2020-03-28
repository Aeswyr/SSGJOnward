package scenes;

import java.awt.event.KeyEvent;

import Levels.PlatBuilder;
import entities.Player;
import entity.Vector;
import gfx.DrawGraphics;
import input.Controller;
import runtime.Handler;
import runtime.Scene;

public class Game extends Scene {

	Player p = new Player();
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

	boolean scrolling = false;

	@Override
	public void update() {

		if (scrolling)
			Handler.getCamera().center(scroll++, 0);
		else if (Controller.getKeyPressed((char) KeyEvent.VK_SPACE))
			scrolling = true;
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

	}

}
