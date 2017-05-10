import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Wall extends GameObject {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
  static List<Wall> wallList = new ArrayList<>();
  public static List<String> spritesDeath = Arrays.asList(
          "src/main/resources/sprites/map/wall-blown-1.png",
          "src/main/resources/sprites/map/wall-blown-2.png",
          "src/main/resources/sprites/map/wall-blown-3.png",
          "src/main/resources/sprites/map/wall-blown-4.png",
          "src/main/resources/sprites/map/wall-blown-5.png",
          "src/main/resources/sprites/map/wall-blown-6.png",
          "src/main/resources/sprites/map/wall-blown-7.png");

  Wall(int x, int y) {
    image = "src/main/resources/sprites/map/wall.png";
    posX = x;
    posY = y;
  }

  public static Wall getWall(int x, int y) {
    Wall wantedWall = new Wall(0, 0);
    for (Wall wall : wallList) {
      if (wall.posX == x && wall.posY == y) {
        wantedWall = wall;
      }
    }
    return wantedWall;
  }

  public void destroyWall() {
    PositionedImage picture = new PositionedImage("src/main/resources/sprites/map/grass.png", posX, posY);
    Graphics2D g2d = Background.bImage.createGraphics();
    final Runnable changer = () -> {
      for (int k = 0; k < 8; k++) {
        PositionedImage image2 = new PositionedImage(image, posX, posY);
        picture.draw(g2d);
        image2.draw(g2d);
        try {
          TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      picture.draw(g2d);
      Map.mapMatrix[posY / 65][posX / 65] = 0;
    };
    scheduler.schedule(changer, 0, TimeUnit.MILLISECONDS);
  }
}
