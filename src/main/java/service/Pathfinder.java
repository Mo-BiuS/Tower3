package service;

import model.doublet.Doublet;
import model.tile.TileList;
import model.tower.TowerList;

import java.util.ArrayList;
import java.util.List;

public class Pathfinder {
    public static List<Doublet> findWayOut(Doublet from, TileList inside, TowerList tower){
        int[][] pathMap = new int[inside.getSizeX()][inside.getSizeY()];
        List<Doublet> listExit = inside.getExitPoint();
        Doublet exit = null;

        pathMap[from.getX()][from.getY()] = 1;

        int i = 0;
        boolean exitFound = false;
        boolean exitNotFound = false;

        while(!exitFound && !exitNotFound){
            i++;

            for(Doublet pos : listExit){
                if(pathMap[pos.getX()][pos.getY()] == i){
                    exitFound = true;
                    exit = pos;
                }
            }
            if(!exitFound) {
                exitNotFound = true;
                for (int y = 0; y < inside.getSizeY(); y++) {
                    for (int x = 0; x < inside.getSizeX(); x++) {
                        if (pathMap[x][y] == i) {
                            if (inside.canGoNorth(x, y) && pathMap[x][y - 1] == 0  && !tower.towerAt(x,y-1)) {
                                pathMap[x][y - 1] = i + 1;
                                exitNotFound = false;
                            }
                            if (inside.canGoSouth(x, y) && pathMap[x][y + 1] == 0  && !tower.towerAt(x,y+1)) {
                                pathMap[x][y + 1] = i + 1;
                                exitNotFound = false;
                            }
                            if (inside.canGoEast(x, y) && pathMap[x + 1][y] == 0  && !tower.towerAt(x+1,y)) {
                                pathMap[x + 1][y] = i + 1;
                                exitNotFound = false;
                            }
                            if (inside.canGoWest(x, y) && pathMap[x - 1][y] == 0  && !tower.towerAt(x-1,y)) {
                                pathMap[x - 1][y] = i + 1;
                                exitNotFound = false;
                            }
                        }
                    }
                }
            }
        }
        if(!exitNotFound) {
            List<Doublet> reversePath = new ArrayList<>();
            while (i > 0) {
                reversePath.add(exit);
                if (inside.canGoNorth(exit.getX(), exit.getY()) && pathMap[exit.getX()][exit.getY() - 1] == i)
                    exit = new Doublet(exit.getX(), exit.getY() - 1);
                else if (inside.canGoSouth(exit.getX(), exit.getY()) && pathMap[exit.getX()][exit.getY() + 1] == i)
                    exit = new Doublet(exit.getX(), exit.getY() + 1);
                else if (inside.canGoEast(exit.getX(), exit.getY()) && pathMap[exit.getX() + 1][exit.getY()] == i)
                    exit = new Doublet(exit.getX() + 1, exit.getY());
                else if (inside.canGoWest(exit.getX(), exit.getY()) && pathMap[exit.getX() - 1][exit.getY()] == i)
                    exit = new Doublet(exit.getX() - 1, exit.getY());
                i--;
            }
            List<Doublet> path = new ArrayList<>();
            for (int j = reversePath.size() - 1; j >= 0; j--) {
                exit = reversePath.get(j);
                path.add(new Doublet(exit.getX(), exit.getY()));
            }
            return path;
        }else return new ArrayList<Doublet>();
    }
}
