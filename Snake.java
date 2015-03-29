public class Snake {
  /* Snake Class is created once for each player and saves and alters the state
   * of each snake.
   */

  int id;
  boolean alive;  // alive or dead?

  double posY;  
  double posX;  
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
  
  public class Tuple { 
    public final int x; 
    public final int y; 
    public Tuple(int x, int y) { 
      this.x = x; 
      this.y = y; 
    } 
  }

  public Tuple[] circle(Field field, int posX, int posY, int size) {
    // Return array with coordinates to paint for snake
    Tuple[] body;  // Array with Tuples to paint
    body = new Tuple[5];
    for (int i = 0; i < 5; i++) {
      body[0] = new Tuple((int)posX,(int)posY);
      body[1] = new Tuple((int)posX-1, (int)posY-1);
      body[2] = new Tuple((int)posX+1, (int)posY-1);
      body[3] = new Tuple((int)posX-1, (int)posY+1);
      body[4] = new Tuple((int)posX+1, (int)posY+1);
    }
    return body;
  }

  public void move(Field field, boolean left, boolean right) {
    /* Alters the field according the move of the snake. Always moves forward,
     * player can choose only between pressing left or right (or nothing).
     * Concept: First check whether a move would result in collision, move then.
     * Gets a pointer of the field as argument.
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
    // Check whether field is free and kill snake 
    double checkPosX = posX;
    double checkPosY = posY;
    // checkPosX = (size + 0) * Math.sin(orientation*(Math.PI/180)); delete?
    checkPosX = Math.round((size + 1) * Math.cos(rad)-0);
    checkPosY = Math.round((size + 1) * Math.sin(rad)-0);
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
    Tuple[] body = circle(field, (int)posX, (int)posY, (int)size);
    for (int i = 0; i < 5; i++) {
      field.field[body[i].x][body[i].y] = id;
    }
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
