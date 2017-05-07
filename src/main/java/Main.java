import javax.swing.*;
import java.awt.*;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Dyna");
//    Background background = new Background();
    GameEngine gameEngine = new GameEngine();
//    frame.add(background);
    frame.add(gameEngine);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();

    frame.addKeyListener(gameEngine);
  }
}
