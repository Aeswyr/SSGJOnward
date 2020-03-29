package scenes;

import java.awt.event.KeyEvent;
import Levels.OmniPlatBuilder;
import Levels.PlatBuilder;
import core.Core;
import core.Support;
import entities.Player;
import entity.Vector;
import gfx.DrawGraphics;
import input.Controller;
import runtime.Handler;
import runtime.Scene;

public class Game extends Scene {

	Player p = new Player();
	PlatBuilder platforms;
	OmniPlatBuilder omniPlatforms;
	int cameraHeight;

	int scroll;

	@Override
	public void init(String arg0) {
		p = new Player();
		p.setX(32);
		cameraHeight = 92;
		platforms = new PlatBuilder();
		omniPlatforms = new OmniPlatBuilder();
		start();
		scroll = 0;
		scrolling = false;

	}

	@Override
	public void render(DrawGraphics g) {

	}

	boolean scrolling;

	@Override
	public void update() {
	    System.out.println(p.getY());
		if (Core.multiplayer)
			Support.sortCommands();
		if(p.getY() < 0) {
		    System.out.println(p.getY());
		    //cameraHeight = p.getY() + 25;
		    if(cameraHeight > p.getY() + 120) {
		        cameraHeight -= 4;
		    }
		} else {
		    if(cameraHeight < 92) {
		        cameraHeight += 4;
		    }
		}
		if (scrolling)
			Handler.getCamera().center(scroll++, cameraHeight);
		else if (Controller.getKeyPressed((char) KeyEvent.VK_SPACE))
			scrolling = true;
		platforms.floorMaker();
		omniPlatforms.omniFloorBuilder();
	}

	@Override
	public void start() {
		Handler.getEntityManager().addEntity(p);
		Vector.setGlobalGravity(-9.8);
		Handler.getCamera().center(scroll, 92);
	}

	@Override
	public void stop() {
		Handler.getEntityManager().removeEntity(p);

	}

}
