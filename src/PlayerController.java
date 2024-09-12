
import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KeyL KeyListener;
    
    public PlayerController(Rect rect, KeyL KeyListener){
        this.rect = rect;
        this.KeyListener = KeyListener;
    }

    public PlayerController(Rect rect){
        this.rect = rect;
        this.KeyListener = null;
    }

    public void update(double dt){
        if(KeyListener != null){
            if(KeyListener.isKeyPressed(KeyEvent.VK_LEFT)){
                moveLeft(dt);
            } 
            else if(KeyListener.isKeyPressed(KeyEvent.VK_RIGHT)){
                moveRight(dt);
            }
        }
    }
    public void moveLeft(double dt){
        if(rect.x - Constants.PADDLE_SPEED * dt > Constants.INSETS_LEFT) {
            this.rect.x -= Constants.PADDLE_SPEED * dt;
        }
    }

    public void moveRight(double dt){
        if((rect.x + Constants.PADDLE_SPEED * dt) + rect.width < Constants.SCREEN_WIDTH - Constants.INSETS_RIGHT) {
            this.rect.x += Constants.PADDLE_SPEED * dt;
        }
    }
}
