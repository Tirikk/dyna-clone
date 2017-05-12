import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Character extends GameObject {
  final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
  String direction;
  boolean alive;
  List<String> spritesMoving = new ArrayList<>();
  List<String> spritesDeath = new ArrayList<>();

  String generateDirection() {
    int i = (int) (Math.random() * 4);
    if (i == 0) {
      direction = "left";
    } else if (i == 1) {
      direction = "up";
    } else if (i == 2) {
      direction = "right";
    } else {
      direction = "down";
    }
    return direction;
  }

  private boolean checkDirection() {
    if (direction.equals("left")) {
      return (posX >= Map.tileSize && Map.isFloor(posX / Map.tileSize - 1, posY / Map.tileSize));
    } else if (direction.equals("right")) {
      return (posX < (Map.width - 1 ) * Map.tileSize && Map.isFloor(posX / Map.tileSize + 1, posY / Map.tileSize));
    } else if (direction.equals("up")) {
      return (posY >= Map.tileSize && Map.isFloor(posX / Map.tileSize, posY / Map.tileSize - 1));
    } else {
      return (posY < (Map.height - 1) * Map.tileSize && Map.isFloor(posX / Map.tileSize, posY / Map.tileSize + 1));
    }
  }

  void move() {
    if (alive) {
      boolean canMove = true;
      List<String> directionsTried = new ArrayList<>();
      if (posX % Map.tileSize == 0 && posY % Map.tileSize == 0) {
        if ((int) (Math.random() * 4) == 0) {
          generateDirection();
        }
        while (!checkDirection() && canMove) {
          directionsTried.add(generateDirection());
          if (directionsTried.contains("left") && directionsTried.contains("right") && directionsTried.contains("up")
                  && directionsTried.contains("down")) {
            canMove = false;
          }
        }
        if (canMove) {
          moveInDirection();
        }
      } else {
        moveInDirection();
      }
    }
  }

  private void moveInDirection() {
    if (direction.equals("left")) {
      posX--;
    } else if (direction.equals("right")) {
      posX++;
    } else if (direction.equals("up")) {
      posY--;
    } else {
      posY++;
    }
  }

  void die() {
    final Runnable remover = () -> {
      GameEngine.characters.remove(GameEngine.getCharacterIndex(posX, posY));
      if (getClass().equals(Monster.class)) {
        GameEngine.enemyList.remove(GameEngine.getEnemyIndex(posX, posY));
      }
    };
    cancelAnim(0);
    alive = false;
    animate(spritesDeath, 0, 300, false, false);
    scheduler.schedule(remover, spritesDeath.size() * 300, TimeUnit.MILLISECONDS);
  }
}
