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
    
    public void chunkBuilder() {
        chunkLength = 0;
        
        for(int i = 0; i < 15; i++) {
            
            floorLength = randFloor.nextInt(6) + 4;
            floorType = randFloor.nextInt(4);
            
            if(floorType == 3) {
                createDoublePlat();
            } else {
                createSinglePlat();
            }
        }
    }
    
    public void createSinglePlat() {
        tempPlat = new Platform(floorLength, Assets.getSprite("brick"));
        tempPlat.setX(previousX);
        tempPlat.setY(64);
        
        Handler.getEntityManager().addEntity(tempPlat);
        
        previousX += 50 + floorLength * 16;
        chunkLength += 50 + floorLength * 16;
    }
    
    public void createDoublePlat() {
        tempPlat = new Platform(floorLength, Assets.getSprite("brick"));
        tempPlat.setX(previousX);
        tempPlat.setY(64);
        
        Handler.getEntityManager().addEntity(tempPlat);
        
        previousX += floorLength * 16;
        chunkLength += floorLength * 16;
        
        tempPlat = new Platform(floorLength, Assets.getSprite("brick"));
        tempPlat.setX(previousX);
        tempPlat.setY(64);
        
        Handler.getEntityManager().addEntity(tempPlat);
        
        previousX += 50 + floorLength * 16;
        chunkLength += 50 + floorLength * 16;
    }
}