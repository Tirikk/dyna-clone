import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class GameObject {
  final ScheduledExecutorService animScheduler = Executors.newScheduledThreadPool(0);
  ScheduledFuture<?> animHandle;
  int posX, posY;
  String image;
  List<String> spritesMoving;
  List<String> spritesDeath;

  void animateWithSchedule(List<String> spriteList, int interval) {
    final Runnable animator = () -> {
      try {
        for (String sprite : spriteList) {
          image = sprite;
          TimeUnit.MILLISECONDS.sleep(interval);
        }
        image = spriteList.get(spriteList.size() - 2);
        TimeUnit.MILLISECONDS.sleep(interval);
      } catch (InterruptedException e) {
      }
    };
    animHandle = animScheduler.scheduleAtFixedRate(animator, interval, interval, TimeUnit.MILLISECONDS);
  }
}
