import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Display extends JFrame {
  
  // Make global variables for screen/framesize
  int screenWidth;
  int screenHeight;
  int scale;  // Determine the scaling of the field (from screen resolution)

  class DrawPanel extends JPanel {
    Field field;
    BufferedImage bimage;
    public DrawPanel (Field field2, int width, int height) {
      field = field2;
      bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void paintComponent (Graphics g) {
      // super.paintComponent(g); //??????
      // setBackground(Color.white);
      // 2DGraphics create from g to display BufferedImage
      Graphics2D g2 = (Graphics2D)g;
      // New Graphics createed from the Buffered Image in order to draw on it
      Graphics2D gr = bimage.createGraphics();
      for (int i = 0; i < field.sizeX; i++) {
        for (int j = 0; j < field.sizeY; j++) {
          // Only draw points which have not been drawn yet
          if (!field.isDrawn[i][j]) {
            field.isDrawn[i][j] = true;
            if (field.field[i][j] == 1) {
              gr.setColor(Color.cyan);
              gr.fillOval(i*scale, j*scale, 10, 10);
            } else if (field.field[i][j] == 2) {
              gr.setColor(new Color(102, 0, 102));
              gr.fillOval(i*scale, j*scale, 10, 10);
            } else if (field.field[i][j] == 3) {
              gr.setColor(Color.red);
              gr.fillOval(i*scale, j*scale, 10, 10);
            } else if (field.field[i][j] == 4) {
              gr.setColor(Color.yellow);
              gr.fillOval(i*scale, j*scale, 10, 10);
            } else if (field.field[i][j] == 0) {
              gr.setColor(Color.black);
              gr.fillOval(i*scale, j*scale, 10, 10);
            } else if (field.field[i][j] < 0) {
              // its a pickup
              // i've got hickup
              Pickup pickup = field.findPickup(field.field[i][j]);
              if (pickup.type == 1) {
                gr.setColor(Color.white);
              } else if (pickup.type == 2) {
                gr.setColor(Color.yellow);
              }
              gr.fillOval(i*scale, j*scale, 10, 10);
            }
          }
        }
      }
      g2.drawImage(bimage, null, 0, 0);
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

    setContentPane(new DrawPanel(field, screenWidth, screenHeight));
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
