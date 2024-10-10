

import java.awt.*;

public class Planet {
    Planet parent = null;
    double radius = 10;
    double speed = 1;
    static int x = 1920;
    static int y = 1080;

    V2 pos = new V2(0, 0);


    void update(double dt) {
        pos = pos.rotate(dt * speed);
    }

    void drawMe(Graphics2D g){
        g.setColor(Color.yellow);
        g.fillOval((x / 2) - 25,  (y / 2) - 25, 100, 100);
    }

    V2 myPos(){
        if(parent == null) return this.pos;
        return this.pos.add(parent.myPos());
    }

}
