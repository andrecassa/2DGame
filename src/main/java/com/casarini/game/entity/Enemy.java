package com.casarini.game.entity;

import com.casarini.game.graphics.Sprite;
import com.casarini.game.util.AABB;
import com.casarini.game.util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{
    private final int RIGHT = 2;
    private final int IDLERIGHT = 0;
    private final int LEFT = 7;
    private final int IDLELEFT = 5;
    private final int DEATHLEFT = 9;
    private final int DEATHRIGHT = 4;

    private AABB sense;
    private int r;
    private int r2 = 150;
    private Vector2f ppos = new Vector2f(0,0);
    private Vector2f ppos2 = new Vector2f(0,0);

    public Enemy(Sprite sprite, Vector2f origin, int size){
        super(sprite, origin, size);

        acc = 1f;
        maxSpeed = 2f;
        r = 135;

        bounds.setWidth(32);
        bounds.setHeight(24);
        bounds.setXOffset(16);
        bounds.setYOffset(24);

        sense = new AABB(new Vector2f(origin.x - size/2, origin.y - size/2), r, r);
    }

    private void move(Player player, Enemy enemy){
        if(player.getBounds().distance(player.getCenter(psize), enemy.getCenter(esize)) < r/2 + r2) {
            //System.out.println("yes");
            right = false;
            left = true;


            if (pos.y > player.getCenter(psize).y) {
                dy -= acc;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else {
                if (dy < 0) {
                    dy += deacc;
                    if (dy < 0) {
                        dy = 0;
                    }
                }
            }
            if (pos.y < player.getCenter(psize).y) {
                dy += acc;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            } else {
                if (dy > 0) {
                    dy -= deacc;
                    if (dy > 0) {
                        dy = 0;
                    }
                }
            }
            if (enemy.getCenter(esize).x > player.getCenter(psize).x) {
                dx -= acc;
                left = true;
                right= false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else {
                if (dx < 0) {
                    dx += deacc;
                    if (dx < 0) {
                        dx = 0;
                    }
                }
            }
            if (enemy.getCenter(esize).x < player.getCenter(psize).x) {
                dx += acc;
                right = true;
                left = false;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            } else {
                if (dx > 0) {
                    dx -= deacc;
                    if (dx > 0) {
                        dx = 0;
                    }
                }
            }
        }else{
            dx = 0;
            dy = 0;
            left = false;
            right = false;
        }

    }


    private void animate() {
        if(right){
            last = RIGHT;
            if(currentAnimation != RIGHT || ani.getDelay() == -1){
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }
        else if(left) {
            last = LEFT;
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }else if (last == RIGHT) {
            if (currentAnimation != IDLERIGHT || ani.getDelay() == -1) {
                setAnimationSpec(IDLERIGHT, sprite.getSpriteArray(IDLERIGHT), 8, 4);
            }
        } else if (last == LEFT) {
            if (currentAnimation != IDLELEFT || ani.getDelay() == -1) {
                setAnimationSpec(IDLELEFT, sprite.getSpriteArray(IDLELEFT), 8, 4);
            }
        } else {
            last = LEFT;
            setAnimationSpec(IDLELEFT, sprite.getSpriteArray(IDLELEFT), 8, 4);
        }
    }




    public void update(Player player, Enemy enemy){
        super.update();
        if(!this.isAlive()){
            if(left && this.getTmp() == 0){
                setAnimationSpec(DEATHLEFT, sprite.getSpriteArray(DEATHLEFT), 10, 5);
            }else if(right && this.getTmp() == 0){
                setAnimationSpec(DEATHRIGHT, sprite.getSpriteArray(DEATHRIGHT), 10, 5);
            }

            this.setTmp(this.getTmp()+1);
            //if tmp = delay-10 (so its the last animation)
            if(this.getTmp() == 40){
                this.stop();
                this.killed();
            }

            move(player, enemy);
            ani.update();
            return;
        }

        move(player, enemy);

        if(!tc.collisionTile(dx, 0)){
            sense.getPos().x += dx;
            pos.x += dx;
        }
        if(!tc.collisionTile(0, dy)){
            sense.getPos().y += dy;
            pos.y += dy;
        }

        ppos = player.getCenter(psize);//only for drawing distance
        ppos2 = enemy.getCenter(esize);//only for drawing distance



        animate();
        ani.update();
    }

    @Override
    public void render(Graphics2D g) {
/*
        //slime hitbox
        g.setColor(Color.green);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

        //slime range (circle)
        g.setColor(Color.blue);
        g.drawOval((int) (sense.getPos().getWorldVar().x - r2), (int) (sense.getPos().getWorldVar().y - r2), r+2*r2, r+2*r2);

        //distance from slime
        g.setColor(Color.yellow);
        g.drawLine((int) ppos.getWorldVar().x, (int) ppos.getWorldVar().y, (int) ppos2.getWorldVar().x, (int) ppos2.getWorldVar().y);
*/
        g.drawImage(ani.getImage(), (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, size, size, null);
    }
}
