package scenes;

import core.Assets;
import core.Init;
import gfx.DrawGraphics;
import gfx.Sprite;
import gui.Button;
import gui.ClickListener;
import gui.Frame;
import gui.NineSlice;
import gui.UICollection;
import gui.UIObject;
import runtime.Handler;
import runtime.Scene;

public class Menu extends Scene{

	NineSlice wood;
	NineSlice paper;
	NineSlice ink;
	
	UICollection main = new UICollection();
	UICollection host = new UICollection();
	UICollection lobby = new UICollection();
	UICollection join = new UICollection();
	
	
	@Override
	public void init(String arg0) {
		wood = new NineSlice(Assets.getSprite("wood_ui"));
		paper = new NineSlice(Assets.getSprite("paper_ui"));
		ink = new NineSlice(Assets.getSprite("ink_ui"));
		
		main.add(new Frame(0, 0, Handler.getWidth() - 8, Handler.getHeight() - 24, wood, Sprite.createLightData(20, 0)));
		main.add(new Button("Host New Game", new ClickListener() {

			@Override
			public void onClick(UIObject source) {
				// TODO Auto-generated method stub
				Handler.startScene(Init.game);
			}
			
		}, 512, 256, 128, 24, paper, paper, Sprite.createLightData(20, 0)));
		
		main.add(new Button("Join Game", new ClickListener() {

			@Override
			public void onClick(UIObject source) {
				// TODO Auto-generated method stub
				Handler.startScene(Init.game);
			}
			
		}, 256, 256, 128, 24, paper, paper, Sprite.createLightData(20, 0)));
		
		start();
	}

	@Override
	public void render(DrawGraphics arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
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
