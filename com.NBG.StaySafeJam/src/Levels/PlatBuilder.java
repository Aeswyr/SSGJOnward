package Levels;

import java.util.Random;
import core.Assets;
import entities.Platform;
import runtime.Handler;

public class PlatBuilder {
    
    Platform tempPlat;
    int seedPlan = 12345;
    Random randFloor = new Random(seedPlan);
    int floorLength;
    int previousX;
    int chunkLength;
    
    public PlatBuilder() {
        previousX = 0;
        
        chunkBuilder();
    }
    
    public void floorMaker() {
        if((Handler.getCamera().xOffset() == (previousX - (chunkLength / 2)))) {
            chunkBuilder();
        }
    }
    
    public void chunkBuilder() {
        for(int i = 0; i < 20; i++) {
            chunkLength = 0;
            
            floorLength = randFloor.nextInt(6) + 4;
            tempPlat = new Platform(floorLength, Assets.getSprite("brick"));
            tempPlat.setX(previousX);
            tempPlat.setY(64);
            
            Handler.getEntityManager().addEntity(tempPlat);
            
            previousX += 50 + floorLength * 16;
            chunkLength += 50 + floorLength * 16;
        }
    }
}