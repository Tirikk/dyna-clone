import javax.swing.*;
import java.awt.*;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Dyna");
    GameEngine gameEngine = new GameEngine();
    frame.add(gameEngine);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();

    frame.addKeyListener(gameEngine);
//    Map.generateMatrix();
//    System.out.println(Map.isFloor(10, 10));
  }
}
