package Levels;

import java.util.Random;
import core.Assets;
import entities.Block;

public class OmniPlatBuilder {

    int seedPlan = 12345;
    Random randFloor = new Random(seedPlan);
    int floorLength;
    int floorType;
    int previousX;
    int chunkLength;
    
    Block[] lFrame = new Block[2];
    Block[] uFrame = new Block[3];
    
    public OmniPlatBuilder() {
        
    }
    
    public Block lBlock() {
            floorLength = randFloor.nextInt(6) + 4;
            lFrame[0] = new Block(floorLength, 1, Assets.getSprite("brick"));
            floorLength = randFloor.nextInt(6) + 4;
            lFrame[1] = new Block(1, floorLength, Assets.getSprite("brick"));
            return null;
    }
    
    public Block uBlock() {
        return null;
    }
    
}
