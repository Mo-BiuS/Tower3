package model.tower;

import lombok.Getter;
import model.doublet.Doublet;
import model.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class TowerList {
    @Getter
    List<Tower> towerList;

    TowerList(){
        towerList = new ArrayList<>();
    }

    public void addAt(TowerType type, Doublet pos){
        towerList.add(new Tower(type, pos));
    }
    public void act(){
        towerList.forEach(e -> e.act());
    }
    public boolean towerAt(int posX, int posY) {
        for(Tower t : towerList){
            if(t.posX == posX && t.posY == posY)return true;
        }
        return false;
    }
}
