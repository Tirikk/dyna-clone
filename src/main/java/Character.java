import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Character extends GameObject {
  final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
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
      return (posX >= 65 && Map.isFloor(posX / 65 - 1, posY / 65));
    } else if (direction.equals("right")) {
      return (posX < 1235 && Map.isFloor(posX / 65 + 1, posY / 65));
    } else if (direction.equals("up")) {
      return (posY >= 65 && Map.isFloor(posX / 65, posY / 65 - 1));
    } else {
      return (posY < 650 && Map.isFloor(posX / 65, posY / 65 + 1));
    }
  }

  void move() {
    if (alive) {
      boolean canMove = true;
      List<String> directionsTried = new ArrayList<>();
      if (posX % 65 == 0 && posY % 65 == 0) {
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
