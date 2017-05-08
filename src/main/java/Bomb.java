import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Bomb extends GameObject {
  //  private final ScheduledExecutorService animScheduler = Executors.newScheduledThreadPool(0);
//  private final ScheduledExecutorService detonateScheduler = Executors.newScheduledThreadPool(0);
//  private ScheduledFuture<?> animHandle;
  List<String> spritesMoving = new ArrayList<>(Arrays.asList(
          "src/main/resources/sprites/bomb/bomb-0.png",
          "src/main/resources/sprites/bomb/bomb-1.png",
          "src/main/resources/sprites/bomb/bomb-2.png"));
  List<String> spritesDeath = new ArrayList<>(Arrays.asList(
          "src/main/resources/sprites/bomb/detonate-1.png",
          "src/main/resources/sprites/bomb/detonate-2.png",
          "src/main/resources/sprites/bomb/detonate-3.png",
          "src/main/resources/sprites/bomb/detonate-4.png",
          "src/main/resources/sprites/bomb/detonate-5.png"));

  Bomb(int x, int y) {
    image = spritesMoving.get(1);
    posX = x * 65;
    posY = y * 65;
    Map.mapMatrix[y][x] = 3;
    animate(spritesMoving, 450, 450, true, true);
    //    animate();
    cancelAnim(4000);
    detonate();
  }

  //  private void animate() {
  //    final Runnable animator = () -> {
  //      try {
  //        image = "src/main/resources/sprites/bomb/bomb-2.png";
  //        TimeUnit.MILLISECONDS.sleep(450);
  //        image = "src/main/resources/sprites/bomb/bomb-1.png";
  //        TimeUnit.MILLISECONDS.sleep(450);
  //        image = "src/main/resources/sprites/bomb/bomb-0.png";
  //        TimeUnit.MILLISECONDS.sleep(450);
  //        image = "src/main/resources/sprites/bomb/bomb-1.png";
  //        TimeUnit.MILLISECONDS.sleep(450);
  //      } catch (InterruptedException e) {
  //      }
  //    };
  //    animHandle = animScheduler.scheduleAtFixedRate(animator, 450, 450, TimeUnit.MILLISECONDS);
  //  }

  private void detonate() {
//    animHandle.cancel(true);
    animate(spritesDeath, 4000, 200, false, false);
    //    final Runnable detonator = () -> {
    //      try {
    //        animHandle.cancel(true);
    //        image = "src/main/resources/sprites/bomb/detonate-1.png";
    //        TimeUnit.MILLISECONDS.sleep(200);
    //        image = "src/main/resources/sprites/bomb/detonate-2.png";
    //        TimeUnit.MILLISECONDS.sleep(200);
    //        image = "src/main/resources/sprites/bomb/detonate-3.png";
    //        TimeUnit.MILLISECONDS.sleep(200);
    //        image = "src/main/resources/sprites/bomb/detonate-4.png";
    //        TimeUnit.MILLISECONDS.sleep(200);
    //        image = "src/main/resources/sprites/bomb/detonate-5.png";
    //        TimeUnit.MILLISECONDS.sleep(200);
    //        Map.mapMatrix[posY / 65][posX / 65] = 0;
    //        GameEngine.bombAlive = false;
    //        GameEngine.toDraw.remove(GameEngine.toDraw.size() - 1);
    //      } catch (InterruptedException e) {
    //      }
    //    };
    //    detonateScheduler.schedule(detonator, 3, TimeUnit.SECONDS);
  }
}
