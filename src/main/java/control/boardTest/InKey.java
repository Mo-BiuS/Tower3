package control.boardTest;

import model.Data;
import model.Tool;
import model.enemy.EnemyList;
import model.enemy.EnemyType;
import model.tile.TileList;
import model.tower.TowerList;
import model.tower.missile.MissileList;

import static model.Tool.PLACE_TILE;
import static model.Tool.PLACE_TOWER;

public class InKey {
    Data d;

    public InKey(Data d){
        this.d = d;
    }

    public void KeyPressed(char key){
        if(key == 9){
            if(d.toolType == PLACE_TILE)d.toolType = PLACE_TOWER;
            else d.toolType = PLACE_TILE;
        }
        else if(key == 32){
            d.simulation = !d.simulation;
            //SPACE
        }else if(key == 97){//a
            d.enemyList.addAt(EnemyType.HEAVY, d.tileList.getRandomEntryPoint());
            d.enemyList.setPath(d.tileList, d.towerList);
        }else if(key == 122){//z
            d.enemyList.addAt(EnemyType.MEDIUM, d.tileList.getRandomEntryPoint());
            d.enemyList.setPath(d.tileList, d.towerList);
        }else if(key == 101){//e;
            d.enemyList.addAt(EnemyType.LIGHT, d.tileList.getRandomEntryPoint());
            d.enemyList.setPath(d.tileList, d.towerList);
        }
    }
    public void KeyReleased(char key){

    }
}
