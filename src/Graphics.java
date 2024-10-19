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
        mercury.setPos(new V2(175, 175));
        mercury.setWidth(10);
        mercury.setHeight(10);
        mercury.setSpeed(0.4);
        mercury.setRadius(4);
        mercury.setColor(new Color(189, 38, 83));


        Planet venus = new Planet();
        planets.add(venus);
        venus.setParent(sun);
        venus.setPos(new V2(300,  300));
        venus.setWidth(12);
        venus.setHeight(12);
        venus.setSpeed(0.4);
        venus.setColor(new Color(189, 126, 38));

        Planet earth = new Planet();
        planets.add(earth);
        earth.setParent(sun);
        earth.setPos(new V2(450,  450));
        earth.setWidth(14);
        earth.setHeight(14);
        earth.setSpeed(0.6);
        earth.setColor(new Color(189, 126, 38));


        Planet mars = new Planet();
        planets.add(mars);
        mars.setParent(sun);
        mars.setPos(new V2(600,  600));
        mars.setWidth(16);
        mars.setHeight(16);
        mars.setSpeed(0.7);
        mars.setColor(new Color(189, 126, 38));


        Planet jupiter = new Planet();
        planets.add(jupiter);
        jupiter.setParent(sun);
        jupiter.setPos(new V2(750,  750));
        jupiter.setWidth(18);
        jupiter.setHeight(18);
        jupiter.setSpeed(0.5);
        jupiter.setColor(new Color(189, 126, 38));


        Planet saturn = new Planet();
        planets.add(saturn);
        saturn.setParent(sun);
        saturn.setPos(new V2(850,  850));
        saturn.setWidth(20);
        saturn.setHeight(20);
        saturn.setSpeed(0.3);
        saturn.setColor(new Color(189, 126, 38));


        Planet neptune = new Planet();
        planets.add(neptune);
        neptune.setParent(sun);
        neptune.setPos(new V2(950,  950));
        neptune.setWidth(13);
        neptune.setHeight(13);
        neptune.setSpeed(0.3);
        neptune.setColor(new Color(189, 126, 38));


        Planet uranus = new Planet();
        planets.add(uranus);
        uranus.setParent(sun);
        uranus.setPos(new V2(1050,  1050));
        uranus.setWidth(12);
        uranus.setHeight(12);
        uranus.setSpeed(0.4);
        uranus.setColor(new Color(189, 126, 38));


        Planet pluto = new Planet();
        planets.add(pluto);
        pluto.setParent(sun);
        pluto.setPos(new V2(1200,  1200));
        pluto.setWidth(10);
        pluto.setHeight(10);
        pluto.setSpeed(0.3);
        pluto.setColor(new Color(189, 126, 38));


        Planet moon = new Planet();
        planets.add(moon);
        moon.setParent(earth);
        moon.setPos(new V2(100,  100));
        moon.setWidth(5);
        moon.setHeight(5);
        moon.setSpeed(0.8);
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
    public static V2 toScreen(V2 world){
        return new V2(((world.getX() - lookAt.getX()) * zoom) + w / 2 , ((world.getY() - lookAt.getY()) * zoom) + h / 2);
    }

    //Функция вызывается при нажатии клавиши один раз, и при удерживании несколько раз в секунду
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_1){
            zoom = zoom + 0.1;
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            zoom = zoom - 0.1;
        }
    }
    static V2 lookAt = new V2(1920 / 2, 1080 / 2);
    static final int w = 1920;
    static final int h = 1080;
    static double zoom = 1;

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
