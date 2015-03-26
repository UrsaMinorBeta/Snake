import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*; 
import javax.swing.border.BevelBorder;

public class Display {
  JFrame frame;

  public Display() {
/*    // Make window
    frame = new JFrame("Sssssssnaaaaake!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Show window
    frame.setLocationRelativeTo(null); // Make it appear in center
    // frame.pack();  // adjusts frame size to content (not used due to grid)
    frame.setSize(1000, 1000);
    frame.setVisible(true);  // well...

/*    JPanel panel = new JPanel(new GridLayout(80, 80, 0, 0));

    for (int i = 0; i < 6400; i++) {
        JLabel l = new JLabel("" + i, JLabel.CENTER);
        //JLabel l = new JLabel(new ImageIcon("image_file.png"), JLabel.CENTER);
        //l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        l.setFont(l.getFont().deriveFont(20f));
        l.setOpaque(true);
        l.setBackground(Color.red);
        panel.add(l);
*/
   /* frame.setContentPane(new DrawPanel());
    frame.setVisible(true);
 */ }
  
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