package core;

import runtime.Handler;
import runtime.Scene;
import scenes.Game;
import scenes.Menu;

public class Init {
	public static void main(String[] args) {
		Engine.start(1440, 720, "onward", "Onward!");
		
		Scene menu = new Menu();
		Scene game = new Game();
		
		Handler.startScene(game);
	}
}
