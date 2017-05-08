import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
  static BufferedImage bImage = new BufferedImage(715, 715, BufferedImage.TYPE_INT_RGB);

  public static void drawImage() {
    Graphics2D g2d = bImage.createGraphics();
    Map.drawBackGround(g2d);
//    for (GameObject wall : Wall.wallList) {
//      PositionedImage image = new PositionedImage(wall.image, wall.posX, wall.posY);
//      image.draw(g2d);
//    }
  }
}
