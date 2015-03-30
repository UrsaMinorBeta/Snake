import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Display extends JFrame {
  
  // Make global variables for screen/framesize
  int screenWidth;
  int screenHeight;
  int scale;  // Determine the scaling of the field (from screen resolution)

  class DrawPanel extends JPanel {
    Field field;
    public DrawPanel (Field field2) {
      field = field2;
    }

    public void paintComponent (Graphics g) {
      super.paintComponent(g); //??????
      setBackground(Color.red);
      for (int i = 0; i < field.sizeX; i++) {
        for (int j = 0; j < field.sizeY; j++) {
          if (field.field[i][j] == 1) {
            g.setColor(Color.blue);
            g.fillOval(i*scale, j*scale, 10, 10);
          } else if (field.field[i][j] == 2) {
            g.setColor(Color.green);
            g.fillOval(i*scale, j*scale, 10, 10);
          } else if (field.field[i][j] < 0) {
            g.setColor(Color.black);
            g.fillOval(i*scale, j*scale, 10, 10);
          }
        }
      }
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
    // Calculate scaling factor   vvv X <-> Y????
    scale = (int)Math.floor(Math.min(screenHeight/(float)field.sizeY, screenWidth/(float)field.sizeX));
    // Set frame size by multplying the scaling factors with field size (and
    // adding some for the border, though not sure why
    setBounds(0,0,field.sizeX*scale+10, field.sizeY*scale+50);

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
}
