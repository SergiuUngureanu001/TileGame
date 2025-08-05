package net.sergiu.tilegame;

import net.sergiu.tilegame.display.Display;
import net.sergiu.tilegame.gfx.Assets;
import net.sergiu.tilegame.gfx.ImageLoader;
import net.sergiu.tilegame.gfx.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game implements Runnable {

    private Display display;

    public String title;
    public int width;
    public int height;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;



    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;


    }

    private void init() throws IOException {
        display = new Display(title, width, height);
        Assets.init();

    }

    private void tick() {

    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear Screen

        g.clearRect(0, 0, width, height);

        // Draw Start

        g.drawImage(Assets.grass, 10, 10, null);

        // Draw End
        bs.show();
        g.dispose();

    }

    @Override
    public void run() {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(running) {
            tick();
            render();
        }

        stop();
    }

    public synchronized void start() {
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
