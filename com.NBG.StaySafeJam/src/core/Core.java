package core;

import java.util.Queue;

import entity.Vector;
import networking.Client;
import networking.Processor;
import networking.Server;
import runtime.Handler;
import runtime.Scene;
import scenes.Game;
import scenes.Menu;

public class Core {

	public static Scene menu, game;
	public static Server server;
	public static Client client;
	public static boolean multiplayer = false;
	public static boolean hosting = false;

	public static Processor process = new Processor() {

		int length = 0;
		int read = 0;
		int flag = 0;
		byte[] command;

		@Override
		public void Process(Queue<byte[]> queue, byte[] data) {
			while (flag < data.length) {
				byte[] cmd = null;
				if (read == 0) {
					switch (data[flag]) {
					case 0:
						length = 1;
						break;
					case 1:
						length = 1;
						break;
					case 2:
						length = 9;
						break;
					case 3:
						length = 11;
						break;
					case 4:
						length = 19;
						break;
					case 5:
						length = 3;
						break;
					case 6:
						length = 3;
						break;
					}
					cmd = new byte[length];
					cmd[0] = data[flag];
					read++;
					flag++;
				}
				while (read < length && flag < data.length) {
					if (command == null) {
						cmd[read] = data[flag];
					} else {
						command[read] = data[flag];
					}
					read++;
					flag++;
				}
				if (read != length) {
					command = cmd;
				} else {
					if (command != null) {
						queue.add(command);
						command = null;
					} else {
						queue.add(cmd);
					}
					read = 0;
					length = 0;
				}

			}
			flag = 0;
		}

	};

	public static void main(String[] args) {
		Engine.start(1440, 720, "onward", "Onward!");

		Engine.getGraphics().setFont(Assets.getFont("script0"));
		Engine.updateScale(3, 3);
		
		Vector.adjustCollisionProximity(8);
		

		menu = new Menu();
		game = new Game();

		Handler.startScene(menu);
	}
}
