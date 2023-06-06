package com.casarini.game.tiles;

import com.almasb.fxgl.entity.level.tiled.TiledMap;
import com.casarini.game.graphics.Sprite;
import com.casarini.game.tiles.blocks.Block;
import com.casarini.game.tiles.blocks.NormBlock;
import com.casarini.game.util.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;

    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        blocks = new ArrayList<Block>();

        String[] block = data.split(",");
        for(int i=0; i<(width*height); i++){
            int tmp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if(tmp != 0){
                int x = (int) (i%width) * tileWidth;
                blocks.add(new NormBlock(sprite.getSprite((int)((tmp-1) % tileColumns), (int)((tmp-1)) / tileColumns), new Vector2f((int) (i%width) * tileWidth, (int) (i/height) * tileHeight), tileWidth, tileHeight));
            }
        }
        int a = 0;
    }

    //@Override
    public void render(Graphics2D g){
        for(int i=0; i< blocks.size(); i++){
            blocks.get(i).render(g);
        }
    }
}
