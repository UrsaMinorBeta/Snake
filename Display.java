import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*; 
import javax.swing.border.BevelBorder;

public class Display {
  JFrame frame;

  public Display() {
    // Make window
    frame = new JFrame("Sssssssnaaaaake!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Show window
    frame.setLocationRelativeTo(null); // Make it appear in center
    // frame.pack();  // adjusts frame size to content (not used due to grid)
    frame.setSize(1000, 1000);
    frame.setVisible(true);  // well...
   
    frame.setContentPane(new DrawPanel());
    frame.setVisible(true);
  }
  
  public static void show(Field field) {
    final String ANSI_CLS = "\u001b[2J";
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
  }
}
