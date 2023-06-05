package com.casarini.game.tiles;

import com.almasb.fxgl.entity.level.tiled.TiledMap;
import com.casarini.game.graphics.Sprite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class TileManager {

    public static ArrayList<TileMap> tm;

    public TileManager(){
        tm = new ArrayList<TileMap>();
    }
    public TileManager(String path){
        tm = new ArrayList<TileMap>();
        addTileMap(path, 16, 16);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight) {
        String imagePath;
        String imagePathTSX;

        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileCount;
        int tileColumns;
        int layers;
        String[] data = new String[10];

        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            String f = "C:/Users/stefa/IdeaProjects/Game/res/tile/mappa1.xml";
            Document doc = builder.parse(new File(f));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;

            imagePathTSX = eElement.getAttribute("source");
            imagePath = imagePathTSX.substring(0, imagePathTSX.length()-4);

            list = doc.getElementsByTagName("map");
            node = list.item(0);
            eElement = (Element) node;

            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            Sprite sprite = new Sprite("C:/Users/stefa/IdeaProjects/Game/res/tile/" + imagePath + ".png", tileWidth, tileHeight);

            tileColumns = sprite.getSpriteSheetWidth() / tileWidth;
            tileCount = tileColumns * (sprite.getSpriteSheetHeight() / tileHeight);

            list = doc.getElementsByTagName("layer");
            layers = list.getLength();

            for(int i=0; i<layers; i++){
                node = list.item(i);
                eElement = (Element) node;

                if(i <= 0){
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));

                }

                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
                System.out.println("-------------------------------------------" + data[i]);

                if(i>=1){
                    tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }else {
                    tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }
            }

        }catch (Exception e){
            System.out.println("ERROR: tileMap " + e);

        }
    }

    public void

    render (Graphics2D g){
        for(int i=0; i<tm.size(); i++){
            tm.get(i).render(g);
        }

    }
}
