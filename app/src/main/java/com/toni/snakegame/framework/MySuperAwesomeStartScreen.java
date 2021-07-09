package com.toni.snakegame.framework;

import com.toni.snakegame.framework.Game;
import com.toni.snakegame.framework.Graphics;
import com.toni.snakegame.framework.Pixmap;
import com.toni.snakegame.framework.Screen;

public class MySuperAwesomeStartScreen extends Screen {
    Pixmap awesomePic;
    int x;

    public MySuperAwesomeStartScreen(Game game){
        super(game);
        awesomePic = game.getGraphics().newPixmap("data/pic.png", Graphics.PixmapFormat.RGB565);
    }

    @Override
    public void update(float deltaTime) {
        x += 50 * deltaTime;
        if(x > 100){
            x = 0;
        }
    }

    @Override
    public void present(float deltaTime) {
        game.getGraphics().clear(0);
        game.getGraphics().drawPixmap(awesomePic, x,  0, 0, 0, awesomePic.getWidth(), awesomePic.getHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        awesomePic.dispose();
    }


}
