package cs3500.pa03.coord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.ships.ShipType;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the Board class
 */
class BoardTest {
  private Board testBoard;
  private Board testBoard4;
  private Map<ShipType, Integer> testFleet = new LinkedHashMap<>();

  /**
   * setup method
   */
  @BeforeEach
  public void setUp() {
    testBoard = new Board(10, 10);
    testBoard4 = new Board(6, 6);
    testFleet.put(ShipType.CARRIER, 1);
    testFleet.put(ShipType.BATTLESHIP, 1);
    testFleet.put(ShipType.DESTROYER, 1);
    testFleet.put(ShipType.SUBMARINE, 7);
  }

  /**
   * checks tests for the hideBoard method
   */
  @Test
  void hideBoard() {
    for (Coord[] rowCoord : testBoard.getBoard()) {
      for (Coord coord : rowCoord) {
        assertFalse(coord.getHide());
      }
    }
    testBoard.hideBoard();
    for (Coord[] rowCoord : testBoard.getBoard()) {
      for (Coord coord : rowCoord) {
        assertTrue(coord.getHide());
      }
    }
  }

  /**
   * checks tests for the unHideBoard method
   */
  @Test
  void unHideBoard() {
    testBoard.hideBoard();
    for (Coord[] rowCoord : testBoard.getBoard()) {
      for (Coord coord : rowCoord) {
        assertTrue(coord.getHide());
      }
    }
    testBoard.unHideBoard();
    for (Coord[] rowCoord : testBoard.getBoard()) {
      for (Coord coord : rowCoord) {
        assertFalse(coord.getHide());
      }
    }
  }

  /**
   * check tests for the mapsShipsToBoard method
   */
  @Test
  void mapShipsToBoard() {
    for (Coord[] rowCoord : testBoard.getBoard()) {
      for (Coord coord : rowCoord) {
        assertFalse(coord.isShipHere());
      }
    }

    testBoard.mapShipsToBoard(testFleet);
    int counter = 0;
    for (Coord[] rowCoord : testBoard.getBoard()) {
      for (Coord coord : rowCoord) {
        counter += (coord.isShipHere()) ? 1 : 0;
      }
    }
    assertTrue(counter > 0);
  }

  /**
   * checks tests for getShipsOnBoard method
   */
  @Test
  void getShipsOnBoard() {
    assertNotNull(testBoard.getShipsOnBoard());
  }

  /**
   * checks tests for the getSmallestDimension method
   */
  @Test
  void getSmallestDimension() {
    Board testBoard2 = new Board(6, 15);
    Board testBoard3 = new Board(15, 6);
    assertEquals(6, testBoard2.getSmallestDimension());
    assertEquals(6, testBoard3.getSmallestDimension());
  }

  /**
   * test for getboardtodisplay
   */
  @Test
  void getBoardToDisplay() {
    String board = "- - - - - - \n"
        + "- - - - - - \n"
        + "- - - - - - \n"
        + "- - - - - - \n"
        + "- - - - - - \n"
        + "- - - - - - \n";
    assertEquals(board, testBoard4.getBoardToDisplay());
  }

  /**
   * checks tests for the getBoard method
   */
  @Test
  void getBoard() {
    Coord[][] board4 = new Coord[6][6];
    for (int x = 0; x < 6; x++) {
      for (int y = 0; y < 6; y++) {
        board4[x][y] = new Coord(x, y);
        assertEquals(testBoard4.getBoard()[x][y].getX(), board4[x][y].getX());
        assertEquals(testBoard4.getBoard()[x][y].getY(), board4[x][y].getY());
      }
    }
  }
}