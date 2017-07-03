import java.util.ArrayList;
import java.util.List;

public class Blast extends GameObject {
  private String path = "src/main/resources/sprites/bomb/blast/";
  private List<String> spritesDeath = new ArrayList<>();
  private String direction;

  Blast(int x, int y, String direction, boolean middle) {
    this.posX = x;
    this.posY = y;
    this.direction = direction;
    for (int i = 1; i < 6; i++) {
      if (middle) {
        spritesDeath.add(path.concat(direction + "-mid-" + i + ".png"));
      } else {
        spritesDeath.add(path.concat(direction + "-end-" + i + ".png"));
      }
    }
    image = spritesDeath.get(0);
  }

  public List<String> getSpritesDeath() {
    return spritesDeath;
  }
}
