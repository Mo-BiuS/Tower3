package model.tower;

import lombok.Getter;
import model.doublet.Doublet;
import model.enemy.Enemy;
import model.enemy.EnemyList;

import java.util.ArrayList;
import java.util.List;

public class TowerList {
    @Getter
    List<Tower> towerList;

    public TowerList(){
        towerList = new ArrayList<>();
    }

    public void remove(Tower t){towerList.remove(t);}
    public void addAt(TowerType type, Doublet pos){
        towerList.add(new Tower(type, pos));
    }
    public Tower getAt(Doublet pos){
        for(Tower t : towerList){
            if(t.posX == pos.getX() && t.posY == pos.getY())return t;
        }
        return null;
    }
    public void act(EnemyList enemyList){
        towerList.forEach(e -> e.act(enemyList));
    }
    public boolean towerAt(int posX, int posY) {
        for(Tower t : towerList){
            if(t.posX == posX && t.posY == posY)return true;
        }
        return false;
    }
}
