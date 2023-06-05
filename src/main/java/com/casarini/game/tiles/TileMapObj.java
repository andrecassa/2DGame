package com.casarini.game.tiles;

import com.casarini.game.graphics.Sprite;
import com.casarini.game.tiles.blocks.Block;
import com.casarini.game.tiles.blocks.HoleBlock;
import com.casarini.game.tiles.blocks.ObjBlock;
import com.casarini.game.util.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TileMapObj extends TileMap{

    public static HashMap<String, Block> tmo_blocks;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        Block tmpBlock;
        tmo_blocks = new HashMap<String, Block>();

        String[] block = data.split(",");
        for(int i=0; i<(width*height); i++){
            int tmp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if(tmp != 0){
                if(tmp == 172){
                    tmpBlock = new HoleBlock(sprite.getSprite((int)((tmp-1) % tileColumns), (int)((tmp-1)) / tileColumns), new Vector2f((int) (i%width) * tileWidth, (int) (i/height) * tileHeight), tileWidth, tileHeight);
                }else{
                    tmpBlock = new ObjBlock(sprite.getSprite((int)((tmp-1) % tileColumns), (int)((tmp-1)) / tileColumns), new Vector2f((int) (i%width) * tileWidth, (int) (i/height) * tileHeight), tileWidth, tileHeight);
                }
                tmo_blocks.put((String.valueOf((int) (i%width)) + "," + String.valueOf((int) (i/height))), tmpBlock);
            }
        }
    }

    public void render(Graphics2D g) {
        for(Block block: tmo_blocks.values()){
            block.render(g);
        }
    }
}
