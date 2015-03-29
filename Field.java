public class Field {
  /* Class Field is supposed to be initialized once per game and contains
   * information about the current state of the field, as number of players,
   * points, effects and of course the free/occupied fields.
   *
   *  
   */

  int sizeY;
  int sizeX;
  boolean walls;  // True = walls, False = leave left: enter right
  int[][] field;  // contains information for each cell with 0 = free

  int numPlayers;
  int pointsToWin;


  public Field(int width, int heigth) {
    numPlayers = 1;  // useless at the moment
    walls = false;  // should be true
    sizeX = width;
    sizeY = heigth;
    // initialize the field
    field = new int[sizeX][sizeY];
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        field[i][j] = 0;
      }
    }
    // draw the border
    if (walls) {
      for (int i = 0; i < sizeX; i++) {
        field[i][0] = 1;
        field[i][sizeY - 1] = 1;
      }
      for (int j = 0; j < sizeY; j++) {
        field[0][j] = 1;
        field[sizeX - 1][j] = 1;
      }
    }
    
  }
}
