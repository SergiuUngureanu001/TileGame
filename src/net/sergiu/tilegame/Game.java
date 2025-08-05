package net.sergiu.tilegame;

import net.sergiu.tilegame.display.Display;
import net.sergiu.tilegame.gfx.Assets;
import net.sergiu.tilegame.gfx.ImageLoader;
import net.sergiu.tilegame.gfx.SpriteSheet;
import net.sergiu.tilegame.states.GameState;
import net.sergiu.tilegame.states.MenuState;
import net.sergiu.tilegame.states.State;

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

    ///  STATES
    private State gameState;
    private State menuState;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;


    }

    private void init() throws IOException {
        display = new Display(title, width, height);
        Assets.init();

        gameState = new GameState();
        menuState = new MenuState();
        State.setState(gameState);

    }


    private void tick() {
       if(State.getState() != null) {
           State.getState().tick();
       }
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

        if(State.getState() != null) {
            State.getState().render(g);
        }

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

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                delta--;
                ticks++;
            }

            if(timer >= 1000000000) {
                System.out.println(ticks + " ticks");
                ticks = 0;
                timer = 0;
            }
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
