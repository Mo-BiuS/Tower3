package control;

import control.boardTest.InKey;
import control.boardTest.InMouse;
import model.Data;
import model.Tool;
import model.doublet.Doublet;
import model.enemy.EnemyList;
import model.enemy.EnemyType;
import model.tile.TileList;
import model.tile.TileType;
import model.tower.Tower;
import model.tower.TowerList;
import model.tower.TowerType;
import model.tower.missile.MissileList;
import processing.core.PApplet;
import view.DrawBoard;
import view.DrawData;

public class BoardTest {
    TileList tileList;
    EnemyList enemyList;
    TowerList towerList;
    MissileList missileList;

    Data data;

    InKey inKey;
    InMouse inMouse;

    DrawBoard drawBoard;
    DrawData drawData;

    long time;
    int lifepoint;

    BoardTest(int x, int y, int tx, int ty, int size){
        tileList = new TileList(tx,ty);
        tileList.fillAll(TileType.GROUND);
        tileList.lineRect(TileType.WALL,0,0,tx,ty);
        tileList.setTile(0,ty-2,TileType.ENTRY);
        tileList.setTile(tx-1,1,TileType.EXIT);
        enemyList = new EnemyList();
        towerList = new TowerList();
        missileList = new MissileList();

        drawBoard = new DrawBoard(x,y,size);
        drawData = new DrawData(10,0, 16);

        data = new Data(tileList,enemyList,towerList,missileList);

        inKey = new InKey(data);
        inMouse = new InMouse(data, drawBoard);

        data.toolType = Tool.PLACE_TILE;
        lifepoint = 100;
        data.simulation = false;
        time = System.currentTimeMillis();
    }

    public void draw(PApplet p) {
        if(System.currentTimeMillis()-time > 10){

            if(data.simulation) {
                towerList.act(enemyList);
                enemyList.act();
                lifepoint-=enemyList.calcDamage(tileList.getExitPoint());
            }
            drawBoard.clear(p);
            drawBoard.drawTiles(tileList, p);
            drawBoard.drawTower(towerList, p);
            drawBoard.drawEnemy(enemyList, p);
            drawBoard.drawCursor(p);

            drawData.drawToolData(data.toolType, data.simulation,p);
            drawData.drawLifpoint(lifepoint,p);

            time = System.currentTimeMillis();
        }
    }

    public void keyPressed(char key) {
        inKey.KeyPressed(key);
    }
    public void keyReleased(char key) {
        inKey.KeyReleased(key);
    }

    public void mousePressed(int mouseX, int mouseY, int mouseButton){
        inMouse.MousePressed(mouseX,mouseY,mouseButton, data.toolType);
    }
}
