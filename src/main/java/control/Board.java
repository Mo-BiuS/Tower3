package control;

import model.enemy.EnemyList;
import model.enemy.EnemyType;
import model.tile.TileList;
import model.tile.TileType;
import model.tower.TowerList;
import processing.core.PApplet;
import view.DrawBoard;
import view.DrawData;

public class Board {
    TileList tileList;
    EnemyList enemyList;
    TowerList towerList;

    DrawBoard drawBoard;
    DrawData drawData;

    long time;
    boolean simulation;
    int toolType;
    int lifepoint;

    Board(int x, int y, int tx, int ty, int size){
        tileList = new TileList(tx,ty);
        tileList.fillAll(TileType.GROUND);
        tileList.lineRect(TileType.WALL,0,0,tx,ty);
        tileList.setTile(0,ty-2,TileType.ENTRY);
        tileList.setTile(tx-1,1,TileType.EXIT);

        enemyList = new EnemyList();
        enemyList.setPath(tileList);

        drawBoard = new DrawBoard(x,y,size);
        drawData = new DrawData(10,0, 16);

        toolType = 0;
        lifepoint = 100;
        simulation = false;
        time = System.currentTimeMillis();
    }

    public void draw(PApplet p) {
        if(System.currentTimeMillis()-time > 10){

            if(simulation) {
                towerList.act();
                enemyList.act();
                lifepoint-=enemyList.calcDamage(tileList.getExitPoint());
            }
            drawBoard.clear(p);
            drawBoard.drawTiles(tileList, p);
            drawBoard.drawEnemy(enemyList, p);
            drawBoard.drawCursor(p);

            drawData.drawToolData(toolType, simulation,p);
            drawData.drawLifpoint(lifepoint,p);

            time = System.currentTimeMillis();
        }
    }

    public void keyPressed(char key) {
        if(key == 9){
            if(toolType == 0)toolType = 1;
            else toolType = 0;
        }
        else if(key == 32){
            simulation = !simulation;
            //SPACE
        }else if(key == 97){//a
            enemyList.addAt(EnemyType.HEAVY, tileList.getRandomEntryPoint());
            enemyList.setPath(tileList);
        }else if(key == 122){//z
            enemyList.addAt(EnemyType.MEDIUM, tileList.getRandomEntryPoint());
            enemyList.setPath(tileList);
        }else if(key == 101){//e;
            enemyList.addAt(EnemyType.LIGHT, tileList.getRandomEntryPoint());
            enemyList.setPath(tileList);
        }
    }

    public void keyReleased(char key) {

    }

    public void mousePressed(int mouseX, int mouseY, int mouseButton){
        if(toolType == 0) {
            int posX = (mouseX - drawBoard.getDx()) / drawBoard.getSize();
            int posY = (mouseY - drawBoard.getDy()) / drawBoard.getSize();
            if (mouseX < drawBoard.getDx()) posX -= 1;
            if (mouseY < drawBoard.getDy()) posY -= 1;


            TileType tileType = tileList.getTile(posX, posY);
            if (tileType != null && !enemyList.enemyAt(posX, posY)) {
                if (mouseButton == 37){
                    if(tileType == TileType.GROUND)tileList.setTile(posX, posY, TileType.WALL);
                    else if(tileType == TileType.WALL)tileList.setTile(posX, posY, TileType.EXIT);
                    else if(tileType == TileType.EXIT)tileList.setTile(posX, posY, TileType.ENTRY);
                    else if(tileType == TileType.ENTRY && tileList.getEntryPoint().size() > 1)
                        tileList.setTile(posX, posY, TileType.GROUND);

                }else if(mouseButton == 39){
                    if(tileType == TileType.ENTRY && tileList.getEntryPoint().size() > 1)
                        tileList.setTile(posX, posY, TileType.EXIT);
                    else if(tileType == TileType.EXIT)tileList.setTile(posX, posY, TileType.WALL);
                    else if(tileType == TileType.WALL)tileList.setTile(posX, posY, TileType.GROUND);
                    else if(tileType == TileType.GROUND)tileList.setTile(posX, posY, TileType.ENTRY);
                }
                enemyList.clearPath();
                enemyList.setPath(tileList);
            }
        }
        else if(toolType == 1){
            //TODO
        }
    }
}
