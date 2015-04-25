import java.util.*;

public class Field {
  /* Class Field is supposed to be initialized once per game and contains
   * information about the current state of the field, as number of players,
   * points, effects and of course the free/occupied fields.
   */

  int sizeY;
  int sizeX;
  boolean walls = false;  // True = walls, False = leave left: enter right
  int[][] field;  // contains information for each cell with 0 = free, pos int = snakes, neg int = pickups
  boolean[][] isDrawn;  // determines wether a point on the field has already been drawn 

  List<Pickup> pickups = new ArrayList<Pickup>();  // contains all active pickups

  int numPlayers;
  int pointsToWin;

  public Field(int width, int heigth) {
    numPlayers = 2;  // useless at the moment
    sizeX = width;
    sizeY = heigth;
    // initialize the field
    field = new int[sizeX][sizeY];
    isDrawn = new boolean[sizeX][sizeY];
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        field[i][j] = 0;
        isDrawn[i][j] = true;
      }
    }
  }

  public void wall() {
    /* Creates walls if there are none
     * And otherwise, it makes them gone
     */
    walls = !walls;
    if (walls) {
      for (int i = 0; i < sizeX; i++) {
        field[i][0] = 1;
        field[i][sizeY - 1] = 1;
      }
      for (int j = 0; j < sizeY; j++) {
        field[0][j] = 1;
        field[sizeX - 1][j] = 1;
      }
    } else {
      for (int i = 0; i < sizeX; i++) {
        field[i][0] = 0;
        field[i][sizeY - 1] = 0;
      }
      for (int j = 0; j < sizeY; j++) {
        field[0][j] = 0;
        field[sizeX - 1][j] = 0;
      }
    }
  }

  public Pickup findPickup(int id) {
    return pickups.get((-id) - 1);
  }

  public void erase() {
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        // Due to better performance only redraw field-points which are occupied
        if (field[i][j] != 0) {
          isDrawn[i][j] = false;
        }
        field[i][j] = 0;
      }
    }

  }
}
