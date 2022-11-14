package control.boardTest;

import model.Data;
import model.Tool;
import model.doublet.Doublet;
import model.tile.TileType;
import model.tower.Tower;
import model.tower.TowerType;
import view.DrawBoard;

public class InMouse {
    Data d;
    DrawBoard drawBoard;

    public InMouse(Data d, DrawBoard drawBoard){
        this.d = d;
        this.drawBoard = drawBoard;
    }

    public void MousePressed(int mouseX, int mouseY, int mouseButton, Tool toolType){
        int posX = (mouseX - drawBoard.getDx()) / drawBoard.getSize();
        int posY = (mouseY - drawBoard.getDy()) / drawBoard.getSize();
        if (mouseX < drawBoard.getDx()) posX -= 1;
        if (mouseY < drawBoard.getDy()) posY -= 1;

        TileType tileType = d.tileList.getTile(posX, posY);
        if(toolType == Tool.PLACE_TILE && tileType != null && !d.enemyList.enemyAt(posX, posY) && !d.towerList.towerAt(posX,posY)) {
            if (mouseButton == 37){
                if(tileType == TileType.GROUND)d.tileList.setTile(posX, posY, TileType.WALL);
                else if(tileType == TileType.WALL)d.tileList.setTile(posX, posY, TileType.EXIT);
                else if(tileType == TileType.EXIT)d.tileList.setTile(posX, posY, TileType.ENTRY);
                else if(tileType == TileType.ENTRY && d.tileList.getEntryPoint().size() > 1)
                    d.tileList.setTile(posX, posY, TileType.GROUND);

            }else if(mouseButton == 39){
                if(tileType == TileType.ENTRY && d.tileList.getEntryPoint().size() > 1)
                    d.tileList.setTile(posX, posY, TileType.EXIT);
                else if(tileType == TileType.EXIT)d.tileList.setTile(posX, posY, TileType.WALL);
                else if(tileType == TileType.WALL)d.tileList.setTile(posX, posY, TileType.GROUND);
                else if(tileType == TileType.GROUND)d.tileList.setTile(posX, posY, TileType.ENTRY);
            }
            d.enemyList.clearPath();
            d.enemyList.setPath(d.tileList,d.towerList);
        }
        else if(toolType == Tool.PLACE_TOWER  && tileType == TileType.GROUND && !d.enemyList.enemyAt(posX, posY)){
            Tower t = d.towerList.getAt(new Doublet(posX,posY));
            if(mouseButton == 37){
                if(t == null){
                    d.towerList.addAt(TowerType.LIGHT, new Doublet(posX,posY));
                }
                else if(t.getTowerType() == TowerType.LIGHT){
                    d.towerList.remove(t);
                    d.towerList.addAt(TowerType.HEAVY, new Doublet(posX,posY));
                }
                else if(t.getTowerType() == TowerType.HEAVY){
                    d.towerList.remove(t);
                }
            }
            else if(mouseButton == 39){
                if(t == null){
                    d.towerList.addAt(TowerType.HEAVY, new Doublet(posX,posY));
                }
                else if(t.getTowerType() == TowerType.LIGHT){
                    d.towerList.remove(t);
                }
                else if(t.getTowerType() == TowerType.HEAVY){
                    d.towerList.remove(t);
                    d.towerList.addAt(TowerType.LIGHT, new Doublet(posX,posY));
                }
            }
            d.enemyList.clearPath();
            d.enemyList.setPath(d.tileList,d.towerList);
        }
    }
    public void MouseReleased(){

    }
}
