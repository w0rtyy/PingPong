public class botController {
    public PlayerController playerController;
    public Rect ball;
    //private double botSpeed;

    public botController(PlayerController playerController, Rect ball){//, double botSpeed){
        this.playerController = playerController;
        this.ball = ball;
        //this.botSpeed = botSpeed;
    }

    public void update(double dt){
        playerController.update(dt);

        if(ball.x < playerController.rect.x){
            playerController.moveLeft(dt); //* botSpeed);
        }else if(ball.x + ball.height > playerController.rect.x + playerController.rect.width){
            playerController.moveRight(dt); //* botSpeed);
        }
    }
}
