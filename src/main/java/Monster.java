import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Monster extends Character {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
  static ArrayList<Monster> monsterList = new ArrayList<>();

  Monster() {
    image = "src/main/resources/sprites/monster/monster-2.png";
    direction = "null";
    animate();
  }

  static void generateMonsters(int n) {
    monsterList.clear();
    int i = 0;
    while (i < n) {
      int column = (int) (Math.random() * 11);
      int row = (int) (Math.random() * 11);
      if (column != 0 | row != 0) {
        if (Map.isFloor(column, row)) {
          monsterList.add(new Monster());
          monsterList.get(i).posX = column * 65;
          monsterList.get(i).posY = row * 65;
          monsterList.get(i).generateDirection();
          i++;
        }
      }
    }
  }

  private void animate() {
    final Runnable animator = () -> {
      try {
        image = "src/main/resources/sprites/monster/monster-2.png";
        TimeUnit.MILLISECONDS.sleep(500);
        image = "src/main/resources/sprites/monster/monster-1.png";
        TimeUnit.MILLISECONDS.sleep(500);
        image = "src/main/resources/sprites/monster/monster-0.png";
        TimeUnit.MILLISECONDS.sleep(500);
        image = "src/main/resources/sprites/monster/monster-1.png";
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    final ScheduledFuture<?> animHandle = scheduler.scheduleAtFixedRate(animator, 500, 500, TimeUnit.MILLISECONDS);
  }
}
