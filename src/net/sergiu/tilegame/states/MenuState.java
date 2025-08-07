package net.sergiu.tilegame.states;

import net.sergiu.tilegame.Game;
import net.sergiu.tilegame.Handler;
import net.sergiu.tilegame.gfx.Assets;
import net.sergiu.tilegame.ui.ClickListener;
import net.sergiu.tilegame.ui.UIImageButton;
import net.sergiu.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(280, 300, 128, 64, Assets.btn_start, new  ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

    }

    @Override
    public void tick() {
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
       uiManager.render(g);
    }
}
