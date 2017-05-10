import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BlastRadius {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
  int posX, posY;
  static int range = 1;
  List<Blast> radius = new ArrayList<>();

  public BlastRadius(int x, int y) {
    this.posX = x;
    this.posY = y;
    populateRadius();
    detonate();
  }

  void populateRadius() {
    for (int i = 1; i <= range; i++) {
      try {
        if (Map.isFloor(posX / 65 - 1, posY / 65) && Map.isFloor(posX / 65 - i, posY / 65)) {
          radius.add(new Blast(posX - i * 65, posY, "left", i < range));
        } else if (Map.isWall(posX / 65 - 1, posY / 65)) {
          Wall blown = Wall.getWall(posX - 65, posY);
          blown.animate(Wall.spritesDeath, 0, 100, false, false);
          blown.destroyWall();
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / 65, posY / 65 - 1) && Map.isFloor(posX / 65, posY / 65 - i)) {
          radius.add(new Blast(posX, posY - i * 65, "up", i < range));
        } else if (Map.isWall(posX / 65, posY / 65 - 1)) {
          Wall blown = Wall.getWall(posX, posY - 65);
          blown.animate(Wall.spritesDeath, 0, 100, false, false);
          blown.destroyWall();
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / 65 + 1, posY / 65) && Map.isFloor(posX / 65 + i, posY / 65)) {
          radius.add(new Blast(posX + i * 65, posY, "right", i < range));
        } else if (Map.isWall(posX / 65 + 1, posY / 65)) {
          Wall blown = Wall.getWall(posX + 65, posY);
          blown.animate(Wall.spritesDeath, 0, 100, false, false);
          blown.destroyWall();
        }
      } catch (IndexOutOfBoundsException e) {
      }
      try {
        if (Map.isFloor(posX / 65, posY / 65 + 1) && Map.isFloor(posX / 65, posY / 65 + i)) {
          radius.add(new Blast(posX, posY + i * 65, "down", i < range));
        } else if (Map.isWall(posX / 65, posY / 65 + 1)) {
          Wall blown = Wall.getWall(posX, posY + 65);
          blown.animate(Wall.spritesDeath, 0, 100, false, false);
          blown.destroyWall();
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
}
