package com.casarini.game.states;

import com.casarini.game.util.KeyHandler;
import com.casarini.game.util.MouseHandler;

import java.awt.*;

public abstract class GameState {

    private GameStateManager gameStateManager;
    public GameState(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D graphics2D);
}
