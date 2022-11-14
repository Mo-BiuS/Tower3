package model;

import model.enemy.EnemyList;
import model.tile.TileList;
import model.tower.TowerList;
import model.tower.missile.MissileList;

public class Data {
    public EnemyList enemyList;
    public TileList tileList;
    public TowerList towerList;
    public MissileList missileList;
    public Tool toolType;
    public Boolean simulation;

    public Data(TileList tileList, EnemyList enemyList, TowerList towerList, MissileList missileList) {
        this.enemyList = enemyList;
        this.tileList = tileList;
        this.towerList = towerList;
        this.missileList = missileList;
    }
}
