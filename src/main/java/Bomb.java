import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bomb extends GameObject {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
  private String path = "src/main/resources/sprites/bomb/bomb-";
  private List<String> spritesMoving = new ArrayList<>();
  private List<String> spritesDeath = new ArrayList<>();

  Bomb(int x, int y) {
    for (int i = 1; i < 6; i++) {
      if (i < 4) {
        spritesMoving.add(path.concat(i + ".png"));
      }
      spritesDeath.add(path.concat("detonate-" + i + ".png"));
    }
    image = spritesMoving.get(1);
    posX = x * 65;
    posY = y * 65;
    Map.mapMatrix[y][x] = 3;
    animate(spritesMoving, 450, 450, true, true);
    cancelAnim(4000);
    detonate();
  }

  private void detonate() {
    final Runnable detonator = () -> {
      GameEngine.bombs.remove(0);
      Map.mapMatrix[posY / 65][posX / 65] = 0;
    };
    animate(spritesDeath, 4000, 100, false, false);
    scheduler.schedule(detonator, 4000 + spritesDeath.size() * 100, TimeUnit.MILLISECONDS);
  }
}
