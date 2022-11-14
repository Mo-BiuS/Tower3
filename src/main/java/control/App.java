package control;

import processing.core.PApplet;
import processing.core.PFont;

public class App extends PApplet {
    int sizeX = 800, sizeY = 600;
    BoardTest b;

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "control.App" };
        PApplet.main(appletArgs);
    }

    @Override
    public void settings() {
        size(sizeX, sizeY);
        b = new BoardTest(50,50,14,10,50);
    }

    public void setup(){
        PFont font = createFont("fixedsys.otf",16);
        this.textFont(font);
    }

    @Override
    public void draw() {
        b.draw(this);
    }

    @Override
    public void keyPressed(){
        b.keyPressed(this.key);
    }
    @Override
    public void keyReleased(){
        b.keyReleased(this.key);
    }

    @Override
    public  void mousePressed(){
        b.mousePressed(mouseX,mouseY, mouseButton);
    }
}