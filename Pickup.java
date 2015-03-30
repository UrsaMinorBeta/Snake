public class Pickup {
  /* Creates pickups, for now:
   * - enable/disable walls
   */

  int id; // always negative
  int posX;
  int posY;
  // Types:
  // 1 = walls (on/off)
  // 2 = erase everything
  int type;

  public Pickup(int identity, int X, int Y, int t) {
    id = identity;
    posX = X;
    posY = Y;
    type = t;
  }

  public void spawn(Field field) {
    if (posX > 5 && posX < field.sizeX -5 && posY > 5 && posY < field.sizeY -5) {
      field.field[(int)posX][(int)posY]     = id;
      for (int i = 0; i < 5; i++) {
        field.field[(int)posX-i][(int)posY-i] = id;
        field.field[(int)posX+i][(int)posY-i] = id;
        field.field[(int)posX-i][(int)posY+i] = id;
        field.field[(int)posX+i][(int)posY+i] = id;
      }
      field.pickups.add(this);
    }
  }

  public void disappear(Field field) {
    field.field[(int)posX][(int)posY]     = 0;
    for (int i = 0; i < 5; i++) {
      field.field[(int)posX-i][(int)posY-i] = 0;
      field.field[(int)posX+i][(int)posY-i] = 0;
      field.field[(int)posX-i][(int)posY+i] = 0;
      field.field[(int)posX+i][(int)posY+i] = 0;
    }
  }

  public void perform(Snake snake, Field field) {
    if (type == 1) {
      field.wall();
    } else if (type == 2) {
      field.erase();
    }
    disappear(field);
  }
}
