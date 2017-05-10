import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Blast extends GameObject {
//  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
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
//    animate(spritesDeath, 0, 100, false, false);
  }

  public List<String> getSpritesDeath() {
    return spritesDeath;
  }
}
