import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Enemy {

    private BufferedImage img = null;

    private int x = 0;
    private int y = 0;

    private int speed = 0;
    private Road road;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Image getImg() {
        return (Image) this.img;
    }

    public Enemy(int x, int y, int speed, Road road) {
        try {
            this.img = ImageIO.read(new File("img/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.road = road;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, img.getWidth() - 15, img.getHeight() - 15);
    }

    public void move() {
        x = x - road.player.getSpeed() + speed;
    }

}