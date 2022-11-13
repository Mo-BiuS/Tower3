package model.tower;

public enum TowerType {
    LIGHT(1,100, 500),
    HEAVY(4,400,1000);

     int dmg;
     int cost;
     int reloadTime;

    TowerType(int dmg, int cost, int reloadTime){
        this.dmg = dmg;
        this.cost = cost;
        this.reloadTime = reloadTime;
    }
}
