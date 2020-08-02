import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Player {

    public static final int MAX_SPEED = 200;
    public static final int MAX_TOP = 10;
    public static final int MAX_BOTTOM = 470;

    private BufferedImage[] img = new BufferedImage[3];

    private enum Direction {
        UP, DOWN, NONE
    }

    private Direction player_direction = Direction.NONE;
    private int speed = 4; // Скорость
    private int boost = 0; // Ускорение
    private int rotation = 0; // Поворот
    private int distance = 0; // Расстояние

    private int x = 50; // Координата x машины
    private int y = 150; // Координата y машины

    private int layer = 0; // первый слой
    private int nextlayer = 1200; // следующий слой

    public Player() {
        try {
            img[0] = ImageIO.read(new File("img/greenCar.png"));
            img[1] = ImageIO.read(new File("img/greenCar_left.png"));
            img[2] = ImageIO.read(new File("img/greenCar_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getLayer() {
        return this.layer;
    }

    public int getNextLayer() {
        return this.nextlayer;
    }

    public Image getImg() {
        switch (player_direction) {
            case UP:
                return (Image) this.img[1];
            case DOWN:
                return (Image) this.img[2];
            default:
                return (Image) this.img[0];
        }

    }

    public void move() {
        distance += speed;
        speed += boost;
        if (speed >= MAX_SPEED)
            speed = MAX_SPEED;
        else if (speed <= 0)
            speed = 0;

        y -= rotation;
        if (y <= MAX_TOP)
            y = MAX_TOP;
        else if (y >= MAX_BOTTOM)
            y = MAX_BOTTOM;

        if (nextlayer - speed <= 0) {
            layer = 0;
            nextlayer = 1200;
        }

        layer -= speed;
        nextlayer -= speed;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, img[0].getWidth() - 15, img[0].getHeight() - 15);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            this.rotation = 10;
            player_direction = Direction.UP;
        } else if (key == KeyEvent.VK_S) {
            this.rotation = -10;
            player_direction = Direction.DOWN;
        } else if (key == KeyEvent.VK_A)
            this.boost = -4;
        else if (key == KeyEvent.VK_D)
            this.boost = 2;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            this.rotation = 0;
            player_direction = Direction.NONE;
        }

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D)
            this.boost = 0;
    }
}