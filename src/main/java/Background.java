import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Background {
  static BufferedImage bImage = new BufferedImage(Map.tileSize * Map.width, Map.tileSize * Map.height, BufferedImage
          .TYPE_INT_RGB);
  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);

  public static void drawBackground() {
    Graphics2D g2d = bImage.createGraphics();
    //    Map.drawBackGround(g2d);
    for (int column = 0; column < Map.width; column++) {
      for (int row = 0; row < Map.height; row++) {
        if (Map.mapMatrix[row][column] == 1) {
          PositionedImage image = new PositionedImage("src/main/resources/sprites/map/block.png", Map.tileSize * column, Map.tileSize *
                  row);
          image.draw(g2d);
        } else {
          PositionedImage image = new PositionedImage("src/main/resources/sprites/map/grass.png", Map.tileSize * column, Map.tileSize *
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

