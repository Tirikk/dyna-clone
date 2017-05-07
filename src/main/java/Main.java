import javax.swing.*;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("RPG Game");
    GameEngine gameEngine = new GameEngine();
    frame.add(gameEngine);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();

    frame.addKeyListener(gameEngine);
  }
}
