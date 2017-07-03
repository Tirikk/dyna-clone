import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Hero extends Character {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
  private ScheduledFuture<?> moveUpHandle, moveDownHandle, moveLeftHandle, moveRightHandle;
  private String path = "src/main/resources/sprites/hero/hero-";
  private List<String> spritesMovingUp = new ArrayList<>();
  private List<String> spritesMovingDown = new ArrayList<>();
  private List<String> spritesMovingLeft = new ArrayList<>();
  private List<String> spritesMovingRight = new ArrayList<>();
  boolean moving;

  Hero() {
    alive = true;
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
        if (posX % Map.tileSize == 0 && posY % Map.tileSize == 0) {
          if (Map.isFloor(posX / Map.tileSize, posY / Map.tileSize - 1)) {
            posY--;
          }
        } else if (posY % Map.tileSize == 0 && posX % Map.tileSize < 25) {
          if (Map.isFloor(posX / Map.tileSize, posY / Map.tileSize - 1)) {
            if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
              GameEngine.offsetX += 1;
            }
            posX--;
          }
        } else if (posY % Map.tileSize == 0 && posX % Map.tileSize > 40) {
          if (Map.isFloor(posX / Map.tileSize + 1, posY / Map.tileSize - 1)) {
            if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
              GameEngine.offsetX -= 1;
            }
            posX++;
          }
        } else if (posX % Map.tileSize == 0) {
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
    moveUpHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
  }

  void moveHeroDown() {
    final Runnable mover = () -> {
      if (posY < (Map.height - 1) * Map.tileSize) {
        if (posX % Map.tileSize == 0 && posY % Map.tileSize == 0) {
          if (Map.isFloor(posX / Map.tileSize, posY / Map.tileSize + 1)) {
            posY++;
          }
        } else if (posY % Map.tileSize == 0 && posX % Map.tileSize < 25) {
          if (Map.isFloor(posX / Map.tileSize, posY / Map.tileSize + 1)) {
            if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
              GameEngine.offsetX += 1;
            }
            posX--;
          }
        } else if (posY % Map.tileSize == 0 && posX % Map.tileSize > 40) {
          if (Map.isFloor(posX / Map.tileSize + 1, posY / Map.tileSize + 1)) {
            if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
              GameEngine.offsetX -= 1;
            }
            posX++;
          }
        } else if (posX % Map.tileSize == 0) {
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
    moveDownHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
  }

  void moveHeroLeft() {
    final Runnable mover = () -> {
      if (posX > 0) {
        if (posX % Map.tileSize == 0 && posY % Map.tileSize == 0) {
          if (Map.isFloor(posX / Map.tileSize - 1, posY / Map.tileSize)) {
            if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
              GameEngine.offsetX += 1;
            }
            posX--;
          }
        } else if (posX % Map.tileSize == 0 && posY % Map.tileSize < 25) {
          if (Map.isFloor(posX / Map.tileSize - 1, posY / Map.tileSize)) {
            posY--;
          }
        } else if (posX % Map.tileSize == 0 && posY % Map.tileSize > 40) {
          if (Map.isFloor(posX / Map.tileSize - 1, posY / Map.tileSize + 1)) {
            posY++;
          }
        } else if (posY % Map.tileSize == 0) {
          if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
            GameEngine.offsetX += 1;
          }
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
    moveLeftHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
  }

  void moveHeroRight() {
    final Runnable mover = () -> {
      if (posX < (Map.width - 1) * Map.tileSize) {
        if (posX % Map.tileSize == 0 && posY % Map.tileSize == 0) {
          if (Map.isFloor(posX / Map.tileSize + 1, posY / Map.tileSize)) {
            if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
              GameEngine.offsetX -= 1;
            }
            posX++;
          }
        } else if (posX % Map.tileSize == 0 && posY % Map.tileSize < 25) {
          if (Map.isFloor(posX / Map.tileSize + 1, posY / Map.tileSize)) {
            posY--;
          }
        } else if (posX % Map.tileSize == 0 && posY % Map.tileSize > 40) {
          if (Map.isFloor(posX / Map.tileSize + 1, posY / Map.tileSize + 1)) {
            posY++;
          }
        } else if (posY % Map.tileSize == 0) {
          if (posX > 325 && posX < (Map.width -1 ) * Map.tileSize - 325) {
            GameEngine.offsetX -= 1;
          }
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
    moveRightHandle = scheduler.scheduleAtFixedRate(mover, 0, 10, TimeUnit.MILLISECONDS);
  }
}
