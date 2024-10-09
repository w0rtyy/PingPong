
public class Ball{
    public Rect rect;
    public Rect downPaddle, topPaddle;
    public Text scoreText;

    //velocity in the x, y direction
    private double vy = 100.0;
    private double vx = 0.0;

    public Ball(Rect rect, Rect downPaddle, Rect topPaddle, Text scoreText){
        this.rect = rect;
        this.downPaddle = downPaddle;
        this.topPaddle = topPaddle;
        this.scoreText = scoreText;
    }

    public double calculateNewVelocityAngle(Rect paddle) {
        double relativeIntersectX = ((paddle.x + (paddle.width / 2.0)) - (this.rect.x + (this.rect.width / 2.0)));
        double normalIntersectX = relativeIntersectX / (paddle.width / 2.0);
        double theta = normalIntersectX * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt){
        
        if(vy >= 0){
            if(this.rect.y + this.rect.height >= this.downPaddle.y && 
                this.rect.y <= this.downPaddle.y &&
                this.rect.x + this.rect.width >= this.downPaddle.x &&
                this.rect.x <= this.downPaddle.x + downPaddle.width){
                
                    double theta = calculateNewVelocityAngle(downPaddle);
                    double newVy = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                    double newVx = (-Math.sin(theta)) * Constants.BALL_SPEED;
                    
                    double oldSign = Math.signum(vy);
                    this.vy = newVy * (-1.0 * oldSign);
                    this.vx = newVx;

                    int score = Integer.parseInt(scoreText.text);
                    score++;
                    scoreText.text = "" + score; 
                    
            }else if(this.rect.y + this.rect.height > this.downPaddle.y + this.downPaddle.height)
                System.out.println("Game Over!!");
        }
        else if(vy < 0){
            if(this.rect.y <= this.topPaddle.y + this.topPaddle.height && 
                this.rect.x >= this.topPaddle.x && 
                this.rect.x + this.rect.width <= this.topPaddle.x + topPaddle.width){   

                    double theta = calculateNewVelocityAngle(topPaddle);
                    double newVy = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                    double newVx = (-Math.sin(theta)) * Constants.BALL_SPEED;
                    
                    double oldSign = Math.signum(vy);
                    this.vy = newVy * (-1.0 * oldSign);
                    this.vx = newVx;
            }
            
        }

        if(vx >= 0){
            if(this.rect.x + this.rect.width > Constants.SCREEN_WIDTH){
                this.vx *= -1.0;
            }
        }
        else if(vx < 0){
            if(this.rect.x < 0)
                this.vx *= -1.0;
        }

        //postion = position + velocity
        //velocity = velocity + accelertaion
        this.rect.x += vx * dt;
        this.rect.y += vy * dt;

    
    }
}
