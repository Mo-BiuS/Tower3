package model.enemy;

import processing.core.PImage;

import java.awt.*;

public enum EnemyType {

    LIGHT(Color.YELLOW, 12,6,0.05f, 1,"SET1.png"),
    MEDIUM(Color.YELLOW, 16, 12, 0.025f,2,null),
    HEAVY(Color.ORANGE, 24, 24, 0.02f,4,null);

    public final Color color;
    public final int size;
    public final int maxLifepoint;
    public final float speed;
    public final String sprite;
    public int dommage;

    EnemyType(Color color, int size, int maxLifepoint, float speed, int dommage, String sprite) {
        this.color = color;
        this.size = size;
        this.maxLifepoint = maxLifepoint;
        this.speed = speed;
        this.sprite = sprite;
        this.dommage = dommage;
    }
}
