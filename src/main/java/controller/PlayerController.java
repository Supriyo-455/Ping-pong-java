package controller;

import listeners.KL;
import shapes.Rect;
import util.Constants;

import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public final KL keyListener;

    // controller.PlayerController constructor for human player
    public PlayerController(final Rect rect, final KL keyListener){
        this.rect = rect;
        this.keyListener = keyListener;
    }

    // controller.PlayerController constructor for ai player
    public PlayerController(final Rect rect){
        this.rect = rect;
        this.keyListener = null;
    }

    public void update(final double dt){
        if(keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown(dt);
            } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(dt);
            }
        }
    }

    public void moveUp(final double dt){
        if (this.rect.y > Constants.INSETS_UP)
            this.rect.y -= (double) (Constants.PADDLE_SPEED * dt);
    }

    public void moveDown(final double dt){
        if (this.rect.y + Constants.PADDLE_HEIGHT < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)
            this.rect.y += (double) (Constants.PADDLE_SPEED * dt);
    }
}
