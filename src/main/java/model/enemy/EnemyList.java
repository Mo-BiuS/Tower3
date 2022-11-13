package model.enemy;

import lombok.Getter;
import model.doublet.Doublet;
import model.tile.TileList;
import service.Pathfinder;

import java.util.ArrayList;
import java.util.List;

public class EnemyList {
    @Getter
    List<Enemy> enemyList;

    public EnemyList(){
        enemyList = new ArrayList<>();
    }

    public void addAt(EnemyType type, Doublet pos){
        enemyList.add(new Enemy(type,pos));
    }
    public void act() {
        enemyList.forEach(e -> e.act());
    }
    public void setPath(TileList t){
        for(Enemy e : enemyList){
            e.clearTarget();
            e.addTarget(Pathfinder.findWayOut(e.getPos(), t));
        }
    }

    public void clearPath() {
        for(Enemy e : enemyList){
            e.clearTarget();
        }
    }

    public boolean enemyAt(int posX, int posY) {
        for(Enemy e : enemyList){
            if(((int)e.posX == posX && (int)e.posY == posY) || ((int)e.desX == posX && (int)e.desY == posY))return true;
        }
        return false;
    }

    public int calcDamage(List<Doublet> exitPoint) {
        int i = 0;
        List<Enemy> killList = new ArrayList<>();
        for(Enemy e : enemyList){
            for (Doublet d : exitPoint){
                if( e.posX == d.getX() && e.posY == d.getY() && e.desX == d.getX() && e.desY == d.getY()){
                    i+=e.type.dommage;
                    killList.add(e);
                }
            }
        }
        killList.forEach(enemy -> enemyList.remove(enemy));
        return i;
    }
}
