package window;

import controller.AiController;
import controller.PlayerController;
import listeners.KL;
import entity.Ball;
import shapes.Circle;
import shapes.Rect;
import util.Constants;
import util.Text;
import util.Time;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect player1;
    public Rect ai;
    public Circle ballEllipse;
    public Ball ball;
    public PlayerController playerController;
    public PlayerController playerController2;
    public AiController aiController;
    public Font font;
    public Text leftScoreText, rightScoreText;
    public int leftScore, rightScore;

    private static GameWindow INSTANCE;

    private GameWindow(){
        //================= Setting up JFrame=========================
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        //===========================================================

        //===============Getting the graphics2d object=============
        this.g2 = (Graphics2D) this.getGraphics();
        //=========================================================

        //==========players and ball===========
        this.player1 = new Rect(
                Constants.HZ_PADDING,       // x
                Constants.VT_PADDING,       // y
                Constants.PADDLE_WIDTH,     // width
                Constants.PADDLE_HEIGHT,    // height
                Constants.PADDLE_COLOR_2      // color
        );
        this.ai = new Rect(
                Constants.SCREEN_WIDTH-Constants.PADDLE_WIDTH-Constants.HZ_PADDING,     // x
                Constants.VT_PADDING,                                                      // y
                Constants.PADDLE_WIDTH,                                                    // width
                Constants.PADDLE_HEIGHT,                                                   // height
                Constants.PADDLE_COLOR_1                                                     // color
        );
        this.ballEllipse = new Circle(
                Constants.SCREEN_WIDTH/2,       // x
                Constants.SCREEN_HEIGHT/2,         // y
                Constants.BALL_WIDTH,              // width
                Constants.BALL_HEIGHT,             // height
                Constants.BALL_COLOR               // color
        );
        this.ball = new Ball(ballEllipse, player1, ai);
        //========================================


        //================Player controller===============
        this.playerController = new PlayerController(this.player1, this.keyListener);
        this.playerController2 = new PlayerController(this.ai);
        //================================================

        //================AI controller==================
        this.aiController = new AiController(this.playerController2, this.ball);
        //================================================


        //============Getting the insets==================
        Constants.INSETS_UP = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;
        //================================================


        //============= Setting up the font and score ==============
        this.leftScore = 0;
        this.rightScore = 0;
        this.font = new Font(Constants.FONT, Font.PLAIN, Constants.FONT_SIZE);
        this.leftScoreText = new Text(String.valueOf(this.leftScore), font, Constants.HZ_PADDING/2.5, Constants.VT_PADDING);
        this.rightScoreText = new Text(String.valueOf(this.rightScore), font, Constants.SCREEN_WIDTH-Constants.HZ_PADDING/2, Constants.VT_PADDING);
        //==========================================================
    }

    public static GameWindow getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new GameWindow();
        }
        return INSTANCE;
    }

    public void update(double dt){
        //System.out.println("'"+dt+" seconds passed since the last frame!");
        //System.out.println(" FPS : '"+ 1/dt +"'");

        // ================================================
        //    Double buffering for smooth gameplay
        // ================================================
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);
        //==================================================

        //=== updating playerController, ai controller, ball ======
        this.playerController.update(dt);
        this.aiController.update(dt);
        this.ball.update(dt);
        //=========================================================

        //=========== updating the scores and resting the ball =============
        if(this.ball.isGameOverForLeft()){
            this.rightScore++;
            this.ball.reset();  // resting the ball
        }else if(this.ball.isGameOverForRight()){
            this.leftScore++;
            this.ball.reset();  // resting the ball
        }
        this.rightScoreText.update(String.valueOf(this.rightScore));
        this.leftScoreText.update(String.valueOf(this.leftScore));
        //==================================================================
    }

    public void draw(Graphics g){
        Graphics2D g2N = (Graphics2D) g;

        // creating a big rect of screen size to change the background color :(XD LOL)
        g2N.setColor(Color.BLACK);
        g2N.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        //===========================================================================

        // drawing font for the first time
        this.leftScoreText.draw(g2N);
        this.rightScoreText.draw(g2N);
        //===============================

        // connecting the graphics2d object with player1, ai , ball, text
        this.player1.draw(g2N);
        this.ai.draw(g2N);
        this.ballEllipse.draw(g2N);
        //===============================================================
    }

    @Override
    public void run() {
        //=========Calculating the time spent===========
        double lastTime = 0.0f;
        while(true){
            double time = Time.getTime();
            double deltaTime = time - lastTime;
            lastTime = time;

            update(deltaTime);
        }
        //==============================================
    }
}
