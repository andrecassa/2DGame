package com.casarini.game.tiles.blocks;

import com.casarini.game.graphics.Sprite;
import com.casarini.game.util.AABB;
import com.casarini.game.util.Vector2f;
import javafx.scene.control.Slider;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Block {
    protected int w;
    protected int h;
    protected BufferedImage img;
    protected Vector2f pos;
    protected AffineTransform at;
    private Slider zoomSlider;

    public Block(BufferedImage img, Vector2f pos, int w, int h){
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public boolean isInside(AABB p){
        return false;
    }
    public boolean update(AABB p) {
        return true;
    }


    public void render(Graphics2D g){
        int a =(int)pos.getWorldVar().x;
        int b =(int)pos.getWorldVar().y;

        g.drawImage(img, (int)pos.getWorldVar().x, (int)pos.getWorldVar().y, w, h, null);
        //g.drawImage(img.getScaledInstance(4, 4, Image.SCALE_SMOOTH), (int)(pos.getWorldVar().x * 4), (int)(pos.getWorldVar().y * 4), w * 4, h * 4, null);

    }
}
