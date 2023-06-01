package com.casarini.game.states;

import com.casarini.game.graphics.Font;
import com.casarini.game.graphics.Sprite;
import com.casarini.game.util.KeyHandler;
import com.casarini.game.util.MouseHandler;
import com.casarini.game.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState{
    private com.casarini.game.graphics.Font font;


    public PlayState(GameStateManager gsm){
        super(gsm);
        //font = new Font("font/RetroGaming.ttf", 16, 16);

    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g) {
        java.awt.Font a = new java.awt.Font("Arial", java.awt.Font.BOLD, 24);
        g.setFont(a);
        g.setColor(Color.black);
        g.drawString("vaffanculo", 50, 50);

        //Sprite.drawArray(g, font, "tua mamma", new Vector2f(100, 100),32, 32, 0, 0);
    }


}
