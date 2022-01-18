public class Ball {
    public Circle ellipse;
    public Rect leftPaddle;
    public Rect rightPaddle;
    private boolean isGameOverForLeft = false;
    private boolean isGameOverForRight = false;

    // velocity x and y directions
    public double vy = 1.0;
    public double vx = -1.0 * Constants.BALL_SPEED;

    public Ball(Circle ellipse, Rect leftPaddle, Rect rightPaddle) {
        this.ellipse = ellipse;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void update(double dt) {

        // checks for ball and paddle collision
        if (this.vx < 0) {
            if (
                    this.ellipse.x <= this.leftPaddle.x + this.leftPaddle.width    // x direction check
                            && this.ellipse.x >= this.leftPaddle.x                 // x direction check
                            && this.ellipse.y >= this.leftPaddle.y                            // y direction check
                            && this.ellipse.y <= this.leftPaddle.y + this.leftPaddle.height   // y direction check
            ) {
                // then bounce the ball
                applyForce(leftPaddle);
                this.vx *= -1;
            }else if(this.ellipse.x + this.ellipse.width < this.leftPaddle.x){
                this.isGameOverForLeft = true;
            }
        } else if (this.vx > 0) {
            if (
                    this.ellipse.x + this.ellipse.width >= this.rightPaddle.x    // x direction check
                            && this.ellipse.x <= this.rightPaddle.x + this.rightPaddle.width   // x direction check
                            && this.ellipse.y >= this.rightPaddle.y                            // y direction check
                            && this.ellipse.y <= this.rightPaddle.y + this.rightPaddle.height   // y direction check
            ) {
                // then bounce the ball
                applyForce(rightPaddle);
                this.vx *= -1;
            }else if(this.ellipse.x > this.rightPaddle.x + this.rightPaddle.width){
                this.isGameOverForRight = true;
            }
        }

        // checks for ball inside screen
        if(this.vy > 0){
            if(this.ellipse.y + this.ellipse.height > Constants.SCREEN_HEIGHT){
                this.vy *= -1;
            }
        }else if(this.vy < 0){
            if(this.ellipse.y < Constants.INSETS_UP){
                this.vy *= -1;
            }
        }

        // In physics
        // position = position + velocity
        // velocity = velocity + acceleration
        this.ellipse.x += vx * dt;
        this.ellipse.y += vy * dt;
    }

    public double calculateAngle(Rect paddle){
        double relativeIntersectY = (paddle.y + (paddle.height/ 2.0)) - (this.ellipse.y + (this.ellipse.height/2));
        double normalIntersectY = relativeIntersectY / (paddle.height/2.0);
        double theta = normalIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void applyForce(Rect paddle){
        double theta = calculateAngle(paddle);
        double newVx = Math.abs(Math.cos(theta)) * Constants.BALL_SPEED;
        double newVy = (Math.sin(theta)) * Constants.BALL_SPEED;

        double oldSign = Math.signum(this.vx);
        this.vx = newVx * (-1.0 * oldSign);
        this.vy = newVy;
    }

    public boolean isGameOverForLeft() {
        return this.isGameOverForLeft;
    }

    public boolean isGameOverForRight() {
        return this.isGameOverForRight;
    }

    public void reset() {
        this.ellipse.x = Constants.SCREEN_WIDTH/2;
        this.ellipse.y = Constants.SCREEN_HEIGHT/2;
        this.isGameOverForLeft = false;
        this.isGameOverForRight = false;
    }
}
