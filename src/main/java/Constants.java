import java.awt.Color;

public class Constants {
    // constants for screen
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String SCREEN_TITLE = "Pong Java";

    // constants for paddle
    public static final double PADDLE_WIDTH = 20;
    public static final double PADDLE_HEIGHT = 150;
    public static final Color PADDLE_COLOR_1 = Color.GREEN;
    public static final Color PADDLE_COLOR_2 = Color.RED;
    public static final double PADDLE_SPEED = 200;

    // constants for ball
    public static final double BALL_WIDTH = 20;
    public static final double BALL_HEIGHT = 20;
    public static final Color BALL_COLOR = Color.WHITE;
    public static final double BALL_SPEED = -300;

    // constants for padding
    public static final double HZ_PADDING = 60;
    public static final double VT_PADDING = 50;

    // constants for insets
    public static double INSETS_UP;
    public static double INSETS_BOTTOM;

    public static final double MAX_ANGLE = 45;
}
