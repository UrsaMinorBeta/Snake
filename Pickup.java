public class Pickup {
  /* Creates pickups, for now:
   * - enable/disable walls
   */

  int id; // always negative
  int posX;
  int posY;
  // Types:
  // 1 = walls (on/off)
  int type;

  public Pickup(int identity, int X, int Y, int t) {
    id = identity;
    posX = X;
    posY = Y;
    type = t;
  }

  public void spawn(Field field) {
    field.field[(int)posX][(int)posY]     = id;
    for (int i = 0; i < 5; i++) {
      field.field[(int)posX-i][(int)posY-i] = id;
      field.field[(int)posX+i][(int)posY-i] = id;
      field.field[(int)posX-i][(int)posY+i] = id;
      field.field[(int)posX+i][(int)posY+i] = id;
    }
    field.pickups.add(this);
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
    }
    disappear(field);
  }
}
