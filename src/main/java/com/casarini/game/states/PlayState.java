package com.casarini.game.states;

import com.casarini.game.entity.Player;
import com.casarini.game.graphics.Sprite;
import com.casarini.game.tiles.TileManager;
import com.casarini.game.util.KeyHandler;
import com.casarini.game.util.MouseHandler;
import com.casarini.game.util.Vector2f;

import java.awt.*;

import static com.casarini.game.tiles.TileManager.tm;

public class PlayState extends GameState{
    private com.casarini.game.graphics.Font font;
    private Player player;
    private TileManager tm;

    public PlayState(GameStateManager gsm){
        super(gsm);
        //tm = new TileManager("C:\\Users\\stefa\\IdeaProjects\\Game\\res\\tile\\mappa1.png");
        //font = new Font("font/RetroGaming.ttf", 16, 16);
        player = new Player(new Sprite("C:\\Users\\stefa\\IdeaProjects\\Game\\src\\main\\resources\\entity\\player1.png"), new Vector2f(300, 300), 128);

    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        //tm.render(g);
        player.render(g);

        /*java.awt.Font a = new java.awt.Font("Arial", java.awt.Font.BOLD, 24);
        g.setFont(a);
        g.setColor(Color.black);
        g.drawString("vaffanculo", 50, 50);*/


        //Sprite.drawArray(g, font, "tua mamma", new Vector2f(100, 100),32, 32, 0, 0);
    }


}
