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
    // ...
    lastPosX = (int)posX;
    lastPosY = (int)posY;
    // ...
    orientation = startOrientation;
    size = 1;
    speed = 1;
    radius = 10;
  }

  public boolean move(Field field, boolean left, boolean right) {
    /* Alters the field according the move of the snake. Always moves forward,
     * player can choose only between pressing left or right (or nothing).
     * Gets a pointer of the field as argument; Returns  collision flag.
     */

    // Change orientation if left or right are triggered
    if (left) {
      orientation += radius;
    }
    if (right) {
      orientation -= radius;
    }
    // Next four lines to something slightly mysterious, but effectively realize
    // the moving (speed not accounted for yet, in general this solution might
    // not work well for variable stuff)
    orientation = (orientation + 360) % 360;
    double rad = Math.toRadians(orientation);
    posY += Math.sin(rad);
    posX += Math.cos(rad);
    // Now check whether we ran into a wall and if so, check if walls exist (and
    // either return collision error or set us on new position)
    if (posX < 0 || posX >= field.sizeX) {
      if (field.walls) {
        return true;
      } else {
        posX = (posX + field.sizeX) % field.sizeX;
        return false;
      }
    }
    if (posY < 0 || posY >= field.sizeY) {
      if (field.walls) {
        return true;
      } else {
        posY = (posY + field.sizeY) % field.sizeY;
        return false;
      }
    }
    // Check whether field is free and either return collision error or occupy
    // field (solved with lastPost to avoid false collision error (which only
    // occurs because of positions as doubles anyway) which is NOT nice but
    // rather just for proof of concept)
    if (field.field[(int)posX][(int)posY] == 0) {
      field.field[(int)posX][(int)posY] = id;
      // ...
      lastPosX = (int)posX;
      lastPosY = (int)posY;
      // ...
      return false;
    } else if ((int)posX == lastPosX && (int)posY == lastPosY) {
      // ...
      lastPosX = (int)posX;
      lastPosY = (int)posY;
      // ...
      return false;
    } else {
      return true;
    }

    // System.out.println("X: "+posX+"\tY: "+posY+"\tOri: "+orientation+"\tRad: "+rad);
  }
}
