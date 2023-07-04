package com.casarini.game.entity;

import com.casarini.game.graphics.Animation;
import com.casarini.game.graphics.Sprite;
import com.casarini.game.util.AABB;
import com.casarini.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public abstract class Entity {

    private final int UP = 5;
    private final int IDLEUP = 2;
    private final int DOWN = 3;
    private final int IDLEDOWN = 0;
    private final int RIGHT = 4;
    private final int IDLERIGHT = 1;
    private final int LEFT = 7;
    private final int IDLELEFT = 6;
    private final int ATTACKLEFT = 8;
    private final int ATTACKRIGHT = 10;
    private final int ATTACKUP = 11;
    private final int ATTACKDOWN = 9;
    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean upIdle;
    protected boolean down;
    protected boolean downIdle;
    protected boolean right;
    protected boolean rightIdle;
    protected boolean left;
    protected boolean leftIdle;
    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;

    protected int last = 100;

    protected float dx;
    protected float dy;
    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 0.01f;
    protected AABB hitBounds;
    protected AABB bounds;

    public void setUpIdle(){upIdle = true;}

    public Entity(Sprite sprite, Vector2f origin, int size){
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin, size/2, size/2);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2), origin.y), size/3, size/3);

        ani = new Animation();
        setAnimation(IDLEDOWN, sprite.getSpriteArray(IDLEDOWN), 10);
    }

    public void getSprite(Sprite sprite){
        this.sprite = sprite;
    }
    public void setSize(int i){size = i;}
    public void setMaxSpeed(float f){maxSpeed = f;}
    public void setAcc(float f){acc = f;}
    public void setDeacc(float f){deacc = f;}

    public AABB getBounds(){return bounds;}

    public int getSize(){return size;}
    public Animation getAnimation(){return ani;}
    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }
    public void setAnimationAttack(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);


    }
    private void animate() {
        if(attack){
            if(currentAnimation == IDLELEFT || currentAnimation == LEFT || ani.getDelay() == -1){
                setAnimationAttack(ATTACKLEFT, sprite.getSpriteArray(ATTACKLEFT), 3);
            }
            else if(currentAnimation == IDLERIGHT || currentAnimation == RIGHT || ani.getDelay() == -1){
                setAnimation(ATTACKRIGHT, sprite.getSpriteArray(ATTACKRIGHT), 3);
            }
            else if(currentAnimation == IDLEUP || currentAnimation == UP || ani.getDelay() == -1){
                setAnimation(ATTACKUP, sprite.getSpriteArray(ATTACKUP), 3);
            }
            else if(currentAnimation == IDLEDOWN || currentAnimation == DOWN || ani.getDelay() == -1){
                setAnimation(ATTACKDOWN, sprite.getSpriteArray(ATTACKDOWN), 3);
            }
        }
        else if(up){
            last = UP;
            if(currentAnimation != UP || ani.getDelay() == -1){
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }
        else if(down){
            last = DOWN;
            if(currentAnimation != DOWN || ani.getDelay() == -1){
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if(right){
            last = RIGHT;
            if(currentAnimation != RIGHT || ani.getDelay() == -1){
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }
        else if(left){
            last = LEFT;
            if(currentAnimation != LEFT || ani.getDelay() == -1){
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else if(last == UP){
            if(currentAnimation != IDLEUP || ani.getDelay() == -1){
                setAnimation(IDLEUP, sprite.getSpriteArray(IDLEUP), 5);
            }
        }
        else if(last == DOWN){
            if(currentAnimation != IDLEDOWN || ani.getDelay() == -1){
                setAnimation(IDLEDOWN, sprite.getSpriteArray(IDLEDOWN), 5);
            }
        }
        else if(last == RIGHT){
            if(currentAnimation != IDLERIGHT || ani.getDelay() == -1) {
                setAnimation(IDLERIGHT, sprite.getSpriteArray(IDLERIGHT), 5);
            }
        }
        else if(last == LEFT){
            if(currentAnimation != IDLELEFT || ani.getDelay() == -1){
                setAnimation(IDLELEFT, sprite.getSpriteArray(IDLELEFT), 5);
            }
        }
        else{
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }
    public void setHitBoxDirectionPlayer(){
        if(up){
            hitBounds.setXOffset(45);
            hitBounds.setYOffset(50);
        }
        else if(down){
            hitBounds.setXOffset(45);
            hitBounds.setYOffset(120);
        }
        else if(left){
            hitBounds.setXOffset(2);
            hitBounds.setYOffset(84);
        }
        else if(right){
            hitBounds.setXOffset(88);
            hitBounds.setYOffset(84);
        }

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
        animate();
        setHitBoxDirectionPlayer();
        ani.update();
    }



    public abstract void render(Graphics2D g);

    }
