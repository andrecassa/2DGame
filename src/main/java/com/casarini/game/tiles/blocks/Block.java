package com.casarini.game.tiles.blocks;

import com.casarini.game.util.AABB;
import com.casarini.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {
    protected int w;
    protected int h;
    protected BufferedImage img;
    protected Vector2f pos;

    public Block(BufferedImage img, Vector2f pos, int w, int h){
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public boolean update(AABB p) {
        return true;
    }

    public void render(Graphics2D g){
        g.drawImage(img, (int)pos.getWorldVar().x, (int)pos.getWorldVar().y, w, h, null);
    }

}
