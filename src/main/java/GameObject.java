import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

abstract class GameObject {
  private final ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(0);
  private final ScheduledExecutorService scheduler3 = Executors.newScheduledThreadPool(0);
  private ScheduledFuture<?> animHandle;
  int posX, posY;
  String image;

  void animate(List<String> spriteList, int delay, int interval, boolean withRotation, boolean
          repeating) {
    final Runnable animator = () -> {
      try {
        for (String sprite : spriteList) {
          image = sprite;
          TimeUnit.MILLISECONDS.sleep(interval);
        }
        if (withRotation) {
          image = spriteList.get(spriteList.size() - 2);
          TimeUnit.MILLISECONDS.sleep(interval);
        }
      } catch (InterruptedException e) {
      }
    };
    if (repeating) {
      animHandle = scheduler1.scheduleAtFixedRate(animator, delay, interval, TimeUnit.MILLISECONDS);
    } else {
      scheduler1.schedule(animator, delay, TimeUnit.MILLISECONDS);
    }
  }

  void cancelAnim(int delay) {
    final Runnable canceler = () -> {
      animHandle.cancel(true);
    };
    scheduler3.schedule(canceler, delay, TimeUnit.MILLISECONDS);
  }
}
