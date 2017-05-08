import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Hero extends Character {
  private final ScheduledExecutorService moveScheduler = Executors.newScheduledThreadPool(1);
  private ScheduledFuture<?> moveUpHandle, moveDownHandle, moveLeftHandle, moveRightHandle;
  private String path = "src/main/resources/sprites/hero/hero-";
  private List<String> spritesMovingUp = new ArrayList<>();
  private List<String> spritesMovingDown = new ArrayList<>();
  private List<String> spritesMovingLeft = new ArrayList<>();
  private List<String> spritesMovingRight = new ArrayList<>();
  private List<String> spritesDeath = new ArrayList<>();
  boolean moving;
  boolean alive = true;

  Hero() {
    for (int i = 1; i < 9; i++) {
      if(i < 4) {
        spritesMovingUp.add(path.concat("up-" + i + ".png"));
        spritesMovingDown.add(path.concat("down-" + i + ".png"));
        spritesMovingLeft.add(path.concat("left-" + i + ".png"));
        spritesMovingRight.add(path.concat("right-" + i + ".png"));
      }
      spritesDeath.add(path.concat("dead-" + i + ".png"));
    }
  }

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
      if (!moving || !alive) {
        moveUpHandle.cancel(true);
        cancelAnim(0);
        image = "src/main/resources/sprites/hero/hero-up-2.png";
      }
    };
    animate(spritesMovingUp, 0, 300, true, true);
    moveUpHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
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
      if (!moving || !alive) {
        moveDownHandle.cancel(true);
        cancelAnim(0);
        image = "src/main/resources/sprites/hero/hero-down-2.png";
      }
    };
    animate(spritesMovingDown, 0, 300, true, true);
    moveDownHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
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
      if (!moving || !alive) {
        moveLeftHandle.cancel(true);
        cancelAnim(0);
        image = "src/main/resources/sprites/hero/hero-left-2.png";
      }
    };
    animate(spritesMovingLeft, 0, 300, true, true);
    moveLeftHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
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
      if (!moving || !alive) {
        moveRightHandle.cancel(true);
        cancelAnim(0);
        image = "src/main/resources/sprites/hero/hero-right-2.png";
      }
    };
    animate(spritesMovingRight, 0, 300, true, true);
    moveRightHandle = moveScheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
  }

  void die() {
    alive = false;
//    final Runnable animator = () -> {
//      try {
//        image = "src/main/resources/sprites/hero/hero-dead-1.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        image = "src/main/resources/sprites/hero/hero-dead-2.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        image = "src/main/resources/sprites/hero/hero-dead-3.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        image = "src/main/resources/sprites/hero/hero-dead-4.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        image = "src/main/resources/sprites/hero/hero-dead-5.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        image = "src/main/resources/sprites/hero/hero-dead-6.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        image = "src/main/resources/sprites/hero/hero-dead-7.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        image = "src/main/resources/sprites/hero/hero-dead-8.png";
//        TimeUnit.MILLISECONDS.sleep(300);
//        GameEngine.toDraw.remove(GameEngine.toDraw.size() - 1);
//      } catch (InterruptedException e) {
//      }
//    };
//    animScheduler.schedule(animator, 0, TimeUnit.MILLISECONDS);
    animate(spritesDeath, 0, 300, false, false);
  }
}
