public abstract class Character extends GameObject {
  String direction;

  void generateDirection() {
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
  }

  private boolean checkDirection() {
    if (direction.equals("left")) {
      return (posX >= 65 && Map.isFloor(posX / 65 - 1, posY / 65));
    } else if (direction.equals("right")) {
      return (posX < 650 && Map.isFloor(posX / 65 + 1, posY / 65));
    } else if (direction.equals("up")) {
      return (posY >= 65 && Map.isFloor(posX / 65, posY / 65 - 1));
    } else {
      return (posY < 650 && Map.isFloor(posX / 65, posY / 65 + 1));
    }
  }

  void move() {
    if (posX % 65 == 0 && posY % 65 == 0) {
      if ((int) (Math.random() * 4) == 0) {
        generateDirection();
      }
      while (!checkDirection()) {
        generateDirection();
      }
      moveInDirection();
    } else {
      moveInDirection();
    }
  }

  private void moveInDirection() {
    if (direction.equals("left")) {
      moveLeft();
    } else if (direction.equals("right")) {
      moveRight();
    } else if (direction.equals("up")) {
      moveUp();
    } else {
      moveDown();
    }
  }

  private void moveLeft() {
    posX--;
  }

  private void moveRight() {
    posX++;
  }

  private void moveUp() {
    posY--;
  }

  private void moveDown() {
    posY++;
  }
}
