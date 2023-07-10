package com.casarini.game.entity;

import com.casarini.game.graphics.Animation;
import com.casarini.game.graphics.Sprite;
import com.casarini.game.util.AABB;
import com.casarini.game.util.TileCollision;
import com.casarini.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {


    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected boolean shift;
    protected int attackSpeed;
    protected int attackDuration;

    protected int last = 100;

    protected float dx;
    protected float dy;
    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 0.01f;
    protected AABB hitBoxPlayer;
    protected AABB hitBounds;
    protected AABB bounds;
    protected TileCollision tc;
    protected int psize = 128;
    protected int esize = 64;
    private boolean alive = true;

    private long invincibilityTime = 1000000000;
    private long hit = 0;
    private int tmp=0;
    private boolean killed = false;





    public Entity(Sprite sprite, Vector2f origin, int size){
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        hitBoxPlayer = new AABB(origin, size/2, size/2);
        bounds = new AABB(origin, size/2, size/2);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2), origin.y), size/3, size/3);

        ani = new Animation();
        setAnimation(0, sprite.getSpriteArray(0), 10);

        tc = new TileCollision(this);
    }

    public void getSprite(Sprite sprite){
        this.sprite = sprite;
    }
    public void setSize(int i){size = i;}
    public void setMaxSpeed(float f){maxSpeed = f;}
    public void setAcc(float f){acc = f;}
    public void setDeacc(float f){deacc = f;}
    public long getInvincibilityTime(){return invincibilityTime;}
    public long getHit(){return hit;}
    public void setHit(){hit = System.nanoTime();}
    public int getTmp(){return tmp;}
    public int setTmp(int t){return tmp = t;}
    public void killed(){killed = true;}
    public boolean isDead(){return killed;}


    public boolean isAlive(){
        if(alive)return true;
        else return false;
    }
    public void setDead(){alive = false;}
    public void setAlive(){
        alive = true;
        killed = false;
    }
    public void entityAlive(){alive = true;}
    public void stop(){
        this.dx = 0;
        this.dy = 0;
    }


    public AABB getBounds(){return bounds;}

    public int getSize(){return size;}
    public Animation getAnimation(){return ani;}
    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }
    public void setAnimationSpec(int i, BufferedImage[] frames, int delay, int lenght){
        currentAnimation = i;
        BufferedImage[] newFrames = new BufferedImage[lenght];
        System.arraycopy(frames, 0, newFrames, 0, lenght);
        ani.setFrames(newFrames);
        ani.setDelay(delay);
    }

    public void setHitBoxDirection(){
        if(up){
            hitBounds.setXOffset(-size / 2);
            hitBounds.setYOffset(-size / 2);
        }
        else if(down){
            hitBounds.setXOffset(size / 2);
            hitBounds.setYOffset(-size / 2);
        }
        else if(left){
            hitBounds.setXOffset(-size / 2);
            hitBounds.setYOffset(0);
        }
        else if(right){
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(0);
        }

    }
    public Vector2f getPos(){return pos;}
    public Vector2f getCenter(int size){
        float a = pos.x;
        float b = pos.y;
        float dx = (a + a + size) / 2;
        float dy = (b + b + size) / 2;

        Vector2f center = new Vector2f(dx, dy);
        return center;
    }



    public void update(){

    }



    public abstract void render(Graphics2D g);

    }
