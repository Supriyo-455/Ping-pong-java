package util;

import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public double x, y;
    public double width;
    public double height;
    public Graphics2D g2;
    public Color color = Color.WHITE;

    public Text(String text, Font font, double x, double y){
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = text.length() * font.getSize()/2 + font.getSize();
        this.height = font.getSize();
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setColor(this.color);
        g2.setFont(font);
        g2.drawString(text, (float) x, (float) y);
    }

    public void update(String updateValue){
        this.text = updateValue;
    }

    public void setFontColor(Color color){
        this.color = color;
    }
}
