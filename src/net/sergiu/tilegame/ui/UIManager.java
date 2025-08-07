package net.sergiu.tilegame.ui;

import net.sergiu.tilegame.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<>();
    }

    public void tick() {
        for(UIObject object : objects){
            object.tick();
        }
    }

    public void render(Graphics g) {
        for(UIObject object : objects){
            object.render(g);
        }
    }

    public void onMouseMoved(MouseEvent e) {
        for(UIObject object : objects){
            object.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for(UIObject object : objects){
            object.onMouseRelease(e);
        }
    }

    public void addObject(UIObject uiObject) {
        objects.add(uiObject);
    }

    public void removeObject(UIObject uiObject) {
        objects.remove(uiObject);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
}
