import java.awt.*;

public class Map {
  static int[][] mapMatrix = new int[11][11];

  static void drawBackGround(Graphics g) {
    for (int column = 0; column < 11; column++) {
      for (int row = 0; row < 11; row++) {
        if (column % 2 != 0 && row % 2 != 0) {
          PositionedImage image = new PositionedImage("src/main/resources/sprites/map/block.png", 65 * column, 65 *
                  row);
          image.draw(g);
        } else {
          PositionedImage image = new PositionedImage("src/main/resources/sprites/map/grass.png", 65 * column, 65 * row);
          image.draw(g);
        }
      }
    }
  }

  static void generateMatrix() {
    for (int column = 0; column < 11; column++) {
      for (int row = 0; row < 11; row++) {
        if (column % 2 != 0 && row % 2 != 0) {
          mapMatrix[column][row] = 0;
        } else {
          mapMatrix[column][row] = 1;
        }
      }
    }
  }

  static boolean isFloor(int column, int row) {
    return mapMatrix[row][column] == 0;
  }
}
