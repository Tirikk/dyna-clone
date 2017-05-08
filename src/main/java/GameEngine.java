import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class GameEngine extends JComponent implements KeyListener {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);
  private Hero hero = new Hero();
  static ArrayList<GameObject> toDraw = new ArrayList<>();
  private ArrayList<Character> enemyList = new ArrayList<>();
  private int keyPressed = 0;

  GameEngine() {
    setPreferredSize(new Dimension(715, 715));
    setVisible(true);
    setOpaque(false);
    generateElements(getGraphics());
  }

  private void generateElements(Graphics g) {
    Map.generateMatrix();
//    Map.generateWalls();
    Background.drawImage();
    hero.posX = 0;
    hero.posY = 0;
    hero.image = "src/main/resources/sprites/hero/hero-down-2.png";
    Monster.generateMonsters(3);
    for (Monster monster : Monster.monsterList) {
      toDraw.add(monster);
      enemyList.add(monster);
    }
    toDraw.add(hero);
    moveEnemies();
    repaintCanvas();
  }

  private void moveEnemies() {
    final Runnable mover = () -> {
      for (Character enemy : enemyList) {
        enemy.move();
      }
    };
    final ScheduledFuture<?> moverHandle =
            scheduler.scheduleAtFixedRate(mover, 30, 30, TimeUnit.MILLISECONDS);
  }

  private void repaintCanvas() {
    final Runnable repainter = this::repaint;
    final ScheduledFuture<?> repainterHandle = scheduler.scheduleAtFixedRate(repainter, 0, 30, TimeUnit
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
    } else if (e.getKeyCode() == KeyEvent.VK_SPACE && hero.alive) {
      if (hero.posX % 65 > 35 && hero.posY % 65 == 0) {
        toDraw.add(new Bomb(hero.posX / 65 + 1, hero.posY / 65));
      } else if (hero.posY % 65 > 35 && hero.posX % 65 == 0) {
        toDraw.add(new Bomb(hero.posX / 65, hero.posY / 65 + 1));
      } else {
        toDraw.add(new Bomb(hero.posX / 65, hero.posY / 65));
      }
    }
  }

  @Override
  public void paint(Graphics graphics) {
    graphics.drawImage(Background.bImage, 0, 0, null);
    for (GameObject enemy : enemyList) {
      if (Math.abs(enemy.posX - hero.posX) < 25 && Math.abs(enemy.posY - hero.posY) < 25 && hero.alive) {
        hero.die();
      }
    }
    for (GameObject object : toDraw) {
      PositionedImage image = new PositionedImage(object.image, object.posX, object.posY);
      image.draw(graphics);
    }
  }
}
