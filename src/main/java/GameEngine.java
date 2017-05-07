import javax.swing.*;
import java.awt.*;

public class GameEngine extends JComponent {
  GameEngine() {
    setPreferredSize(new Dimension(715, 715));
    setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {
    Map.drawBackGround(graphics);
  }
}
