package view;

import lombok.Getter;
import model.enemy.Enemy;
import model.enemy.EnemyList;
import model.enemy.EnemyType;
import model.tile.TileList;
import model.tile.TileType;
import model.tower.Tower;
import model.tower.TowerList;
import model.tower.TowerType;
import model.tower.missile.Missile;
import model.tower.missile.MissileList;
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
        for (Enemy e : enemyList.getEnemyList()){
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

    public void drawTower(TowerList towerList, PApplet p) {
        p.strokeWeight(1);
        for (Tower e : towerList.getTowerList()){
            if(e.getTowerType() == TowerType.HEAVY)p.fill(150,100,100);
            else if(e.getTowerType() == TowerType.LIGHT)p.fill(255,150,150);
            p.ellipse(dx+e.getPosX()*size+size/2,
                    dy+e.getPosY()*size+size/2,
                    3*size/4, 3*size/4);

            drawMissile(e.getMissileList(), p);
        }
    }
    public void drawMissile(MissileList missileList, PApplet p){
        p.strokeWeight(1);
        p.fill(0);
        for (Missile m : missileList.getMissileList()){
            p.ellipse(dx+m.getPosX()*size+size/2,
                    dy+m.getPosY()*size+size/2,
                    4, 4);
        }
    }
}
