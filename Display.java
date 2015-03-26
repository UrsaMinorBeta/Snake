import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Display extends JFrame {

  class DrawPanel extends JPanel {
    Field field;
    public DrawPanel (Field field2) {
      field = field2;
    }

    public void paintComponent (Graphics g) {
      super.paintComponent(g); //??????
      setBackground(Color.red);
      g.setColor(Color.blue);
      for (int i = 0; i < field.sizeX; i++) {
        for (int j = 0; j < field.sizeY; j++) {
          if (field.field[i][j] != 0) {
            g.fillOval(i*2, j*2, 10, 10);
          }
        }
      }
      // g.fillOval(10,100,50,30);
    }
  }

  public Display(Field field) {
    // Make window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Show window
    setLocationRelativeTo(null); // Make it appear in center
    // frame.pack();  // adjusts frame size to content (not used due to grid)
    setSize(1000, 1000);

    // frame.setContentPane(new DrawPanel());
    setContentPane(new DrawPanel(field));
    setVisible(true);
  }

  public void show(Field field) {
    repaint();
    setVisible(true);
  }
/*    final String ANSI_CLS = "\u001b[2J";
    final String ANSI_HOME = "\u001b[H";
    System.out.print(ANSI_CLS + ANSI_HOME);
    System.out.flush();
    for (int x = 0; x < field.sizeX; x++) {
      for (int y = 0; y < field.sizeY; y++) {
        if (field.field[x][y] == 1) System.out.print("X");
        else if (field.field[x][y] == 2) System.out.print("O");
        else System.out.print(" ");
      }
      System.out.println();
    }
  }*/
  // Following: hacked (but efficient) solution for keyHandling
  /* public void keyPressed(KeyEvent e) {
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
  }*/
}
