import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gameplay extends JFrame implements KeyListener {
  // Hacked solution
  boolean right1;
  boolean left1;
  boolean right2;
  boolean left2;

  public static void main(String[] args) {
    new Gameplay();
  }

  public Gameplay() {
    // hacked solution
    setSize(0, 0);
    setLayout(null);
    setVisible(true);

    // Initialize stuff
    Field field = new Field(500, 500);
    Display display = new Display(field);
    display.addKeyListener(this);
    Snake snake1 = new Snake(30, 20, 1, 300);
    Snake snake2 = new Snake(50, 40, 2, 100);
    run(display, field, snake1, snake2);
  }

  public void run(Display display, Field field, Snake snake1, Snake snake2) {
    /* Main loop, moves snakes and checks for collisions, putting snakes down
     * and being interface between snakes and field
     */

    while(true) {
      // Moving snakes, killing them if necessary
      if (!snake1.alive || snake1.move(field, left1, right1)) snake1.alive = false;
      if (!snake2.alive || snake2.move(field, left2, right2)) snake2.alive = false;
      // Display field
      display.show(field);


      // Sleep... bad, what's better?
      try {
        Thread.sleep(40);
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
