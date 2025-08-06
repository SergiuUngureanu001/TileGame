package net.sergiu.tilegame;

import net.sergiu.tilegame.display.Display;
import net.sergiu.tilegame.gfx.Assets;
import net.sergiu.tilegame.gfx.GameCamera;
import net.sergiu.tilegame.gfx.ImageLoader;
import net.sergiu.tilegame.gfx.SpriteSheet;
import net.sergiu.tilegame.input.KeyManager;
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

    private String title;
    private int width;
    private int height;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    ///  STATES
    private State gameState;
    private State menuState;

    /// INPUT
    private KeyManager keyManager;

    /// CAMERA
    private GameCamera gameCamera;

    ///  HANDLER
    private Handler handler;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        keyManager = new KeyManager();
    }

    private void init() throws IOException {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameCamera = new GameCamera(this,0, 0);
        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);

    }


    private void tick() {
        keyManager.tick();

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
                System.out.println(ticks + " FPS");
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }
    /// GETTERS AND SETTERS
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    ///  THREAD GAME
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
