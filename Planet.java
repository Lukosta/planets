

import java.awt.*;

public class Planet {
    Planet parent = null;
    double radius = 10;
    double speed = 1;
    V2 pos = new V2(1920 / 2, 1080 / 2);
    int width = 100;
    int height = 100;
    Color color = Color.yellow;

    public void setColor(Color c){
        this.color = c;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setParent (Planet p){
        this.parent = p;
    }
    public  void setRadius(double r){
        this.radius = r;
    }
    public void setSpeed(double s){
        this.speed = s;
    }

    public void setPos(V2 pos) {
        this.pos = pos;
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
        g.setColor(color);
        V2 drawAt = Graphics.toScreen(myPos());
        g.fillOval(drawAt.getXInt() - width / 2,  drawAt.getYInt() - height / 2, width, height);
    }

    V2 myPos(){
        if(parent == null) return this.pos;
        return this.pos.add(parent.myPos());
    }

}
