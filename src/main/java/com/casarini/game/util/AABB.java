package com.casarini.game.util;

import com.casarini.game.entity.Entity;
import com.casarini.game.tiles.TileMapObj;
import com.casarini.game.tiles.blocks.Block;
import com.casarini.game.tiles.blocks.HoleBlock;
import javafx.scene.shape.Rectangle;

public class AABB {

    private Vector2f pos;
    private float xOffset = 0;
    private float yOffset = 0;

    private float w;
    private float h;
    private float r;
    private int size;
    private Entity e;

    public AABB(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public AABB(Vector2f pos, int r, Entity e) {
        this.pos = pos;
        this.r = r;
        this.e = e;

        size = r;
    }

    public Vector2f getPos() {
        return pos;
    }

    public float getRadius() {
        return r;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

    public void setBox(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle(Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth(float f) {
        w = f;
    }

    public void setHeight(float f) {
        h = f;
    }

    public void setXOffset(float f) {
        xOffset = f;
    }

    public void setYOffset(float f) {
        yOffset = f;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }


    public boolean collides(AABB bBox) {
        float ax = ((pos.getWorldVar().x + (xOffset)) + (w / 2));
        float ay = ((pos.getWorldVar().y + (yOffset)) + (h / 2));
        float bx = ((bBox.pos.getWorldVar().x + (bBox.xOffset / 2)) + (w / 2));
        float by = ((bBox.pos.getWorldVar().y + (bBox.yOffset / 2)) + (h / 2));

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.w / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.h / 2)) {
                return true;
            }
        }
        return false;
    }
    public boolean col(Rectangle rect1, Rectangle rect2){
        double rect1Left = rect1.getX();
        double rect1Right = rect1.getX() + rect1.getWidth();
        double rect1Top = rect1.getY();
        double rect1Bottom = rect1.getY() + rect1.getHeight();

        double rect2Left = rect2.getX();
        double rect2Right = rect2.getX() + rect2.getWidth();
        double rect2Top = rect2.getY();
        double rect2Bottom = rect2.getY() + rect2.getHeight();

        if (rect1Left < rect2Right && rect1Right > rect2Left && rect1Top < rect2Bottom && rect1Bottom > rect2Top) {
            return true;
        }else {
            return false;
        }
    }

    public double distance(Vector2f pos, Vector2f pos2) {
        return Math.sqrt((pos2.y - pos.y) * (pos2.y - pos.y) + (pos2.x - pos.x) * (pos2.x - pos.x));
    }

    public Vector2f getCenter(int size){
        float a = pos.x;
        float b = pos.y;
        float dx = (a + a + size) / 2;
        float dy = (b + b + size) / 2;

        Vector2f center = new Vector2f(dx, dy);
        return center;
    }
}