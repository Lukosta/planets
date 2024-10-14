/* Максимально простой способ запустить нестатичную графику и управление с клавиатуры в своей программе.
 * В угоду простоте ниже используются нелучшие практики программирования
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Graphics extends JFrame implements KeyListener {
    static ArrayList<Planet> planets = new ArrayList<>();
    static {
        Planet sun = new Planet();
        planets.add(sun);
        sun.setSpeed(0);

        Planet mercury = new Planet();
        planets.add(mercury);
        mercury.setParent(sun);
        mercury.setPos(new V2(175, 200));
        mercury.setWidth(50);
        mercury.setHeight(50);
        mercury.setSpeed(0.2);
        mercury.setColor(new Color(189, 38, 83));


        Planet venus = new Planet();
        planets.add(venus);
        venus.setParent(sun);
        venus.setPos(new V2(300,  325));
        venus.setWidth(60);
        venus.setHeight(60);
        venus.setSpeed(0.2);
        venus.setColor(new Color(189, 126, 38));

        Planet earth = new Planet();
        planets.add(earth);
        earth.setParent(sun);
        earth.setPos(new V2(450,  475));
        earth.setWidth(62);
        earth.setHeight(62);
        earth.setSpeed(0.2);
        earth.setColor(new Color(189, 126, 38));


        Planet mars = new Planet();
        planets.add(mars);
        mars.setParent(sun);
        mars.setPos(new V2(600,  625));
        mars.setWidth(64);
        mars.setHeight(64);
        mars.setSpeed(0.2);
        mars.setColor(new Color(189, 126, 38));


        Planet jupiter = new Planet();
        planets.add(jupiter);
        jupiter.setParent(sun);
        jupiter.setPos(new V2(750,  775));
        jupiter.setWidth(60);
        jupiter.setHeight(60);
        jupiter.setSpeed(0.2);
        jupiter.setColor(new Color(189, 126, 38));


        Planet saturn = new Planet();
        planets.add(saturn);
        saturn.setParent(sun);
        saturn.setPos(new V2(300,  325));
        saturn.setWidth(60);
        saturn.setHeight(60);
        saturn.setSpeed(0.2);
        saturn.setColor(new Color(189, 126, 38));


        Planet neptune = new Planet();
        planets.add(neptune);
        neptune.setParent(sun);
        neptune.setPos(new V2(300,  325));
        neptune.setWidth(60);
        neptune.setHeight(60);
        neptune.setSpeed(0.2);
        neptune.setColor(new Color(189, 126, 38));


        Planet uranus = new Planet();
        planets.add(uranus);
        uranus.setParent(sun);
        uranus.setPos(new V2(300,  325));
        uranus.setWidth(60);
        uranus.setHeight(60);
        uranus.setSpeed(0.2);
        uranus.setColor(new Color(189, 126, 38));


        Planet pluto = new Planet();
        planets.add(pluto);
        pluto.setParent(sun);
        pluto.setPos(new V2(300,  325));
        pluto.setWidth(60);
        pluto.setHeight(60);
        pluto.setSpeed(0.2);
        pluto.setColor(new Color(189, 126, 38));


        Planet moon = new Planet();
        planets.add(moon);
        moon.setParent(earth);
        moon.setPos(new V2(300,  325));
        moon.setWidth(60);
        moon.setHeight(60);
        moon.setSpeed(0.2);
        moon.setColor(new Color(189, 126, 38));

    }


    public static void draw(Graphics2D g) {
        g.setColor(new Color(42, 15, 73));
        g.fillRect(0, 0, 1950, 1080);
        for( Planet p : planets){
            p.update(1.0 / 60);
        }

        for( Planet p : planets){
            p.drawMe(g);

        }
    }

    //Функция вызывается при нажатии клавиши один раз, и при удерживании несколько раз в секунду
    public void keyPressed(KeyEvent e) {
    }

    static final int w = 1920;
    static final int h = 1080;

    //магический код позволяющий всему работать, лучше не трогать
    public static void main(String[] args) throws InterruptedException {
        Graphics jf = new Graphics();
        jf.setSize(w, h);//размер экрана
        jf.setUndecorated(false);//показать заголовок окна
        jf.setTitle("Моя супер программа");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.createBufferStrategy(2);
        jf.addKeyListener(jf);
        //в бесконечном цикле рисуем новый кадр
        while (true) {
            long frameLength = 1000 / 60; //пытаемся работать из рассчета  60 кадров в секунду
            long start = System.currentTimeMillis();
            BufferStrategy bs = jf.getBufferStrategy();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, jf.getWidth(), jf.getHeight());
            draw(g);

            bs.show();
            g.dispose();

            long end = System.currentTimeMillis();
            long len = end - start;
            if (len < frameLength) {
                Thread.sleep(frameLength - len);
            }
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    //Вызывается когда клавиша отпущена пользователем, обработка события аналогична keyPressed
    public void keyReleased(KeyEvent e) {

    }
}
