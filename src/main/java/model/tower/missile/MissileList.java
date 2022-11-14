package model.tower.missile;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MissileList {
    @Getter
    List<Missile> missileList;

    public MissileList(){
        missileList = new ArrayList<>();
    }
    public void act() {
        missileList.forEach(m -> m.act());
    }
}
