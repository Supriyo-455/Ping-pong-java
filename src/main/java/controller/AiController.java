package controller;

import shapes.Circle;
import entity.Ball;

public class AiController {
    public PlayerController playerController;
    public Ball ball;
    public Circle ballEllipse;

    public AiController(final PlayerController playerController, final Ball ball){
        this.playerController = playerController;
        this.ball = ball;
        this.ballEllipse = this.ball.ellipse;
    }

    public void update(double dt){
        playerController.update(dt);
        if(this.ball.vx > 0) {
            if (ballEllipse.y < playerController.rect.y) {
                playerController.moveUp(dt);
            } else if (ballEllipse.y + ballEllipse.height > playerController.rect.y + playerController.rect.height) {
                playerController.moveDown(dt);
            }
        }
    }
}
