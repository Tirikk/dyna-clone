import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bomb extends GameObject {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
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
    posX = x * Map.tileSize;
    posY = y * Map.tileSize;
    Map.mapMatrix[y][x] = 3;
    animate(spritesMoving, 450, 450, true, true);
    cancelAnim(4000);
    detonate();
  }

  private void detonate() {
    final Runnable detonator = () -> {
      GameEngine.bombs.remove(0);
      Map.mapMatrix[posY / Map.tileSize][posX / Map.tileSize] = 0;
    };
    final Runnable blaster = () -> {
      GameEngine.blasts.add(new BlastRadius(posX, posY));
    };
    animate(spritesDeath, 4000, 100, false, false);
    scheduler.schedule(detonator, 4000 + spritesDeath.size() * 100, TimeUnit.MILLISECONDS);
    scheduler.schedule(blaster, 4000, TimeUnit.MILLISECONDS);
  }
}
