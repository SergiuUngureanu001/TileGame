package net.sergiu.tilegame.entities;

import net.sergiu.tilegame.Handler;
import net.sergiu.tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity>  entities;

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
    }

    public void tick() {
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
        }
        player.tick();
    }

    public void render(Graphics g) {
        for(Entity e: entities) {
            e.render(g);
        }
        player.render(g);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    ///  GETTERS AND SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
