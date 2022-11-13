package model.tile;

import java.awt.*;

public enum TileType {
    GROUND( Color.white,    true, false, false),
    ENTRY(  Color.blue,     true, true, false),
    EXIT(   Color.red,      true, false, true),
    WALL(   Color.black,    false, false, false);

    public final Color color;
    public final Boolean traversable;
    public final Boolean entryPoint;
    public final Boolean exitPoint;

    TileType(Color color, Boolean traversable, Boolean entryPoint, Boolean exitPoint){
        this.color = color;
        this.traversable = traversable;
        this.entryPoint = entryPoint;
        this.exitPoint = exitPoint;
    }
}
