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

        Planet mercury = new Planet();
        planets.add(mercury);
        mercury.setParent(sun);

        Planet venus = new Planet();
        planets.add(venus);
        venus.setParent(sun);

        Planet earth = new Planet();
        planets.add(earth);
        earth.setParent(sun);

        Planet mars = new Planet();
        planets.add(mars);
        mars.setParent(sun);

        Planet jupiter = new Planet();
        planets.add(jupiter);
        jupiter.setParent(sun);

        Planet saturn = new Planet();
        planets.add(saturn);
        saturn.setParent(sun);

        Planet neptune = new Planet();
        planets.add(neptune);
        neptune.setParent(sun);

        Planet uranus = new Planet();
        planets.add(uranus);
        uranus.setParent(sun);

        Planet pluto = new Planet();
        planets.add(pluto);
        pluto.setParent(sun);

        Planet moon = new Planet();
        planets.add(moon);
        moon.setParent(earth);
    }


    public static void draw(Graphics2D g) {
        g.setColor(new Color(42, 15, 73));
        g.fillRect(0, 0, 1920, 1080);
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
