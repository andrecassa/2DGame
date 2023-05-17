package com.casarini.game;

import com.casarini.game.util.KeyHandler;
import com.casarini.game.util.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
public class GamePanel extends JPanel{
    public static int width;
    public static int height;
    private Thread thread;
    private boolean running = false;
    private BufferedImage img;
    private Graphics2D g;
    private MouseHandler mouse;
    private KeyHandler key;
    public GamePanel(int width, int height){
        GamePanel.width = width;
        GamePanel.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocusInWindow();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(null, this::run, "GameThread");
            thread.start();
        }
    }
    public void init(){
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
    }

    public void run(){
        init();

        final int billion = 1000000000;
        final double GAME_HERTZ = 45.0;
        final double TBU = billion / GAME_HERTZ;//time before Update

        final int MUBR = 5;//Must update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 45;
        final double TTBR = billion / TARGET_FPS;//Total time before render

        int framecount = 0;
        int lastSecondTime = (int) lastUpdateTime/billion;
        int oldFrameCount = 0;

        while (running){
            double now = System.nanoTime();
            int updateCount = 0;
            while(now - lastUpdateTime > TBU && updateCount < MUBR){
                update();
                input(mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
            }

            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            framecount++;

            int thisSecond = (int) (lastUpdateTime/billion);
            if(thisSecond > lastSecondTime){
                if(framecount != oldFrameCount){
                    System.out.println("new second " + thisSecond + " " + framecount);
                    oldFrameCount = framecount;
                }
                framecount = 0;
                lastSecondTime = thisSecond;
            }
            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU){
                Thread.yield();
                try{
                    Thread.sleep(1);
                }catch(Exception e) {
                    System.out.println("Error: yielding thread");
                }

                now = System.nanoTime();
            }

        }
    }

    public void update(){

    }

    public void input(MouseHandler mouse, KeyHandler key){

    }

    public void render(){
        if(g != null){
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0,0, width, height);
        }
    }

    public void draw(){
        Graphics g2 = (Graphics2D) this.getGraphics();
        g2.drawImage(img,0, 0,  width, height, null);
        g2.dispose();
    }
}
