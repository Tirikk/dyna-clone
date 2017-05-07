import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JComponent{

  BackgroundPanel() {
    setPreferredSize(new Dimension(715, 715));
    setVisible(true);
    Map.generateMatrix();
  }

  @Override
  public void paint(Graphics graphics) {
    Map.drawBackGround(graphics);
  }
}
