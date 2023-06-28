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

    public AABB(Vector2f pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }
    public AABB(Vector2f pos, int r, Entity e){
        this.pos = pos;
        this.r = r;
        this.e = e;

        size = r;
    }
    public Vector2f getPos(){return pos;}
    public float getRadius(){return r;}
    public float getWidth(){return w;}
    public float getHeight(){return h;}

    public void setBox(Vector2f pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle(Vector2f pos, int r){
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth(float f){w = f;}
    public void setHeight(float f){h = f;}
    public void setXOffset(float f){xOffset = f;}
    public void setYOffset(float f){yOffset = f;}
    public float getXOffset(){return xOffset;}
    public float getYOffset(){return yOffset;}


    public boolean collides(AABB bBox){
        float ax = ((pos.getWorldVar().x + (xOffset)) + (w / 2));
        float ay = ((pos.getWorldVar().y + (yOffset)) + (h / 2));
        float bx = ((bBox.pos.getWorldVar().x + (bBox.xOffset / 2)) + (w / 2));
        float by = ((bBox.pos.getWorldVar().y + (bBox.yOffset / 2)) + (h / 2));

        if(Math.abs(ax - bx) < (this.w / 2) + (bBox.w / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.h / 2)) {
                return true;
            }
        }
        return false;
    }

    //find collision between a circle and a box
    public boolean colCircle(AABB aBox){
        float cx = (float) (pos.getWorldVar().x + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
        float cy = (float) (pos.getWorldVar().y + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));

        float xDelta = cx - Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().x));
        float yDelta = cy - Math.max(aBox.pos.getWorldVar().y + (aBox.getHeight() / 2), Math.min(cy, aBox.pos.getWorldVar().y));

        if((xDelta * xDelta + yDelta * yDelta) < (this.r / Math.sqrt(2)) * (this.r / Math.sqrt(2))){
            return true;
        }

        return false;
    }

    public boolean collisionTile(float ax, float ay){
        for(int c=0; c<4; c++){
            int xt = (int) ((pos.x + ax) + (c % 2) * w + xOffset) / 64;
            int yt = (int) ((pos.y + ay) + ((int) (c / 2)) * h + yOffset) / 64;

            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
                Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if(block instanceof HoleBlock){
                    return collisionHole(ax, ay, xt, yt, block);
                }
                return TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt)).update(this);
            }
        }
        return false;
    }
    private boolean collisionHole(float ax, float ay, float xt, float yt, Block block){
        int nextXt = (int) ((((pos.x + ax) + xOffset) / 64) + w / 64);
        int nextYt = (int) ((((pos.y + ay) + yOffset) / 64) + h / 64);

        if((nextXt == yt + 1) || (nextYt == xt + 1)){
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))){
                Block blockNeighbour = TileMapObj.tmo_blocks.get(String.valueOf(nextXt) + "," + String.valueOf(nextYt));
                return blockNeighbour.update(this);
            }else{
                if(block.isInside(this)){
                    return block.update(this);
                }
            }
        }

        return false;
    }
}
