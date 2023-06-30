package cs3500.pa03.coord;

import cs3500.pa03.ships.Ship;
import cs3500.pa03.ships.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * represents a board in battleship game
 */
public class Board {
  private Coord[][] board;
  private Random random;
  private int height;
  private int width;
  private List<Ship> shipsOnBoard = new ArrayList<>();

  /**
   * constructs a board
   *
   * @param h an int representing the height
   * @param w an int representing the width
   */
  public Board(int h, int w) {
    height = h;
    width = w;

    board = new Coord[height][width];

    for (int row = 0; row < height; row++) {
      Coord[] currRow = new Coord[width];
      for (int col = 0; col < width; col++) {
        currRow[col] = new Coord(row, col);
      }
      board[row] = currRow;
    }

    random = new Random();
  }

  /**
   * hides the entire board from view
   */
  public void hideBoard() {
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        board[row][col].setHide();
      }
    }
  }

  /**
   * unhides the entire board from view
   */
  public void unHideBoard() {
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        board[row][col].unHide();
      }
    }
  }

  /**
   * places a map of ships and the number of them to the current board
   *
   * @param ships a map of ships and how many of each there are
   */
  public void mapShipsToBoard(Map<ShipType, Integer> ships) {
    for (ShipType type : ships.keySet()) {
      for (int i = 0; i < ships.get(type); i++) {
        boolean isVertical = random.nextInt(2) == 0;
        if (isVertical || board.length >= 6 && board.length <= 7
            || (board[0].length <= 7 && board[0].length >= 6)) {
          mapVertical(type);
        } else {
          mapHorizontal(type);
        }
      }
    }
  }

  /**
   * places given ships randomly on the board oriented horizontally
   *
   * @param type the type of given ship
   */
  private void mapHorizontal(ShipType type) {
    Ship s = new Ship(type);
    int shipLength = s.getLives();
    int randRow = random.nextInt(height);
    int startColCoor;
    if (shipLength == width) {
      startColCoor = 0;
    } else {
      startColCoor = random.nextInt(width - shipLength);
    }

    if (!validHorzSpots(shipLength, startColCoor, randRow)) {
      mapVertical(type);
    } else {
      for (int i = 0; i < shipLength; i++) {
        board[randRow][startColCoor + i].placeShip(s);
        s.addCoord(board[randRow][startColCoor + i]);
      }
      addShip(s);
    }
  }

  /**
   * determines if the given row can place a given ship or not
   *
   * @param shipLength the length of the given ship to be placed
   * @param start the starting index in the row to start placing
   * @param row the given row to place the ship
   * @return whether there is space or not
   */
  private boolean validHorzSpots(int shipLength, int start, int row) {
    if (start + shipLength > width) {
      return false;
    }
    for (Ship ship : shipsOnBoard) {
      for (Coord coord : ship.getCoords()) {
        if (coord.getX() >= start && coord.getX() <= start + shipLength - 1
            && coord.getY() == row) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * places a ship vertically on the board
   *
   * @param type the type of the given ship
   */
  private void mapVertical(ShipType type) {
    Ship s = new Ship(type);
    int shipLength = s.getLives();
    int randCol = random.nextInt(width);
    int startRowCoor;
    if (shipLength == height) {
      startRowCoor = 0;
    } else {
      startRowCoor = random.nextInt(height - shipLength);
    }

    if (!validVertSpots(shipLength, startRowCoor, randCol)) {
      mapHorizontal(type);
    } else {
      for (int i = 0; i < shipLength; i++) {
        board[startRowCoor + i][randCol].placeShip(s);
        s.addCoord(board[startRowCoor + i][randCol]);
      }
      addShip(s);
    }
  }

  /**
   * adds a ship to the current list of ships on the board
   *
   * @param ship input ship
   */
  private void addShip(Ship ship) {
    shipsOnBoard.add(ship);
  }

  /**
   * gets the current list of ships on a board
   *
   * @return a list of ships on this board
   */
  public List<Ship> getShipsOnBoard() {
    return shipsOnBoard;
  }

  /**
   * determines if a given column can place a given ship in it
   *
   * @param shipLength the length of the given ship to be placed
   * @param start the starting index in the column
   * @param col the column in the board
   * @return whether there is sufficient room in this column
   */
  private boolean validVertSpots(int shipLength, int start, int col) {
    if (start + shipLength > height) {
      return false;
    }
    for (Ship ship : shipsOnBoard) {
      for (Coord coord : ship.getCoords()) {
        if (coord.getY() >= start && coord.getY() <= start + shipLength - 1
            && coord.getX() == col) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * gets the smaller dimension of this board
   *
   * @return an int of the smaller dimension
   */
  public int getSmallestDimension() {
    return Math.min(board.length, board[0].length);
  }

  /**
   * gets the current board into a string version to be displayed
   *
   * @return a string of the current board
   */

  public String getBoardToDisplay() {
    StringBuilder boardAsString = new StringBuilder();
    for (Coord[] row : board) {
      for (Coord col : row) {
        boardAsString.append(col.toString()).append(" ");
      }
      boardAsString.append("\n");
    }
    return boardAsString.toString();
  }

  /**
   * returns the 2d array of coordinates in this board
   *
   * @return a 2d array of coordinates that represents this board
   */
  public Coord[][] getBoard() {
    return board;
  }
}