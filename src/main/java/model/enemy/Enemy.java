package model.enemy;

import lombok.Getter;
import lombok.Setter;
import model.doublet.Doublet;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Enemy {

    float posX, posY;
    float desX, desY;
    EnemyType type;
    List<Doublet> targets;

    public Enemy(EnemyType type, Doublet pos){
        this.type = type;
        this.posX = this.desX = pos.getX();
        this.posY = this.desY = pos.getY();
        targets = new ArrayList<>();
    }

    public Doublet getPos(){
        return new Doublet((int)posX, (int)posY);
    }

    public void addTarget(int desX, int desY){
        targets.add(new Doublet(desX, desY));
    }
    public void addTarget(Doublet p){
        targets.add(p);
    }
    public void addTarget(List<Doublet> p){
        targets.addAll(p);
    }

    public void act(){
        if(!(posX == desX && posY == desY)){
            //===================================================================
            if(posX < desX){
                if(posX+type.speed < desX)posX+=type.speed;
                else posX = desX;
            }
            else if(posX > desX){
                if(posX-type.speed > desX)posX-=type.speed;
                else posX = desX;
            }

            if(posY < desY){
                if(posY+type.speed < desY)posY+=type.speed;
                else posY = desY;
            }
            else if(posY > desY){
                if(posY-type.speed > desY)posY-=type.speed;
                else posY = desY;
            }
            //===================================================================
        }else if(!targets.isEmpty()){
            Doublet p = targets.get(0);
            targets.remove(p);
            desX = p.getX();
            desY = p.getY();
        }
    }

    public void clearTarget() {
        targets.clear();
    }
}
