

import java.awt.*;

public class Planet {
    Planet parent = null;
    double radius = 10;
    double speed = 1;
    int x = 1920;
    int y = 1080;
    V2 pos = new V2(0, 0);


    void setParent (Planet p){
        parent = p;
    }
    void setRadius(double r){
        radius = r;
    }
    void setSpeed(double s){
        speed = s;
    }

    public Planet getParent() {
        return parent;
    }

    public double getRadius() {
        return radius;
    }

    public double getSpeed() {
        return speed;
    }



    void update(double dt) {
        pos = pos.rotate(dt * speed);
    }

    void drawMe(Graphics2D g){
        g.setColor(Color.yellow);
        g.fillOval((x / 2) - 50,  (y / 2) - 50, 100, 100);
    }

    V2 myPos(){
        if(parent == null) return this.pos;
        return this.pos.add(parent.myPos());
    }

}
