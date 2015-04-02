import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Gameplay implements KeyListener {
  // Arrays with boolean values for left/right-keys pressed.
  // dependent on number of players
  // left/right[0] = player one, left/right[1] = player two
  boolean[] left;
  boolean[] right;
  Snake[] snakes;  // Array of snakes, dependent on numPlayers

  int numPlayers;

  // Random number generator:
  Random rand = new Random();

  public static void main(String[] args) {
    new Gameplay();
  }

  public Gameplay() {
    // Initialize stuff
    Field field = new Field(500, 500);
    numPlayers = 4;
    Display display = new Display(field);
    display.addKeyListener(this);
    // Initialize control-array for snakes
    left = new boolean[numPlayers];
    right = new boolean[numPlayers];
    snakes = new Snake[numPlayers];
    for (int i = 0; i < numPlayers; i++) {
      left[i] = false;
      right[i] = false;
      snakes[i] = new Snake(rand.nextInt(field.sizeX), rand.nextInt(field.sizeY), i+1, rand.nextInt(359));
    }
    run(display, field, snakes);
  }

  public void run(Display display, Field field, Snake[] snakes) {
    /* Main loop, moves snakes and checks for collisions, putting snakes down
     * and being interface between snakes and field and pickups
     */

    while(true) {
      // Moving snakes, killing them if necessary
      for (int i = 0; i < numPlayers; i++) {
        if (snakes[i].alive) snakes[i].move(field, right[i], left[i]);
      }
      // if (snake1.alive) snake1.move(field, right[0], left[0]);
      // if (snake2.alive) snake2.move(field, right[1], left[1]);
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
    if (37 == e.getKeyCode()) left[0] = true;
    if (39 == e.getKeyCode()) right[0] = true;
    if (81 == e.getKeyCode()) left[1] = true;
    if (87 == e.getKeyCode()) right[1] = true;
    if (66 == e.getKeyCode()) left[2] = true;
    if (78 == e.getKeyCode()) right[2] = true;
    if (102 == e.getKeyCode()) left[3] = true;
    if (105 == e.getKeyCode()) right[3] = true;
  }

  public void keyReleased(KeyEvent e) {
    if (37 == e.getKeyCode()) left[0] = false;
    if (39 == e.getKeyCode()) right[0] = false;
    if (81 == e.getKeyCode()) left[1] = false;
    if (87 == e.getKeyCode()) right[1] = false;
    if (66 == e.getKeyCode()) left[2] = false;  
    if (78 == e.getKeyCode()) right[2] = false; 
    if (102 == e.getKeyCode()) left[3] = false; 
    if (105 == e.getKeyCode()) right[3] = false; 
  }

  public void keyTyped(KeyEvent e) {
  }
}
