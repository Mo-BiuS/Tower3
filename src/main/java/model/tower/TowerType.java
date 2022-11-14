package model.tower;

import model.tower.missile.MissileType;

public enum TowerType {
    LIGHT(MissileType.SIMPLE,10,100, 1200),
    HEAVY(MissileType.SIMPLE,4,400,400);

    MissileType missileType;
    int range;
     int cost;
     int reloadTime;

    TowerType(MissileType missileType, int range, int cost, int reloadTime){
        this.range = range;
        this.missileType = missileType;
        this.cost = cost;
        this.reloadTime = reloadTime;
    }
}
