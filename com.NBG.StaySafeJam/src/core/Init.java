package core;

import runtime.Handler;
import runtime.Scene;
import scenes.Game;
import scenes.Menu;

public class Init {
	
	
	public static Scene menu, game;
	
	public static void main(String[] args) {
		Engine.start(1440, 720, "onward", "Onward!");
		
		Engine.getGraphics().setFont(Assets.getFont("script0"));
		Engine.updateScale(3, 3);
		
		
		menu = new Menu();
		game = new Game();
		
		Handler.startScene(menu);
	}
}
