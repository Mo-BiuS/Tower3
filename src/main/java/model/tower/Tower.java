package model.tower;

import lombok.Getter;
import model.doublet.Doublet;
import model.enemy.Enemy;
import model.enemy.EnemyList;
import model.tower.missile.Missile;
import model.tower.missile.MissileList;

@Getter
public class Tower {
    MissileList missileList;
    TowerType towerType;
    int posX, posY;
    double time;

    Tower(TowerType towerType, Doublet pos){
        this.towerType = towerType;
        this.posX = pos.getX();
        this.posY = pos.getY();
        missileList = new MissileList();
        time = System.currentTimeMillis();
    }

    public void act(EnemyList enemyList){
        if(System.currentTimeMillis()-time > towerType.reloadTime) {
            Enemy target = null;
            double dist = -1;
            for (Enemy e : enemyList.getEnemyList()) {
                double d = Math.hypot(e.getPosX() - posX, e.getPosY() - posY);
                if (d <= towerType.range && (dist == -1 || d > dist)) {
                    dist = d;
                    target = e;
                }
            }
            if (target != null) {
                missileList.getMissileList().add(new Missile(towerType.missileType, posX, posY, target));
                time = System.currentTimeMillis();
            }
        }

        missileList.act();
    }
}
