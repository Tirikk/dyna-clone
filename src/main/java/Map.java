import java.awt.*;

public class Map {
  static int[][] mapMatrix = new int[11][20];

  static void drawBackGround(Graphics g) {
    PositionedImage image = new PositionedImage("src/main/resources/sprites/map/background.png", 0, 0);
    image.draw(g);
  }

  static void generateMatrix() {
    for (int column = 0; column < 11; column++) {
      for (int row = 0; row < 20; row++) {
        if (column % 2 != 0 && row % 2 != 0) {
          mapMatrix[column][row] = 1;
        } else if ((int) (Math.random() * 3) == 1) {
          mapMatrix[column][row] = 2;
        } else {
          mapMatrix[column][row] = 0;
        }
      }
    }
    mapMatrix[0][0] = 0;
    mapMatrix[1][0] = 0;
    mapMatrix[0][1] = 0;
  }

  static void generateWalls() {
    for (int column = 0; column < 11; column++) {
      for (int row = 0; row < 20; row++) {
        if (mapMatrix[column][row] == 2) {
          Wall.wallList.add(new Wall(row * 65, column * 65));
        }
      }
    }
  }

  static boolean isFloor(int column, int row) {
    return mapMatrix[row][column] == 0;
  }

  static boolean isWall(int column, int row) {
    return mapMatrix[row][column] == 2;
  }
}
