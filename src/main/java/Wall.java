import java.util.ArrayList;
import java.util.List;

public class Wall extends GameObject {
  static List<GameObject> wallList = new ArrayList<>();

  Wall(int x, int y) {
    image = "src/main/resources/sprites/map/wall.png";
    posX = x;
    posY = y;
  }
}
