package net.sergiu.tilegame;

import net.sergiu.tilegame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Tile Game", 600, 600);
        game.start();
    }
}
