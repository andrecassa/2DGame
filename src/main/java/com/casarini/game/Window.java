package com.casarini.game;

import javax.swing.JFrame;
public class Window extends JFrame{
    public Window(){
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
