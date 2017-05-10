import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameEngine extends JComponent implements KeyListener {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
  private Hero hero = new Hero();
  static ArrayList<Character> characters = new ArrayList<>();
  static ArrayList<GameObject> bombs = new ArrayList<>();
  static ArrayList<BlastRadius> blasts = new ArrayList<>();

  static int offsetX = 0;

  static ArrayList<Character> enemyList = new ArrayList<>();
  private int keyPressed = 0;

  GameEngine() {
    setPreferredSize(new Dimension(715, 715));
    setVisible(true);
    setOpaque(false);
    generateElements(getGraphics());
  }

  private void generateElements(Graphics g) {
    Map.generateMatrix();
    Map.generateWalls();
    Background.drawBackground();
    Background.drawWalls();
    hero.posX = 0;
    hero.posY = 0;
    hero.image = "src/main/resources/sprites/hero/hero-down-2.png";
    Monster.generateMonsters(3);
    for (Monster monster : Monster.monsterList) {
      characters.add(monster);
      enemyList.add(monster);
    }
    characters.add(hero);
    moveEnemies();
    repaintCanvas();
  }

  private void moveEnemies() {
    final Runnable mover = () -> {
      for (Character enemy : enemyList) {
        enemy.move();
      }
    };
    scheduler.scheduleAtFixedRate(mover, 30, 30, TimeUnit.MILLISECONDS);
  }

  private void repaintCanvas() {
    final Runnable repainter = this::repaint;
    scheduler.scheduleAtFixedRate(repainter, 0, 30, TimeUnit
            .MILLISECONDS);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_DOWN && keyPressed < 1 && hero.alive) {
      keyPressed++;
      hero.moving = true;
      hero.moveHeroDown();
    } else if (e.getKeyCode() == KeyEvent.VK_UP && keyPressed < 1 && hero.alive) {
      keyPressed++;
      hero.moving = true;
      hero.moveHeroUp();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT && keyPressed < 1 && hero.alive) {
      keyPressed++;
      hero.moving = true;
      hero.moveHeroLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && keyPressed < 1 && hero.alive) {
      keyPressed++;
      hero.moving = true;
      hero.moveHeroRight();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_DOWN && keyPressed > 0) {
      if (keyPressed > 0) {
        keyPressed--;
      }
      hero.moving = false;
    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
      if (keyPressed > 0) {
        keyPressed--;
      }
      hero.moving = false;
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (keyPressed > 0) {
        keyPressed--;
      }
      hero.moving = false;
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if (keyPressed > 0) {
        keyPressed--;
      }
      hero.moving = false;
    } else if (e.getKeyCode() == KeyEvent.VK_SPACE && hero.alive && bombs.size() < 1) {
      if (hero.posX % 65 > 35 && hero.posY % 65 == 0) {
        bombs.add(new Bomb(hero.posX / 65 + 1, hero.posY / 65));
      } else if (hero.posY % 65 > 35 && hero.posX % 65 == 0) {
        bombs.add(new Bomb(hero.posX / 65, hero.posY / 65 + 1));
      } else {
        bombs.add(new Bomb(hero.posX / 65, hero.posY / 65));
      }
    }
  }

  public static int getCharacterIndex(int x, int y) {
    int i = 0;
    for (GameObject character : characters) {
      if (x == character.posX && y == character.posY) {
        i = characters.indexOf(character);
      }
    }
    return i;
  }

  public static int getEnemyIndex(int x, int y) {
    int i = 0;
    for (GameObject enemy : enemyList) {
      if (x == enemy.posX && y == enemy.posY) {
        i = characters.indexOf(enemy);
      }
    }
    return i;
  }

  @Override
  public void paint(Graphics graphics) {
    graphics.translate(offsetX, 0);
    graphics.drawImage(Background.bImage, 0, 0, null);
    for (Character enemy : enemyList) {
      if (Math.abs(enemy.posX - hero.posX) < 25 && Math.abs(enemy.posY - hero.posY) < 25 && hero.alive) {
        hero.die();
      }
    }
    for (BlastRadius blastRadius : blasts) {
      for (GameObject blast : blastRadius.radius) {
        PositionedImage image = new PositionedImage(blast.image, blast.posX, blast.posY);
        image.draw(graphics);
        if (Math.abs(blast.posX - hero.posX) < 40 && Math.abs(blast.posY - hero.posY) < 40 && hero.alive) {
          hero.die();
        }
        for (Character enemy : enemyList) {
          if (Math.abs(blast.posX - enemy.posX) < 40 && Math.abs(blast.posY - enemy.posY) < 40 && enemy.alive) {
            enemy.die();
          }
        }
      }
      for (GameObject bomb : bombs) {
        if (Math.abs(bomb.posX - hero.posX) < 40 && Math.abs(bomb.posY - hero.posY) < 40 && hero.alive) {
          hero.die();
        }
      }
    }
    for (GameObject bomb : bombs) {
      PositionedImage image = new PositionedImage(bomb.image, bomb.posX, bomb.posY);
      image.draw(graphics);
    }
    for (GameObject object : characters) {
      PositionedImage image = new PositionedImage(object.image, object.posX, object.posY);
      image.draw(graphics);
    }
  }
}
