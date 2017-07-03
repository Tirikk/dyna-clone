import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BlastRadius {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
  int posX, posY;
  static int range = 2;
  List<Blast> radius = new ArrayList<>();

  public BlastRadius(int x, int y) {
    this.posX = x;
    this.posY = y;
    populateRadius();
    detonate();
  }

  void populateRadius() {
    for (int i = range; i > 0; i--) {
      try {
        if (Map.isFloor(posX / Map.tileSize - i, posY / Map.tileSize) && unblockedWall(posX - i * Map.tileSize, posY)) {
          radius.add(new Blast(posX - i * Map.tileSize, posY, "left", i < range));
        } else if (Map.isWall(posX / Map.tileSize - i, posY / Map.tileSize) && unblockedWall(posX - i * Map.tileSize, posY)) {
          Wall blown = Wall.getWall(posX - i * Map.tileSize, posY);
          if (!blown.destroyed) {
            blown.animate(Wall.spritesDeath, 0, 100, false, false);
            blown.destroyWall();
          }
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / Map.tileSize, posY / Map.tileSize - i) && unblockedWall(posX, posY - i * Map.tileSize)) {
          radius.add(new Blast(posX, posY - i * Map.tileSize, "up", i < range));
        } else if (Map.isWall(posX / Map.tileSize, posY / Map.tileSize - i) && unblockedWall(posX, posY - i * Map.tileSize)) {
          Wall blown = Wall.getWall(posX, posY - i * Map.tileSize);
          if (!blown.destroyed) {
            blown.animate(Wall.spritesDeath, 0, 100, false, false);
            blown.destroyWall();
          }
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / Map.tileSize + i, posY / Map.tileSize) && unblockedWall(posX + i * Map.tileSize, posY)) {
          radius.add(new Blast(posX + i * Map.tileSize, posY, "right", i < range));
        } else if (Map.isWall(posX / Map.tileSize + i, posY / Map.tileSize) && unblockedWall(posX + i * Map.tileSize, posY)) {
          Wall blown = Wall.getWall(posX + i * Map.tileSize, posY);
          if (!blown.destroyed) {
            blown.animate(Wall.spritesDeath, 0, 100, false, false);
            blown.destroyWall();
          }
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / Map.tileSize, posY / Map.tileSize + i) && unblockedWall(posX, posY + i * Map.tileSize)) {
          radius.add(new Blast(posX, posY + i * Map.tileSize, "down", i < range));
        } else if (Map.isWall(posX / Map.tileSize, posY / Map.tileSize + i) && unblockedWall(posX, posY + i * Map.tileSize)) {
          Wall blown = Wall.getWall(posX, posY + i * Map.tileSize);
          if (!blown.destroyed) {
            blown.animate(Wall.spritesDeath, 0, 100, false, false);
            blown.destroyWall();
          }
        }
      } catch (IndexOutOfBoundsException e) {
      }
    }
  }

  private void detonate() {
    for (Blast blast : radius) {
      blast.animate(blast.getSpritesDeath(), 0, 100, false, false);
    }
    final Runnable remover = () -> {
      GameEngine.blasts.remove(0);
    };
    scheduler.schedule(remover, 500, TimeUnit.MILLISECONDS);
  }

  private boolean unblockedWall(int x, int y) {
    boolean unblocked = true;
    try {
      if (posY != y) {
        for (int i = 1; i < Math.abs(posY - y) / Map.tileSize; i++) {
          if (!Map.isFloor(posX / Map.tileSize, posY / Map.tileSize - (posY - y) / Math.abs(posY - y) * i)) {
            unblocked = false;
          }
        }
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (posX != x) {
        for (int i = 1; i < Math.abs(posX - x) / Map.tileSize; i++) {
          if (!Map.isFloor(posX / Map.tileSize - (posX - x) / Math.abs(posX - x) * i, posY / Map.tileSize)) {
            unblocked = false;
          }
        }
      }
    } catch (IndexOutOfBoundsException e) {
    }
    return unblocked;
  }
}
