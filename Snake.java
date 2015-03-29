public class Snake {
  /* Snake Class is created once for each player and saves and alters the state
   * of each snake.
   */

  int id;
  boolean alive;  // alive or dead?

  double posY;  // y is horizontal
  double posX;  // x is vertical
  int lastPosY;  // y is horizontal
  int lastPosX;  // x is vertical
  int orientation;  // orientation

  double size;  // to scale size of snake; 1 for default
  double speed;  // to scale size of snake; 1 for default
  double radius;  // how much of a kurve there is

  public Snake(int startX, int startY, int identity, int startOrientation) {
    /* Is initialized with coordinates and orientation, might be a good idea to
     * add size/speed for modified games? Also, identity should never be the
     * same for different snakes. Except for teams?
     */

    id = identity;
    alive = true;
    posX = startX;
    posY = startY;
    orientation = startOrientation;
    size = 4;  // Radius of snake (ceiled)
    speed = 1;
    radius = 10;
  }
  
  public class Tuple<X, Y> { 
    public final X x; 
    public final Y y; 
    public Tuple(X x, Y y) { 
      this.x = x; 
      this.y = y; 
    } 
  }

  /* public void circle(Field field, int posX, int posY, int size) {
    int[] body;  // Array with Tuples to paint
    int i = 0;
    for (int n = 0; n < ) {
      body[i] 
  }*/

  public void move(Field field, boolean left, boolean right) {
    /* Alters the field according the move of the snake. Always moves forward,
     * player can choose only between pressing left or right (or nothing).
     * Concept: First check whether a move would result in collision, move then.
     * Gets a pointer of the field as argument; Returns  collision flag.
     */

    // Change orientation if left or right are triggered
    if (left) {
      orientation += radius;
    }
    if (right) {
      orientation -= radius;
    }
    // Put orientation into ... degrees?
    orientation = (orientation + 360) % 360;
    double rad = Math.toRadians(orientation);
    // Check whether field is free and either return collision error or occupy
    double checkPosX = posX;
    double checkPosY = posY;
    // checkPosX = (size + 0) * Math.sin(orientation*(Math.PI/180)); delete?
    checkPosX = (size + 1) * Math.cos(rad)-0;
    checkPosX = Math.round(checkPosX);
    checkPosY = (size + 1) * Math.sin(rad)-0;
    checkPosY = Math.round(checkPosY);
    System.out.println(id+"--c:  "+(int)(posX+checkPosX)+"/"+(int)(posY+checkPosY));
    if (field.field[(int)(posX+checkPosX)][(int)(posY+checkPosY)] != 0) {  // (= move allowed)
      alive = false;
    }
    // Next two lines to something slightly mysterious, but effectively realize
    // the moving (speed not accounted for yet, in general this solution might
    // not work well for variable stuff)
    posY += Math.sin(rad);
    posX += Math.cos(rad);
    // Paint "circle" around the position of the snake
    field.field[(int)posX][(int)posY] = id;
    field.field[(int)posX-1][(int)posY-1] = id;
    field.field[(int)posX+1][(int)posY-1] = id;
    field.field[(int)posX-1][(int)posY+1] = id;
    field.field[(int)posX+1][(int)posY+1] = id;
    // Now check whether we ran into a wall and if so, check if walls exist (and
    // either return collision error or set us on new position)
    // ... SHOULD BE REALIZED ABOVE, WALLS IMPLEMENTED DIFFERENT BE GOOD
    // IDEA
    if (posX < 0 || posX >= field.sizeX) {
      if (field.walls) {
        alive = false;
      } else {
        posX = (posX + field.sizeX) % field.sizeX;
      }
    }
    if (posY < 0 || posY >= field.sizeY) {
      if (field.walls) {
        alive = false;
      } else {
        posY = (posY + field.sizeY) % field.sizeY;
      }
    }
  }
}
