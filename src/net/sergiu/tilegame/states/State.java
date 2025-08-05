package net.sergiu.tilegame.states;

import java.awt.*;

public abstract class State {


    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    ///  CLASS
    public abstract void tick();
    public abstract void render(Graphics g);
}
