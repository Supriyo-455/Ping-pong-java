package window;

import listeners.KL;
import listeners.ML;
import util.Constants;
import util.Text;
import util.Time;

import javax.swing.*;
import java.awt.*;

public class MenuWindow extends JFrame implements Runnable {


    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Font font, titleFont;
    public Text startGameText, endGameText, gameTitleText;

    public MenuWindow() {
        //================= Setting up JFrame=========================
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        //============================================================

        //===============Getting the graphics2d object=============
        this.g2 = (Graphics2D) this.getGraphics();
        //=========================================================

        //============Getting the insets==================
        Constants.INSETS_UP = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;
        //================================================

        //============= Setting up the font and score ==============
        this.font = new Font(Constants.FONT, Font.PLAIN, Constants.FONT_SIZE);
        this.startGameText = new Text(Constants.START_GAME_TEXT, font, Constants.SCREEN_WIDTH/2 - Constants.START_GAME_TEXT_OFFSET, Constants.SCREEN_HEIGHT/2);
        this.endGameText = new Text(Constants.EXIT_GAME_TEXT, font, Constants.SCREEN_WIDTH/2 - Constants.END_GAME_TEXT_OFFSET, Constants.SCREEN_HEIGHT/2 + 50);
        this.titleFont = new Font(Constants.FONT, Font.PLAIN, Constants.PING_PONG_TEXT_SIZE);
        this.gameTitleText = new Text(Constants.PING_PONG_TEXT, titleFont, Constants.SCREEN_WIDTH/2 - Constants.PING_PONG_TEXT_OFFSET, Constants.SCREEN_HEIGHT/2 - Constants.PING_PONG_TEXT_OFFSET_Y);
        //==========================================================
    }

    public void update(double dt) throws InterruptedException {

        // ================================================
        //    Double buffering for smooth gameplay
        // ================================================
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);
        //==================================================

        //============= checking for mouse events ==============
        if(
                mouseListener.getMouseX() >= startGameText.x
                        && mouseListener.getMouseX() <= startGameText.x + startGameText.width
                        && mouseListener.getMouseY() >= startGameText.y - startGameText.font.getSize()
                        && mouseListener.getMouseY() <= startGameText.y + startGameText.height - startGameText.font.getSize()
        ){
            startGameText.setFontColor(new Color(0xCE7A7A));
            if(mouseListener.isMousePressed()) {
                enterGame();
            }
        }else if(
                mouseListener.getMouseX() >= endGameText.x
                        && mouseListener.getMouseX() <= endGameText.x + endGameText.width
                        && mouseListener.getMouseY() >= endGameText.y - endGameText.font.getSize()
                        && mouseListener.getMouseY() <= endGameText.y + endGameText.height - endGameText.font.getSize()
        ){
            endGameText.setFontColor(new Color(0xCE7A7A));
            if(mouseListener.isMousePressed()) {
                System.exit(0);
            }
        }else{
            startGameText.setFontColor(Color.WHITE);
            endGameText.setFontColor(Color.WHITE);
        }
        //======================================================
    }

    private void enterGame() throws InterruptedException {
        GameWindow window = GameWindow.getINSTANCE();
        Thread t1 = new Thread(window);
        t1.start();
        t1.join();
    }

    public void draw(Graphics g){
        Graphics2D g2N = (Graphics2D) g;

        // creating a big rect of screen size to change the background color :(XD LOL)
        g2N.setColor(Color.BLACK);
        g2N.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        //===========================================================================

        // drawing menu text for the first time
        this.startGameText.draw(g2N);
        this.endGameText.draw(g2N);
        this.gameTitleText.draw(g2N);
        //=====================================
    }


    @Override
    public void run() {
        //=========Calculating the time spent===========
        double lastTime = 0.0f;
        while(true){
            double time = Time.getTime();
            double deltaTime = time - lastTime;
            lastTime = time;

            try {
                update(deltaTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //==============================================
    }
}
