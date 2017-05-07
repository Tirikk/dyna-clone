import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Hero extends Character {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  private ScheduledFuture<?> moverHandle;

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
      image = "src/main/resources/sprites/hero/hero-up-2.png";
      if (posY % 72 == 0) {
        moverHandle.cancel(true);
      }
    };
    moverHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
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
      image = "src/main/resources/sprites/hero/hero-down-2.png";
      if (posY % 72 == 0) {
        moverHandle.cancel(true);
      }
    };
    moverHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
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
      image = "src/main/resources/sprites/hero/hero-left-2.png";
      if (posX % 72 == 0) {
        moverHandle.cancel(true);
      }
    };
    moverHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
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
      image = "src/main/resources/sprites/hero/hero-right-2.png";
      if (posX % 72 == 0) {
        moverHandle.cancel(true);
      }
    };
    moverHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
  }
}
