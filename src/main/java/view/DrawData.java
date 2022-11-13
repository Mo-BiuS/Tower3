package view;

import processing.core.PApplet;

public class DrawData {
    int posX, posY, textSize;
    public DrawData(int posX, int posY, int textSize){
        this.posX = posX;
        this.posY = posY;
        this.textSize = textSize;
    }

    public void drawToolData(int i, boolean simulation, PApplet p){
        p.fill(255);
        p.textSize(textSize);
        String str = "";

        if(i == 0)str+="TILE_EDIT";
        else if(i == 1)str+="TOWER_EDIT";

        str +=" | ";

        if(simulation) str+="SIMULATION RUNNING";
        else str+="SIMULATION IDLE";

        p.text(str,posX,posY+textSize);
    }
    public void drawLifpoint(int i, PApplet p){
        p.fill(255);
        p.textSize(textSize);
        p.text("LIFEPOINT : " + i,posX, posY+textSize*2.2f);
    }
}
