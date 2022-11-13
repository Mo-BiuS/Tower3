package model.tower;

import lombok.Getter;
import model.doublet.Doublet;

@Getter
public class Tower {
    TowerType towerType;
    long time;
    int posX, posY;

    Tower(TowerType towerType, Doublet pos){
        this.towerType = towerType;
        this.posX = pos.getX();
        this.posY = pos.getY();
        time = System.currentTimeMillis();
    }

    public void act(){
        if(System.currentTimeMillis() - time > towerType.reloadTime){

        }
    }
}
