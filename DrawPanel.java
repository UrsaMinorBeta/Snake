import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class DrawPanel extends JPanel {
  public DrawPanel () {
  }

  public void paintComponent (Graphics g) {
    super.paintComponent(g); //??????
    setBackground(Color.red);
    g.setColor(Color.blue);
    g.fillOval(10,100,50,30);
  }
}
