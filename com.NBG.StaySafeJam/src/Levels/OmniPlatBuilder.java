package Levels;

import java.util.Random;
import core.Assets;
import entities.Block;
import runtime.Handler;

public class OmniPlatBuilder {

    int seedPlan = 12345;
    Random randFloor = new Random(seedPlan);
    int floorLength;
    int floorType;
    int floorHeight;
    int gapSize;
    int heightOffset;
    int previousX;
    int previousX2;
    int chunkLength;
    Block tempBlock;

    public OmniPlatBuilder() {
        previousX = 0;
        previousX2 = 0;

        omniChunkBuilder();
    }

    public void omniFloorBuilder() {
        if ((Handler.getCamera()
                .xOffset() == (previousX - (chunkLength / 2)))) {
            System.out.println(
                    "chunks: " + chunkLength + " x value: " + previousX);
            omniChunkBuilder();
        }
    }

    private void omniChunkBuilder() {
        chunkLength = 0;

        for (int i = 0; i < 10; i++) {

            floorLength = randFloor.nextInt(6) + 4;
            floorType = randFloor.nextInt(10);

            if (floorType <= 3) {
                gapSize = 0;
            } else {
                gapSize = randFloor.nextInt(5) + 1;
            }

            floorType = randFloor.nextInt(2);

            if (floorType == 1) {
                lBlock();
            } else {
                uBlock();
            }
            
            obstacleBlock();
        }
    }

    private void lBlock() {
        floorHeight = randFloor.nextInt(3) + 6;

        floorLength = randFloor.nextInt(6) + 4;
        tempBlock = new Block(floorLength, 1, Assets.getSprite("brick"));
        tempBlock.setX(previousX);
        tempBlock.setY(-floorHeight * 16);
        previousX += floorLength * 16 + gapSize * 16;
        chunkLength += floorLength * 16 + gapSize * 16;

        Handler.getEntityManager().addEntity(tempBlock);

        heightOffset = randFloor.nextInt(2) - 1;

        floorLength = randFloor.nextInt(4) + 1;
        tempBlock = new Block(1, floorLength, Assets.getSprite("brick"));
        tempBlock.setX(previousX + floorLength * 16);
        tempBlock.setY(-floorHeight * 16 - heightOffset * 16);

        Handler.getEntityManager().addEntity(tempBlock);
    }

    private void uBlock() {
        floorHeight = randFloor.nextInt(3) + 6;

        floorLength = randFloor.nextInt(6) + 4;
        tempBlock = new Block(floorLength, 1, Assets.getSprite("brick"));
        tempBlock.setX(previousX);
        tempBlock.setY(-floorHeight * 16);
        previousX += floorLength * 16 + gapSize * 16;
        chunkLength += floorLength * 16 + gapSize * 16;

        Handler.getEntityManager().addEntity(tempBlock);

        heightOffset = randFloor.nextInt(2) - 1;

        floorLength = randFloor.nextInt(4) + 1;
        tempBlock = new Block(1, floorLength, Assets.getSprite("brick"));
        tempBlock.setX(previousX + floorLength * 16);
        tempBlock.setY(-floorHeight * 16 - heightOffset * 16);

        Handler.getEntityManager().addEntity(tempBlock);

        heightOffset = randFloor.nextInt(2) - 1;

        floorLength = randFloor.nextInt(4) + 1;
        tempBlock = new Block(1, floorLength, Assets.getSprite("brick"));
        tempBlock.setX(previousX + floorLength * 16);
        tempBlock.setY(-floorHeight * 16 - heightOffset * 16);

        Handler.getEntityManager().addEntity(tempBlock);
    }

    private void obstacleBlock() {
        floorHeight = randFloor.nextInt(6) - 2;
        floorLength = randFloor.nextInt(6) + 4;
        tempBlock = new Block(floorLength, 1, Assets.getSprite("brick"));
        tempBlock.setX(previousX - 64);
        tempBlock.setY(floorHeight * 16);

        Handler.getEntityManager().addEntity(tempBlock);
        
        tempBlock = new Block(floorLength - 2, 1, Assets.getSprite("brick"));
        tempBlock.setX(previousX + 32 - (randFloor.nextInt(4) * 16));
        tempBlock.setY(floorHeight * 16 + 32 - (randFloor.nextInt(4) * 16));
        
        Handler.getEntityManager().addEntity(tempBlock);
    }
}
