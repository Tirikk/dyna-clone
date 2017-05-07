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
  private ArrayList<Character> toDraw = new ArrayList<>();
  private ArrayList<Character> enemyList = new ArrayList<>();

  GameEngine() {
    setPreferredSize(new Dimension(715, 715));
    setVisible(true);
    generateElements();
  }

  private void generateElements() {
    Map.generateMatrix();
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
            scheduler.scheduleAtFixedRate(mover, 20, 20, TimeUnit.MILLISECONDS);
  }

  private void repaintCanvas() {
    final Runnable repainter = this::repaint;
    final ScheduledFuture<?> repainterHandle = scheduler.scheduleAtFixedRate(repainter, 0, 10, TimeUnit
            .MILLISECONDS);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    //      hero.moveHeroDown();
    //    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
    //      hero.moveHeroUp();
    //    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    //      hero.moveHeroLeft();
    //    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    //      hero.moveHeroRight();
    //    }
  }

  @Override
  public void paint(Graphics graphics) {
    Map.drawBackGround(graphics);
    for (Character character : toDraw) {
      PositionedImage image = new PositionedImage(character.image, character.posX, character.posY);
      image.draw(graphics);
    }
  }
}
