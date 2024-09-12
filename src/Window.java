import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KeyL keyListener = new KeyL();
    public Rect playerOne, ai, ballRect;
    public Ball ball;
    public PlayerController playerController;
    public botController botController;
    public Text scoreText;


    public Window(){
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        g2 = (Graphics2D)this.getGraphics();
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.TOOLBAR_WIDTH = this.getWidth();
        Constants.INSETS_BOTTOM = this.getInsets().bottom;
        Constants.INSETS_RIGHT = this.getInsets().right;
        Constants.INSETS_LEFT = this.getInsets().left;


        //playerOne = new Rect(Constants.H_PADDING, Constants.SCREEN_HEIGHT - Constants.H_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerOne = new Rect(Constants.SCREEN_WIDTH / 2 - 10, Constants.SCREEN_HEIGHT - Constants.H_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerController = new PlayerController(playerOne, keyListener);

        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.H_PADDING, 40,  Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        
        scoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE), Constants.TEXT_X_POS, Constants.TEXT_Y_POS);

        ballRect = new Rect(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, Constants.BALL_WIDTH, Constants.BALL_WIDTH, Constants.PADDLE_COLOR);
        ball = new Ball(ballRect, playerOne, ai, scoreText);

        botController = new botController(new PlayerController(ai), ballRect, Constants.BOT_PADDLE_SPEED);

        

    }

    public void update(double dt){ 
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg); 
        g2.drawImage(dbImage,0 , 0, this);
        
        botController.update(dt);
        playerController.update(dt);
        ball.update(dt);

    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        
        playerOne.draw(g2);
        ai.draw(g2);
        ballRect.draw(g2);
        scoreText.draw(g2);
    }

    @Override
    public void run(){
        double lastFrameTime = 0.0;
        while(true){
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update (deltaTime);

        }
    }

}
