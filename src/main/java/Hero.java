import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Hero extends Character {
  private final ScheduledExecutorService moveScheduler = Executors.newScheduledThreadPool(1);
  private final ScheduledExecutorService animScheduler = Executors.newScheduledThreadPool(1);
  private ScheduledFuture<?> moveUpHandle, moveDownHandle, moveLeftHandle, moveRightHandle;
  private ScheduledFuture<?> downAnim, upAnim, leftAnim, rightAnim;
  boolean moving;

  void moveHeroUp() {
    final Runnable mover = () -> {
      if (posY > 0) {
        if (posX % 65 == 0 && posY % 65 == 0) {
          if (Map.isFloor(posX / 65, posY / 65 - 1)) {
            posY--;
          }
        } else if (posY % 65 == 0 && posX % 65 < 25) {
          if (Map.isFloor(posX / 65, posY / 65 - 1)) {
            posX--;
          }
        } else if (posY % 65 == 0 && posX % 65 > 40) {
          if (Map.isFloor(posX / 65 + 1, posY / 65 - 1)) {
            posX++;
          }
        } else if (posX % 65 == 0) {
          posY--;
        }
      }
      if (!moving) {
        moveUpHandle.cancel(true);
        upAnim.cancel(true);
        image = "src/main/resources/sprites/hero/hero-up-2.png";
      }
    };
    final Runnable animator = () -> {
      try {
        image = "src/main/resources/sprites/hero/hero-up-1.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-up-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-up-3.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-up-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
      } catch (InterruptedException e) {
      }
    };
    moveUpHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
    upAnim = animScheduler.scheduleAtFixedRate(animator, 0, 300, TimeUnit.MILLISECONDS);
  }

  void moveHeroDown() {
    final Runnable mover = () -> {
      if (posY < 650) {
        if (posX % 65 == 0 && posY % 65 == 0) {
          if (Map.isFloor(posX / 65, posY / 65 + 1)) {
            posY++;
          }
        } else if (posY % 65 == 0 && posX % 65 < 25) {
          if (Map.isFloor(posX / 65, posY / 65 + 1)) {
            posX--;
          }
        } else if (posY % 65 == 0 && posX % 65 > 40) {
          if (Map.isFloor(posX / 65 + 1, posY / 65 + 1)) {
            posX++;
          }
        } else if (posX % 65 == 0) {
          posY++;
        }
      }
      if (!moving) {
        moveDownHandle.cancel(true);
        downAnim.cancel(true);
        image = "src/main/resources/sprites/hero/hero-down-2.png";
      }
    };
    final Runnable animator = () -> {
      try {
        image = "src/main/resources/sprites/hero/hero-down-1.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-down-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-down-3.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-down-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
      } catch (InterruptedException e) {
      }
    };
    moveDownHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
    downAnim = animScheduler.scheduleAtFixedRate(animator, 0, 300, TimeUnit.MILLISECONDS);
  }

  void moveHeroLeft() {
    final Runnable mover = () -> {
      if (posX > 0) {
        if (posX % 65 == 0 && posY % 65 == 0) {
          if (Map.isFloor(posX / 65 - 1, posY / 65)) {
            posX--;
          }
        } else if (posX % 65 == 0 && posY % 65 < 25) {
          if (Map.isFloor(posX / 65 - 1, posY / 65)) {
            posY--;
          }
        } else if (posX % 65 == 0 && posY % 65 > 40) {
          if (Map.isFloor(posX / 65 - 1, posY / 65 + 1)) {
            posY++;
          }
        } else if (posY % 65 == 0) {
          posX--;
        }
      }
      if (!moving) {
        moveLeftHandle.cancel(true);
        leftAnim.cancel(true);
        image = "src/main/resources/sprites/hero/hero-left-2.png";
      }
    };
    final Runnable animator = () -> {
      try {
        image = "src/main/resources/sprites/hero/hero-left-1.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-left-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-left-3.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-left-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
      } catch (InterruptedException e) {
      }
    };
    moveLeftHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
    leftAnim = animScheduler.scheduleAtFixedRate(animator, 0, 300, TimeUnit.MILLISECONDS);
  }

  void moveHeroRight() {
    final Runnable mover = () -> {
      if (posX < 650) {
        if (posX % 65 == 0 && posY % 65 == 0) {
          if (Map.isFloor(posX / 65 + 1, posY / 65)) {
            posX++;
          }
        } else if (posX % 65 == 0 && posY % 65 < 25) {
          if (Map.isFloor(posX / 65 + 1, posY / 65)) {
            posY--;
          }
        } else if (posX % 65 == 0 && posY % 65 > 40) {
          if (Map.isFloor(posX / 65 + 1, posY / 65 + 1)) {
            posY++;
          }
        } else if (posY % 65 == 0) {
          posX++;
        }
      }
      if (!moving) {
        moveRightHandle.cancel(true);
        rightAnim.cancel(true);
        image = "src/main/resources/sprites/hero/hero-right-2.png";
      }
    };
    final Runnable animator = () -> {
      try {
        image = "src/main/resources/sprites/hero/hero-right-1.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-right-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-right-3.png";
        TimeUnit.MILLISECONDS.sleep(300);
        image = "src/main/resources/sprites/hero/hero-right-2.png";
        TimeUnit.MILLISECONDS.sleep(300);
      } catch (InterruptedException e) {
      }
    };
    moveRightHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
    rightAnim = animScheduler.scheduleAtFixedRate(animator, 0, 300, TimeUnit.MILLISECONDS);
  }
}
