package com.casarini.game.states;

import com.casarini.game.GamePanel;
import com.casarini.game.entity.Enemy;
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
    private Enemy enemy;
    private TileManager tm;

    public static Vector2f map;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager("C:\\Users\\stefa\\IdeaProjects\\Game\\res\\tile\\mappa1.xml");
        //font = new Font("font/RetroGaming.ttf", 16, 16);
        int size = 128;
        enemy = new Enemy(new Sprite("C:\\Users\\stefa\\IdeaProjects\\Game\\res\\entity\\slime1.png", 32), new Vector2f( 300 + - size/3 + (GamePanel.width/2), -32 -8 -size/3 + (GamePanel.height/2)), size/2);
        player = new Player(new Sprite("C:\\Users\\stefa\\IdeaProjects\\Game\\src\\main\\resources\\entity\\player1.png", 48), new Vector2f( -16-8 - size/3 + (GamePanel.width/2), -32 -8 -size/3 + (GamePanel.height/2)), size);

    }

    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
        enemy.update(player, enemy);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        tm.render(g);
        player.render(g);
        enemy.render(g);

        /*java.awt.Font a = new java.awt.Font("Arial", java.awt.Font.BOLD, 24);
        g.setFont(a);
        g.setColor(Color.black);
        g.drawString("vaffanculo", 50, 50);*/


        //Sprite.drawArray(g, font, "tua mamma", new Vector2f(100, 100),32, 32, 0, 0);
    }


}
