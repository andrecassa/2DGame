package com.casarini.game.util;

import com.casarini.game.entity.Entity;
import com.casarini.game.tiles.TileManager;
import com.casarini.game.tiles.TileMapObj;
import com.casarini.game.tiles.blocks.Block;
import com.casarini.game.tiles.blocks.HoleBlock;

public class TileCollision {

    private Entity e;
    private Block block;

    public TileCollision (Entity e){
        this.e = e;
    }
    public boolean collisionTile(float ax, float ay){
        for(int c=0; c<4; c++){
            int xt = (int) ((e.getBounds().getPos().x + ax) + (c % 2) * e.getBounds().getWidth() + e.getBounds().getXOffset()) / 64;
            int yt = (int) ((e.getBounds().getPos().y + ay) + ((int) (c / 2)) * e.getBounds().getHeight() + e.getBounds().getYOffset()) / 64;

            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
                return TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt)).update(e.getBounds());
            }
        }
        return false;
    }
    private boolean collisionHole(float ax, float ay, float xt, float yt, Block block){
        int nextXt = (int) ((((e.getBounds().getPos().x + ax) + e.getBounds().getXOffset()) / 64) + e.getBounds().getWidth() / 64);
        int nextYt = (int) ((((e.getBounds().getPos().y + ay) + e.getBounds().getYOffset()) / 64) + e.getBounds().getHeight() / 64);

        if((nextXt == yt + 1) || (nextYt == xt + 1)){
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))){
                Block blockNeighbour = TileMapObj.tmo_blocks.get(String.valueOf(nextXt) + "," + String.valueOf(nextYt));
                return blockNeighbour.update(e.getBounds());
            }else{
                if(block.isInside(e.getBounds())){
                    return block.update(e.getBounds());
                }
            }
        }

        return false;
    }
}
