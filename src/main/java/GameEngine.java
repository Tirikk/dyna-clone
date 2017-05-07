import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameEngine extends JComponent {
  private Hero hero = new Hero();
  private ArrayList<Character> toDraw = new ArrayList<>();

  GameEngine() {
    setPreferredSize(new Dimension(715, 715));
    setVisible(true);
    generateElements();
  }

  private void generateElements() {
    hero.posX = 0;
    hero.posY = 0;
    hero.image = "src/main/resources/sprites/hero-down-2.png";
    toDraw.add(hero);
  }

  @Override
  public void paint(Graphics graphics) {
    Map.drawBackGround(graphics);
    for (Character character : toDraw) {
      PositionedImage image = new PositionedImage(character.image, character.posX, character.posY);
      image.draw(graphics);
    }
  }
}
