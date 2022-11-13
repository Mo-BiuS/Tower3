package model.doublet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doublet {
    private int x, y;
    public Doublet(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "( "+x + ", " + y + " )";
    }
}
