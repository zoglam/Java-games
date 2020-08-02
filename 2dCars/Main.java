import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cars"); // Создаем фрейм и ему название через констуктор
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Событие на крестик у окна
        frame.setSize(1100, 635); // Размеры окна
        frame.setResizable(false); // Нельзя изменять окно
        frame.add(new Road()); // Нарисовать дорогу
        frame.setVisible(true); // Установка видимости окна
    }
}