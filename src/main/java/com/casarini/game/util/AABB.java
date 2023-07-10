package com.casarini.game.util;

import com.casarini.game.entity.Entity;
import com.casarini.game.tiles.TileMapObj;
import com.casarini.game.tiles.blocks.Block;
import com.casarini.game.tiles.blocks.HoleBlock;

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