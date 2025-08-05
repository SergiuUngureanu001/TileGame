package net.sergiu.tilegame;

import net.sergiu.tilegame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Title", 700, 700);
        game.start();
    }
}
