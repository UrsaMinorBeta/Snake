public class Pickup {
  /* Creates pickups, func not hollow
   * some example ones will follow:
   * - enable and disable walls
   * - erase the field for close calls
   */

  int id; // always negative
  int posX;
  int posY;
  // Types:
  // (2 Likes!)
  // 1 = walls (on/off)
  // 2 = erase (pof!)
  int type;

  public Pickup(int identity, int X, int Y, int t) {
    /* This spawner is shy
     * But it doesnt lie
     * Identity has to be minus
     * For this convention is genius...
     */
    id = identity;
    posX = X;
    posY = Y;
    type = t;
  }

  public void spawn(Field field) {
    /* We have beautiful pickups
     * they are worth only hickups
     * if we cannot see them
     * so - let them be then
     */
    if (posX > 5 && posX < field.sizeX -5 && posY > 5 && posY < field.sizeY -5) {
      field.field[(int)posX][(int)posY]     = id;
      for (int i = 0; i < 5; i++) {
        field.field[(int)posX-i][(int)posY-i] = id;
        field.field[(int)posX+i][(int)posY-i] = id;
        field.field[(int)posX-i][(int)posY+i] = id;
        field.field[(int)posX+i][(int)posY+i] = id;
      }
      field.pickups.add(this);  // Add the pickup to the list of pickups in field
    }
  }

  public void disappear(Field field) {
    // Clear the pickup from the field
    field.field[(int)posX][(int)posY]     = 0;
    for (int i = 0; i < 5; i++) {
      field.field[(int)posX-i][(int)posY-i] = 0;
      field.field[(int)posX+i][(int)posY-i] = 0;
      field.field[(int)posX-i][(int)posY+i] = 0;
      field.field[(int)posX+i][(int)posY+i] = 0;
    }
  }

  public void perform(Snake snake, Field field) {
    // Execute the pickups special ability
    if (type == 1) {
      field.wall();
    } else if (type == 2) {
      field.erase();
    }
    disappear(field);
  }
}
