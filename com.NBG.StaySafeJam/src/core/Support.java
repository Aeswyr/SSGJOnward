package core;

import java.nio.ByteBuffer;

import entity.Entity;
import networking.Processing;
import runtime.Handler;

public class Support {

	public static void sortCommands() {
		while (!Processing.commandBuffer.isEmpty()) {
			ByteBuffer cmd = ByteBuffer.wrap(Processing.commandBuffer.poll());

			switch (cmd.get()) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				Entity e0 = Handler.getEntityManager().getEntityID(cmd.getShort());
				e0.setX(cmd.getInt());
				e0.setY(cmd.getInt());
				break;
			case 4:
				Entity e1 = Handler.getEntityManager().getEntityID(cmd.getShort());
				e1.getVector().setVelocityX(cmd.getDouble());
				e1.getVector().setVelocityY(cmd.getDouble());
				break;
			case 5:
				Handler.getEntityManager().addEntity(parseID(cmd.getShort()));
				break;
			case 6:
				Handler.getEntityManager().removeEntity(Handler.getEntityManager().getEntityID(cmd.getShort()));
				break;
			}
		}
	}
	
	private static Entity parseID(short id) {
		return null;
	}
}
