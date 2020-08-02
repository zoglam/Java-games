import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;

    // Каждые 20 миллисекунд запускать actionPerfomed
    // у данного объекта - Road
    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon("img/road.png").getImage();
    Player player = new Player();
    Thread enemy = new Thread(this);

    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Road() {
        mainTimer.start(); // запуск таймера
        enemy.start();
        addKeyListener(new MyKeyAdapter()); // добавить обработчик событий
        setFocusable(true); // Фокус на окно, чтобы принимало нажатия
    }

    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(img, player.getLayer(), 0, null);
        g.drawImage(img, player.getNextLayer(), 0, null);
        g.drawImage(player.getImg(), player.getX(), player.getY(), null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        g.drawString("Скорость: " + player.getSpeed() + " км/ч " + 
            "Пройдено пути: " + player.getDistance() + " км", 0, 595);

        for (Enemy enemy : enemies) {
            g.drawImage(enemy.getImg(), enemy.getX(), enemy.getY(), null);
        }

    }

    private void testCollisions() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy enemy = i.next();
            if (player.getRectangle().intersects(enemy.getRectangle())) {
                // JOptionPane.showMessageDialog(null, "GAME OVER!");
                // System.exit(1);
                i.remove();
            } else if (enemy.getX() >= 2400 || enemy.getX() <= -2400)
                i.remove();
            else
                enemy.move();
        }
    }

    private void testWin(){
        if (player.getDistance() >= 10000){
            JOptionPane.showMessageDialog(null, "WIN!");
            System.exit(1);
        }
    }

    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint(); // Вызывает paint()
        testCollisions();
        testWin();
    }

    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200, (rand.nextInt(470) + 10) % 470, (rand.nextInt(60) + 10) % 60, this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}