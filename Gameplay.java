import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Gameplay implements KeyListener {
  // Hacked solution
  boolean right1;
  boolean left1;
  boolean right2;
  boolean left2;
  // Random number generator:
  Random rand = new Random();

  public static void main(String[] args) {
    new Gameplay();
  }

  public Gameplay() {
    // Initialize stuff
    Field field = new Field(700, 700);
    Display display = new Display(field);
    display.addKeyListener(this);
    Snake snake1 = new Snake(50, 50, 1, 100);
    Snake snake2 = new Snake(200, 200, 2, 100);
    // Pickup pickup1 = new Pickup(-1, 250, 250, 1);
    // pickup1.spawn(field);
    run(display, field, snake1, snake2);
  }

  public void run(Display display, Field field, Snake snake1, Snake snake2) {
    /* Main loop, moves snakes and checks for collisions, putting snakes down
     * and being interface between snakes and field
     */

    while(true) {
      // Moving snakes, killing them if necessary
      if (snake1.alive) snake1.move(field, right1, left1);
      if (snake2.alive) snake2.move(field, right2, left2);
      // Display field
      display.show(field);
      // Randomly spawn pickups
      if (rand.nextInt(500) == 25) {
        Pickup pickup = new Pickup(- (field.pickups.size() + 1), rand.nextInt(field.sizeX), rand.nextInt(field.sizeY), rand.nextInt(2)+1);
        pickup.spawn(field);
      }

      // Sleep... bad, what's better?
      try {
        Thread.sleep(15);
      } catch (Exception e) {
      }
    }
  }

  // Following: hacked (but efficient) solution for keyHandling
  public void keyPressed(KeyEvent e) {
    if (37 == e.getKeyCode()) left1 = true;
    if (39 == e.getKeyCode()) right1 = true;
    if (81 == e.getKeyCode()) left2 = true;
    if (87 == e.getKeyCode()) right2 = true;
  }

  public void keyReleased(KeyEvent e) {
    if (37 == e.getKeyCode()) left1 = false;
    if (39 == e.getKeyCode()) right1 = false;
    if (81 == e.getKeyCode()) left2 = false;
    if (87 == e.getKeyCode()) right2 = false;
  }

  public void keyTyped(KeyEvent e) {
  }
}
