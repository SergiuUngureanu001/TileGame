package net.sergiu.tilegame.states;

import net.sergiu.tilegame.Game;
import net.sergiu.tilegame.Handler;
import net.sergiu.tilegame.entities.creatures.Player;
import net.sergiu.tilegame.gfx.Assets;
import net.sergiu.tilegame.tiles.Tile;
import net.sergiu.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 100, 100);


    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
