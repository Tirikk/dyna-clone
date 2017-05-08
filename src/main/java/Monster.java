import java.util.ArrayList;
import java.util.List;

public class Monster extends Character {
  static ArrayList<Monster> monsterList = new ArrayList<>();
  private String path = "src/main/resources/sprites/monster/monster-";
  private List<String> spritesMoving = new ArrayList<>();
//  private List<String> spritesDeath = new ArrayList<>();

  Monster() {
    for (int i = 1; i < 6; i++) {
      if (i < 4) {
        spritesMoving.add(path.concat(i + ".png"));
      }
//      spritesDeath.add(path.concat("detonate-" + i + ".png"));
    }
    image = spritesMoving.get(1);
    direction = "null";
    animate(spritesMoving, 450, 450, true, true);
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
}
