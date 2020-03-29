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
    int floorType;
    int previousX;
    int chunkLength;
    int gapSize;
    
    public PlatBuilder() {
        previousX = 0;
        
        chunkBuilder();
    }
    
    public void floorMaker() {
        if((Handler.getCamera().xOffset() == (previousX - (chunkLength / 2)))) {
            System.out.println("chunks: " + chunkLength + " x value: " + previousX);
            chunkBuilder();
        }
    }
    
    private void chunkBuilder() {
        chunkLength = 0;
        
        for(int i = 0; i < 10; i++) {
            
            floorLength = randFloor.nextInt(6) + 4;
            floorType = randFloor.nextInt(10);
            
            if(floorType <= 3) {
                gapSize = 0;
            } else {
                gapSize = randFloor.nextInt(80) + 16;
            }
            
            createSinglePlat();
        }
    }
    
    private void createSinglePlat() {
        tempPlat = new Platform(floorLength, Assets.getSprite("brick"));
        tempPlat.setX(previousX);
        tempPlat.setY(64);
        
        Handler.getEntityManager().addEntity(tempPlat);
        
        previousX += gapSize + floorLength * 16;
        chunkLength += gapSize + floorLength * 16;
    }
    
}