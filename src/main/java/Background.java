import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Background {
  static BufferedImage bImage = new BufferedImage(1300, 715, BufferedImage.TYPE_INT_RGB);
  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);

  public static void drawBackground() {
    Graphics2D g2d = bImage.createGraphics();
    //    Map.drawBackGround(g2d);
    for (int column = 0; column < 20; column++) {
      for (int row = 0; row < 11; row++) {
        if (Map.mapMatrix[row][column] == 1) {
          PositionedImage image = new PositionedImage("src/main/resources/sprites/map/block.png", 65 * column, 65 *
                  row);
          image.draw(g2d);
        } else {
          PositionedImage image = new PositionedImage("src/main/resources/sprites/map/grass.png", 65 * column, 65 *
                  row);
          image.draw(g2d);
        }
      }
    }
  }

  public static void drawWalls() {
    Graphics2D g2d = bImage.createGraphics();
    for (GameObject wall : Wall.wallList) {
      PositionedImage image = new PositionedImage(wall.image, wall.posX, wall.posY);
      image.draw(g2d);
    }
  }
}

