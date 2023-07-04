package com.casarini.game.entity;

import com.casarini.game.graphics.Sprite;
import com.casarini.game.util.AABB;
import com.casarini.game.util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{

    private AABB sense;
    private int r;
    private int r2 = 150;
    private Vector2f ppos = new Vector2f(0,0);
    private Vector2f ppos2 = new Vector2f(0,0);
    public Enemy(Sprite sprite, Vector2f origin, int size){
        super(sprite, origin, size);

        acc = 1f;
        maxSpeed = 3f;
        r = 135;

        bounds.setWidth(32);
        bounds.setHeight(24);
        bounds.setXOffset(16);
        bounds.setYOffset(24);

        sense = new AABB(new Vector2f(origin.x - size/2, origin.y - size/2), r, r);
    }

    public void update(Player player, Enemy enemy){
    ppos = player.getCenter(128);
    ppos2 = enemy.getCenter(64);
        if(player.getBounds().distance(player.getCenter(128), enemy.getCenter(64)) < r/2 + r2){
            //System.out.println("yes");
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

        g.setColor(Color.blue);
        g.drawOval((int) (sense.getPos().getWorldVar().x - r2), (int) (sense.getPos().getWorldVar().y - r2), r+2*r2, r+2*r2);


        g.setColor(Color.yellow);
        g.drawLine((int) ppos.getWorldVar().x, (int) ppos.getWorldVar().y, (int) ppos2.getWorldVar().x, (int) ppos2.getWorldVar().y);

        g.drawImage(ani.getImage(), (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, size, size, null);
    }
}
