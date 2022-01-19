package shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect {

    public double x;
    public double y;
    public double width;
    public double height;
    public Color color;

    public Rect(double x, double y, double width, double height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D g2){
        g2.setColor(this.color);
        g2.fill(new Rectangle2D.Double(this.x, this.y, this.width, this.height));
        //this line is for integer coordinates
        //g2.fillRect(this.x, this.y, this.width, this.height);
    }
}
