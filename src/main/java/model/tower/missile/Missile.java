package model.tower.missile;

import lombok.Getter;
import model.enemy.Enemy;

@Getter
public class Missile {
    MissileType missileType;
    float posX, posY;
    Enemy target;

    public Missile(MissileType missileType, int posX, int posY, Enemy target){
        this.missileType = missileType;
        this.posX = posX;
        this.posY = posY;
        this.target = target;

    }

    public void act(){
        if(target.getPosX() > posX)posX+=missileType.speed;
        else posX-=missileType.speed;

        if(target.getPosY() > posY)posY+=missileType.speed;
        else posY-=missileType.speed;
    }
}
