package model.tower.missile;

public enum MissileType {
    SIMPLE(1,0.05f);

    public final int dmg;
    public final float speed;

    MissileType(int dmg, float speed){
        this.dmg = dmg;
        this.speed = speed;
    }
}
