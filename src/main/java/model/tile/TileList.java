package model.tile;

import lombok.Getter;
import model.doublet.Doublet;

import java.util.ArrayList;
import java.util.List;

public class TileList {
    @Getter
    private final int sizeX, sizeY;
    TileType[][] tileList;

    public TileList(int x, int y){
        this.sizeX = x;
        this.sizeY = y;
        tileList = new TileType[x][y];
    }

    public boolean isIn(int posX, int posY){
        return(posX >= 0 && posY >= 0 && posX < sizeX && posY < sizeY);
    }
    public boolean isIn(Doublet pos){
        return isIn(pos.getX(),pos.getY());
    }

    public void setTile(int posX, int posY, TileType t){
        if(isIn(posX,posY)) tileList[posX][posY] = t;
        else System.out.println("Error tileList setTile out of bound : "+ posX + "/" + sizeX + " | " + posY + "/" + sizeY);
    }
    public void setTile(Doublet pos, TileType t){setTile(pos.getX(), pos.getY(), t);}

    public TileType getTile(int posX, int posY){
        if(isIn(posX,posY))return tileList[posX][posY];
        else return null;
    }
    public TileType getTile(Doublet pos){return getTile(pos.getX(), pos.getY());}

    public boolean canGoEast(int x, int y){
        return isIn(x+1,y) && tileList[x+1][y].traversable;
    }
    public boolean canGoWest(int x, int y){
        return isIn(x-1,y) && tileList[x-1][y].traversable;
    }
    public boolean canGoSouth(int x, int y){
        return isIn(x,y+1) && tileList[x][y+1].traversable;
    }
    public boolean canGoNorth(int x, int y){
        return isIn(x,y-1) && tileList[x][y-1].traversable;
    }


    public List<Doublet> getEntryPoint(){
        List<Doublet> p = new ArrayList<>();
        for(int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (tileList[x][y].entryPoint)
                    p.add(new Doublet(x, y));
            }
        }
        return p;
    }
    public List<Doublet> getExitPoint(){
        List<Doublet> p = new ArrayList<>();
        for(int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (tileList[x][y].exitPoint)
                    p.add(new Doublet(x, y));
            }
        }
        return p;
    }

    public void fillAll(TileType tile){
        for(int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                tileList[x][y] = tile;
            }
        }
    }

    public void fillRect(TileType tile, int px, int py, int sx, int sy){
        for(int x = px; x < sx; x++){
            for (int y = py; y < sy; y++){
                tileList[x][y] = tile;
            }
        }
    }

    public void lineRect(TileType tile, int px, int py, int sx, int sy){
        for (int i = px; i < sx; i++){
            tileList[i][py] = tile;
            tileList[i][sy-1] = tile;
        }
        for (int i = py; i < sy; i++){
            tileList[px][i] = tile;
            tileList[sx-1][i] = tile;
        }
    }

    public Doublet getRandomEntryPoint() {
        List<Doublet> doubletList =  getEntryPoint();
        return doubletList.get((int) (Math.random()*doubletList.size()));
    }
}
