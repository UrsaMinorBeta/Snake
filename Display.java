import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Display extends JFrame {
  
  // Make global variables for screen/framesize
  int screenWidth;
  int screenHeight;
  float scale;  // Determine the scaling of the field (from screen resolution)

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
            g.fillOval(i*(int)Math.floor(scale), j*(int)Math.floor(scale), 5, 5);
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
    // Set size to fullscreen using Toolkit
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenHeight = screenSize.height;
    screenWidth = screenSize.width;
    // Calculate scaling factor
    scale = Math.min(screenHeight/(float)field.sizeX, screenWidth/(float)field.sizeY);
    // Set frame size by multplying the scaling factors with field size (and
    // adding some for the border, though not sure why
    setBounds(0,0,field.sizeX*(int)Math.ceil(scale)+10, field.sizeY*(int)Math.ceil(scale)+50);

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
