import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Bomb extends GameObject {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);

  Bomb(int x, int y) {
    image = "src/main/resources/sprites/bomb/bomb-1.png";
    posX = x * 65;
    posY = y * 65;
    Map.mapMatrix[y][x] = 3;
    animate();
  }

  private void animate() {
    final Runnable animator = () -> {
      try {
        image = "src/main/resources/sprites/bomb/bomb-2.png";
        TimeUnit.MILLISECONDS.sleep(450);
        image = "src/main/resources/sprites/bomb/bomb-1.png";
        TimeUnit.MILLISECONDS.sleep(450);
        image = "src/main/resources/sprites/bomb/bomb-0.png";
        TimeUnit.MILLISECONDS.sleep(450);
        image = "src/main/resources/sprites/bomb/bomb-1.png";
        TimeUnit.MILLISECONDS.sleep(450);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    final ScheduledFuture<?> animHandle = scheduler.scheduleAtFixedRate(animator, 450, 450, TimeUnit.MILLISECONDS);
  }
}
