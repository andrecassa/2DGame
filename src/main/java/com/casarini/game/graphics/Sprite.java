package com.casarini.game.graphics;

import com.casarini.game.graphics.Font;
import com.casarini.game.states.PlayState;
import com.casarini.game.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Sprite {
    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 16;
    private int w;
    private int h;
    private int wSprite;
    private int hSprite;

    public Sprite(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        LoadSpriteArray();
    }
    public Sprite(String file, int TILE_SIZE){
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        LoadSpriteArray();
    }

    public Sprite(String file, int w, int h){
        this.w = w;
        this.h = h;
        System.out.println("loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        LoadSpriteArray();
    }
    public void SetSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i){
        w = i;
        wSprite = SPRITESHEET.getWidth() / w;
    }
    public void setHeight(int i){
        h = i;
        hSprite = SPRITESHEET.getHeight() / h;
    }
    public int getWidth(){return w;}
    public int getHeight(){return h;}
    public int getSpriteSheetWidth() { return SPRITESHEET.getWidth(); }
    public int getSpriteSheetHeight() { return SPRITESHEET.getHeight(); }

    private BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        String a = "C:\\Users\\stefa\\IdeaProjects\\Game\\res\\tile\\map1\\map1.png";
        try{
            sprite = ImageIO.read(new File(file));
        }catch(Exception e){
            System.out.println("ERROR: impossibile caricare il file " + file);
        }
        return sprite;
    }
    public void LoadSpriteArray(){
        spriteArray = new BufferedImage[hSprite][wSprite];

        for(int y=0; y<hSprite; y++){
            for(int x=0; x<wSprite; x++){
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }
    public BufferedImage getSpriteSheet(){return SPRITESHEET;}
    public BufferedImage getSprite(int x, int y){return SPRITESHEET.getSubimage(x*w, y*h, w, h);}
    public BufferedImage[] getSpriteArray(int i){return spriteArray[i];}
    public BufferedImage[] getSpriteArrayAttack(int i){
        return spriteArray[i];
    }
    public BufferedImage[][] getSpriteArray2(int i){return spriteArray;}
    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height,
                                 int xOffset, int yOffset){
        float x = pos.x;
        float y = pos.y;

        for(int i=0; i<img.size(); i++){
            g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
        }
        x += xOffset;
        y += yOffset;
    }
    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height,
                                 int xOffset, int yOffset){
        float x = pos.x;
        float y = pos.y;

        for(int i=0; i<word.length(); i++){
            if(word.charAt(i) != 32){
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }





}