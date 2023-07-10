package com.casarini.game.entity;

import com.casarini.game.GamePanel;
import com.casarini.game.graphics.Sprite;
import com.casarini.game.states.PlayState;
import com.casarini.game.util.KeyHandler;
import com.casarini.game.util.MouseHandler;
import com.casarini.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

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

    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth(36);
        bounds.setHeight(20);
        bounds.setXOffset(48);
        bounds.setYOffset(96);
    }
    private void move(){
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

    private void animate() {
        if(attack){
            if(currentAnimation == IDLELEFT || currentAnimation == LEFT || ani.getDelay() == -1){
                setAnimationSpec(ATTACKLEFT, sprite.getSpriteArray(ATTACKLEFT), 4, 4);
            }
            else if(currentAnimation == IDLERIGHT || currentAnimation == RIGHT || ani.getDelay() == -1){
                setAnimationSpec(ATTACKRIGHT, sprite.getSpriteArray(ATTACKRIGHT), 4, 4);
            }
            else if(currentAnimation == IDLEUP || currentAnimation == UP || ani.getDelay() == -1){
                setAnimationSpec(ATTACKUP, sprite.getSpriteArray(ATTACKUP), 4, 4);
            }
            else if(currentAnimation == IDLEDOWN || currentAnimation == DOWN || ani.getDelay() == -1){
                setAnimationSpec(ATTACKDOWN, sprite.getSpriteArray(ATTACKDOWN), 4, 4);
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
    public void resetPosition(){
        System.out.println("Reseting player");
        pos.x = GamePanel.width / 2 - 32;
        PlayState.map.x = 0;

        pos.y = GamePanel.height / 2 - 32;
        PlayState.map.y = 0;
    }

    @Override
    public void update(){
        super.update();
        move();
        if(!tc.collisionTile(dx, 0)) {
            PlayState.map.x += dx;
            pos.x += dx;
        }
        if(!tc.collisionTile(0, dy)){
            PlayState.map.y += dy;
            pos.y += dy;
        }

        animate();
        setHitBoxDirectionPlayer();
        ani.update();
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
