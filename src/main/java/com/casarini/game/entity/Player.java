package com.casarini.game.entity;

import com.casarini.game.graphics.Sprite;
import com.casarini.game.states.PlayState;
import com.casarini.game.util.KeyHandler;
import com.casarini.game.util.MouseHandler;
import com.casarini.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth(36);
        bounds.setHeight(20);
        bounds.setXOffset(48);
        bounds.setYOffset(96);
    }
    public void move(){
        if(up){
            dy -= acc;
            if(dy < -maxSpeed){
                dy = -maxSpeed;
            }
        }else{
            if(dy < 0){
                dy += deacc;
                if(dy < 0){
                    dy = 0;
                }
            }
        }
        if(down){
            dy += acc;
            if(dy > maxSpeed){
                dy = maxSpeed;
            }
        }else{
            if(dy > 0){
                dy -= deacc;
                if(dy > 0){
                    dy = 0;
                }
            }
        }
        if(left){
            dx -= acc;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }else{
            if(dx < 0){
                dx += deacc;
                if(dx < 0){
                    dx = 0;
                }
            }
        }
        if(right){
            dx += acc;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }else{
            if(dx > 0){
                dx -= deacc;
                if(dx > 0){
                    dx = 0;
                }
            }
        }

    }

    public void update(){
        super.update();
        move();
        if(!bounds.collisionTile(dx, 0)) {
            PlayState.map.x += dx;
            pos.x += dx;
        }
        if(!bounds.collisionTile(0, dy)){
            PlayState.map.y += dy;
            pos.y += dy;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor((Color.blue));
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

        if(attack){
            g.setColor(Color.red);
            g.drawRect((int) (pos.getWorldVar().x + hitBounds.getXOffset()), (int) (pos.getWorldVar().y + hitBounds.getYOffset()), (int) hitBounds.getWidth(), (int) hitBounds.getHeight());
        }

        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int)(pos.getWorldVar().y), size, size, null);
    }
    public void input(MouseHandler mouse, KeyHandler key){
        if(mouse.getMouseB() == 1){
            System.out.println("Player: " + pos.x + " " + pos.y);
        }

        if(key.up.down){
            up = true;
        }else {
            up = false;
        }
        if(key.down.down){
            down = true;
        }else {
            down = false;
        }
        if(key.left.down){
            left = true;
        }else {
            left = false;
        }
        if(key.right.down){
            right = true;
        }else {
            right = false;
        }

        if(key.attack.down){
            attack = true;
        }else {
            attack = false;
        }
    }
}
