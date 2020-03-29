package scenes;

import java.io.IOException;
import core.Assets;
import core.Core;
import core.Support;
import gfx.DrawGraphics;
import gfx.Sprite;
import gui.Button;
import gui.ClickListener;
import gui.Frame;
import gui.NineSlice;
import gui.UICollection;
import gui.UIObject;
import networking.Server;
import runtime.Handler;
import runtime.Scene;

public class Menu extends Scene {

	NineSlice wood;
	NineSlice paper;
	NineSlice ink;

	UICollection main;
	UICollection host;
	UICollection lobby;
	UICollection join;

	@Override
	public void init(String arg0) {

		main = new UICollection();
		host = new UICollection();
		lobby = new UICollection();
		join = new UICollection();

		wood = new NineSlice(Assets.getSprite("wood_ui"));
		paper = new NineSlice(Assets.getSprite("paper_ui"));
		ink = new NineSlice(Assets.getSprite("ink_ui"));

		main.add(
				new Frame(0, 0, Handler.getWidth() - 8, Handler.getHeight() - 24, wood, Sprite.createLightData(20, 0)));

		main.add(new Button("Single Player", new ClickListener() {

			@Override
			public void onClick(UIObject source) {
				Handler.startScene(Core.game);
			}

		}, 64, 48, 128, 24, paper, paper, Sprite.createLightData(20, 0)));

		main.add(new Button("Host New Game", new ClickListener() {

			@Override
			public void onClick(UIObject source) {
				try {
					Core.server = new Server();
					networking.Processing.InitClient(Core.process);
					Core.multiplayer = true;
					Core.hosting = true;
				} catch (IOException e) {

				}
			}

		}, 64, 96, 128, 24, paper, paper, Sprite.createLightData(20, 0)));

		main.add(new Button("Join Game", new ClickListener() {

			@Override
			public void onClick(UIObject source) {
				networking.Processing.InitClient(Core.process);
				Core.multiplayer = true;
			}

		}, 64, 144, 128, 24, paper, paper, Sprite.createLightData(20, 0)));

		start();
	}

	@Override
	public void render(DrawGraphics arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if (Core.multiplayer)
			Support.sortCommands();
	}

	@Override
	public void start() {
		Handler.getUI().flushObjects();
		Handler.getUI().addObject(main);
	}

	@Override
	public void stop() {
		Handler.getUI().flushObjects();
	}

}
