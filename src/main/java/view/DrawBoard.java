package view;

import lombok.Getter;
import model.enemy.Enemy;
import model.enemy.EnemyList;
import model.enemy.EnemyType;
import model.tile.TileList;
import model.tile.TileType;
import processing.core.PApplet;

import java.awt.*;
import java.util.List;
@Getter
public class DrawBoard {
    int dx, dy, size;

    public DrawBoard(int dx, int dy, int size){
        this.dx=dx;
        this.dy=dy;
        this.size=size;
    }

    public void drawTiles(TileList b, PApplet p){
        p.strokeWeight(1);
        for (int x = 0; x < b.getSizeX(); x++){
            for(int y = 0; y < b.getSizeY(); y++){
                TileType t = b.getTile(x,y);
                p.fill(t.color.getRed(),t.color.getGreen(),t.color.getBlue());
                p.rect(x*size+dx,y*size+dy,size,size);
            }
        }
    }
    public void drawEnemy(EnemyList enemyList, PApplet p){
        p.strokeWeight(1);
        for (int i = 0; i < enemyList.getEnemyList().size(); i++){
            Enemy e = enemyList.getEnemyList().get(i);
            EnemyType t = e.getType();
            p.fill(t.color.getRed(),t.color.getGreen(),t.color.getBlue());
            p.ellipse(dx+e.getPosX()*size+size/2,
                      dy+e.getPosY()*size+size/2,
                         t.size, t.size);
        }
    }
    public void drawCursor(PApplet p){
        p.fill(0,0,0,0);
        p.strokeWeight(4);
        int posX = (p.mouseX-dx)/size;
        int posY = (p.mouseY-dy)/size;
        if(p.mouseX < dx)posX-=1;
        if(p.mouseY < dy)posY-=1;
        p.rect(posX*size+dx,posY*size+dy,size,size);
    }

    public void clear(PApplet p) {
        p.background(50);
    }
}
