package net.sergiu.tilegame.states;

import net.sergiu.tilegame.Game;
import net.sergiu.tilegame.Handler;
import net.sergiu.tilegame.entities.creatures.Player;
import net.sergiu.tilegame.entities.statics.Tree;
import net.sergiu.tilegame.gfx.Assets;
import net.sergiu.tilegame.tiles.Tile;
import net.sergiu.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"worlds/world1.txt");
        handler.setWorld(world);


    }

    @Override
    public void tick() {
        world.tick();

    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
