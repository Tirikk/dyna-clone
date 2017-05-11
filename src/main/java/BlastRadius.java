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
        if (Map.isFloor(posX / 65 - i, posY / 65) && unblockedWall(posX - i * 65, posY)) {
          radius.add(new Blast(posX - i * 65, posY, "left", i < range));
        } else if (Map.isWall(posX / 65 - i, posY / 65) && unblockedWall(posX - i * 65, posY)) {
          Wall blown = Wall.getWall(posX - i * 65, posY);
          if (!blown.destroyed) {
            blown.animate(Wall.spritesDeath, 0, 100, false, false);
            blown.destroyWall();
          }
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / 65, posY / 65 - i) && unblockedWall(posX, posY - i * 65)) {
          radius.add(new Blast(posX, posY - i * 65, "up", i < range));
        } else if (Map.isWall(posX / 65, posY / 65 - i) && unblockedWall(posX, posY - i * 65)) {
          Wall blown = Wall.getWall(posX, posY - i * 65);
          if (!blown.destroyed) {
            blown.animate(Wall.spritesDeath, 0, 100, false, false);
            blown.destroyWall();
          }
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / 65 + i, posY / 65) && unblockedWall(posX + i * 65, posY)) {
          radius.add(new Blast(posX + i * 65, posY, "right", i < range));
        } else if (Map.isWall(posX / 65 + i, posY / 65) && unblockedWall(posX + i * 65, posY)) {
          Wall blown = Wall.getWall(posX + i * 65, posY);
          if (!blown.destroyed) {
            blown.animate(Wall.spritesDeath, 0, 100, false, false);
            blown.destroyWall();
          }
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / 65, posY / 65 + i) && unblockedWall(posX, posY + i * 65)) {
          radius.add(new Blast(posX, posY + i * 65, "down", i < range));
        } else if (Map.isWall(posX / 65, posY / 65 + i) && unblockedWall(posX, posY + i * 65)) {
          Wall blown = Wall.getWall(posX, posY + i * 65);
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
        for (int i = 1; i < Math.abs(posY - y) / 65; i++) {
          if (!Map.isFloor(posX / 65, posY / 65 - (posY - y) / Math.abs(posY - y) * i)) {
            unblocked = false;
          }
        }
      }
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      if (posX != x) {
        for (int i = 1; i < Math.abs(posX - x) / 65; i++) {
          if (!Map.isFloor(posX / 65 - (posX - x) / Math.abs(posX - x) * i, posY / 65)) {
            unblocked = false;
          }
        }
      }
    } catch (IndexOutOfBoundsException e) {
    }
    return unblocked;
  }
}
