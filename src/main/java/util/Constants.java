package util;

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

    // constants for font
    public static final String FONT = "Monospace Regular";
    public static final int FONT_SIZE = 20;

    // menu screen text
    public static final String PING_PONG_TEXT = "PING-PONG";
    public static final int PING_PONG_TEXT_SIZE = 100;
    public static final double PING_PONG_TEXT_OFFSET = 285;
    public static final double PING_PONG_TEXT_OFFSET_Y = 100;
    public static final String START_GAME_TEXT = "Start Game";
    public static final double START_GAME_TEXT_OFFSET = 50;
    public static final String SETTINGS_TEXT = "Settings";
    public static final String EXIT_GAME_TEXT = "Exit Game";
    public static final double END_GAME_TEXT_OFFSET = 45;
}
