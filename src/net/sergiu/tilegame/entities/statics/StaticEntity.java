package net.sergiu.tilegame.entities.statics;

import net.sergiu.tilegame.Handler;
import net.sergiu.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, float x, float y, int  width,  int height) {
        super(handler, x, y, width, height);
    }
}
