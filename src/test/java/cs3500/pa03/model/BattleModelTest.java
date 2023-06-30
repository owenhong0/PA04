package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.GameResult;
import cs3500.pa03.coord.Board;
import cs3500.pa03.coord.Coord;
import cs3500.pa03.ships.Ship;
import cs3500.pa03.ships.ShipType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * checks tests for the BattleModel class
 */
class BattleModelTest {
  private BattleModel testModel;

  @BeforeEach
  public void setUp() {
    testModel = new BattleModel();
  }

  /**
   * checks tests for the checkDimensions method
   */
  @Test
  void checkDimensions() {
    assertFalse(testModel.checkDimensions(new int[] {5, 5}));
    assertFalse(testModel.checkDimensions(new int[] {5, 15}));
    assertTrue(testModel.checkDimensions(new int[] {6, 15}));
    assertTrue(testModel.checkDimensions(new int[] {8, 8}));
  }

  /**
   * checks tests for the getBoardsToDisplay method
   */
  @Test
  void getBoardsToDisplay() {
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(1, 1));
    shots.add(new Coord(1, 2));
    shots.add(new Coord(1, 3));
    shots.add(new Coord(1, 4));
    Board testBoard = new Board(10, 10);
    testModel.addP2Board(testBoard);
    testModel.addP1Ships(new Comp(testModel,
        "CPU", shots).setup(10, 10, testModel.setRandomFleet()));
    Map<ShipType, Integer> testFleet2 = new LinkedHashMap<>();
    testFleet2.put(ShipType.CARRIER, 2);
    testFleet2.put(ShipType.BATTLESHIP, 2);
    testFleet2.put(ShipType.DESTROYER, 2);
    testFleet2.put(ShipType.SUBMARINE, 2);
    testModel.addP2Ships(new Comp(testModel,
        "CPU2", shots).setup(10, 10, testFleet2));
    testModel.addP1Board(testBoard);
    assertNotNull(testModel.getBoardsToDisplay());
    assertEquals("Opponent Board Data: \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "Your Board:\n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "- - - - - - - - - - \n"
            + "Please Enter 5 Shots:\n"
            + "Remember the board starts at (0, 0) and goes to dimensions - 1\n"
            + "------------------------------------------------------------------\n",
        testModel.getBoardsToDisplay());
  }

  /**
   * checks tests for the getSmallestDim method
   */
  @Test
  void getSmallestDim() {
    testModel.setDimensions(new int[] {6, 15});
    assertEquals(6, testModel.getSmallestDim());
  }

  /**
   * checks tests for the checkFleet method
   */
  @Test
  void checkFleet() {
    testModel.setDimensions(new int[] {6, 15});
    assertTrue(testModel.checkFleet(new int[] {1, 1, 1, 1}));
    assertTrue(testModel.checkFleet(new int[] {1, 1, 1, 2}));
    assertTrue(testModel.checkFleet(new int[] {2, 1, 1, 2}));
    assertFalse(testModel.checkFleet(new int[] {2, 10, 1, 2}));
    assertFalse(testModel.checkFleet(new int[] {2, 3, 1, 2}));
    assertFalse(testModel.checkFleet(new int[] {2, 3, 1, 0}));
  }

  /**
   * checks tests for the setDimensions method
   */
  @Test
  void setDimensions() {
    assertEquals(0, testModel.getHeight());
    assertEquals(0, testModel.getWidth());
    testModel.setDimensions(new int[] {10, 10});

    assertEquals(10, testModel.getHeight());
    assertEquals(10, testModel.getWidth());
  }

  /**
   * checks tests for the convertFleet method
   */
  @Test
  void convertFleet() {
    Map<ShipType, Integer> testFleet = new LinkedHashMap<>();
    testFleet.put(ShipType.CARRIER, 1);
    testFleet.put(ShipType.BATTLESHIP, 1);
    testFleet.put(ShipType.DESTROYER, 1);
    testFleet.put(ShipType.SUBMARINE, 1);

    assertEquals(testFleet, testModel.convertFleet(new int[] {1, 1, 1, 1}));

    Map<ShipType, Integer> testFleet2 = new LinkedHashMap<>();
    testFleet2.put(ShipType.CARRIER, 2);
    testFleet2.put(ShipType.BATTLESHIP, 2);
    testFleet2.put(ShipType.DESTROYER, 2);
    testFleet2.put(ShipType.SUBMARINE, 2);
    assertEquals(testFleet2, testModel.convertFleet(new int[] {2, 2, 2, 2}));
  }

  /**
   * checks tests for the setRandomFleet method
   */
  @Test
  void setRandomFleet() {
    Map<ShipType, Integer> testFleet = new LinkedHashMap<>();
    testFleet.put(ShipType.CARRIER, 1);
    testFleet.put(ShipType.BATTLESHIP, 1);
    testFleet.put(ShipType.DESTROYER, 1);
    testFleet.put(ShipType.SUBMARINE, 1);

    Map<ShipType, Integer> testFleet2 = testModel.setRandomFleet();
    assertNotNull(testFleet2);
    for (ShipType type : testFleet2.keySet()) {
      assertTrue(testFleet2.get(type) >= 1);
    }
  }

  /**
   * checks tests for the getShips method
   */
  @Test
  void getShips() {
    assertEquals(4, testModel.getShips(new int[] {1, 1, 1, 1}));
    assertEquals(8, testModel.getShips(new int[] {2, 2, 2, 2}));
  }

  /**
   * checks tests for the addP2Board method
   */
  @Test
  void addP2Board() {
    assertNull(testModel.getP2Board());
    testModel.addP2Board(new Board(10, 10));
    assertNotNull(testModel.getP2Board());
  }

  /**
   * checks tests for the getP2Board method
   */
  @Test
  void getP2Board() {
    assertNull(testModel.getP2Board());
    Board testBoard = new Board(10, 10);
    testModel.addP2Board(testBoard);
    assertEquals(testBoard, testModel.getP2Board());
  }

  /**
   * checks tests for the addP1Board method
   */
  @Test
  void addP1Board() {
    assertNull(testModel.getP1Board());
    testModel.addP1Board(new Board(10, 10));
    assertNotNull(testModel.getP1Board());
  }

  /**
   * checks tests for the getP1Board method
   */
  @Test
  void getP1Board() {
    assertNull(testModel.getP1Board());
    Board testBoard = new Board(10, 10);
    testModel.addP1Board(testBoard);
    assertEquals(testBoard, testModel.getP1Board());
  }

  /**
   * checks tests for the getHeight method
   */
  @Test
  void getHeight() {
    assertEquals(0, testModel.getHeight());
    testModel.setDimensions(new int[] {6, 6});
    assertEquals(6, testModel.getHeight());
  }

  /**
   * checks tests for the getWidth method
   */
  @Test
  void getWidth() {
    assertEquals(0, testModel.getWidth());
    testModel.setDimensions(new int[] {6, 6});
    assertEquals(6, testModel.getWidth());
  }

  /**
   * checks tests for the addP1Ships method
   */
  @Test
  void addP1Ships() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    assertNull(testModel.getP1Ships());
    testModel.addP1Ships(shipList);
    assertTrue(testModel.getP1Ships().size() > 0);
  }

  /**
   * checks tests for the addCompShips method
   */
  @Test
  void addCompShips() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    assertNull(testModel.getP2Ships());
    testModel.addP2Ships(shipList);
    assertTrue(testModel.getP2Ships().size() > 0);
  }

  /**
   * checks tests for the checkShots method
   */
  @Test
  void checkShots() {
    testModel.setDimensions(new int[] {6, 6});
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(1, 1));
    shots.add(new Coord(1, 2));
    shots.add(new Coord(1, 3));
    shots.add(new Coord(1, 4));
    testModel.addP1Ships(new Comp(testModel,
        "CPU", shots).setup(6, 6, testModel.setRandomFleet()));
    assertTrue(testModel.checkShots(new int[] {1, 2, 2, 3, 3, 4, 4, 5, 5, 6}));
    assertFalse(testModel.checkShots(new int[] {8, 2, 2, 3, 3, 4, 4, 5, 5, 6}));
  }

  /**
   * checks tests for the convertShots method
   */
  @Test
  void convertShots() {
    ArrayList<Coord> testCoords = new ArrayList<>();
    testCoords.add(new Coord(1, 1));
    testCoords.add(new Coord(2, 2));
    List<Coord> modelCoords = testModel.convertShots(new int[] {1, 1, 2, 2});
    System.out.println("X: "
        + modelCoords.get(0).getX() + " Y: "
        + modelCoords.get(0).getY());
    System.out.println("X: "
        + modelCoords.get(1).getX()
        + " Y: "
        + modelCoords.get(1).getY());

    for (int i = 0; i < testCoords.size(); i++) {
      assertEquals(testCoords.get(i).getY(), modelCoords.get(i).getY());
      assertEquals(testCoords.get(i).getX(), modelCoords.get(i).getX());
    }
  }

  /**
   * checks tests for the randomShots method
   */
  @Test
  void randomShots() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    testModel.addP2Ships(shipList);
    testModel.addP1Ships(shipList);
    List<Coord> possibleCoords = new ArrayList<>();
    possibleCoords.add(new Coord(10, 10));
    possibleCoords.add(new Coord(1, 1));
    assertNotNull(testModel.randomShots(possibleCoords));
  }

  /**
   * checks tests for the updateCompBoard method
   */
  @Test
  void updateP2Board() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    testModel.addP2Ships(shipList);

    Board testBoard = new Board(10, 10);
    testModel.setDimensions(new int[] {10, 10});
    testModel.addP2Board(testBoard);
    for (Coord[] x : testBoard.getBoard()) {
      for (Coord coord : x) {
        assertFalse(coord.getShot());
        assertFalse(coord.getHide());
      }
    }

    ArrayList<Coord> testHits = new ArrayList<>();
    testHits.add(new Coord(1, 1));
    testHits.add(new Coord(2, 2));

    ArrayList<Coord> testMisses = new ArrayList<>();
    testHits.add(new Coord(3, 1));
    testHits.add(new Coord(4, 2));

    testModel.updateP2Board(testHits, testMisses);

    int counter = 0;
    for (Coord[] x : testModel.getP2Board().getBoard()) {
      for (Coord coord : x) {
        if (coord.getShot()) {
          counter++;
        }
      }
    }
    System.out.println(testBoard.getBoardToDisplay());
    assertTrue(counter > 0);
  }

  /**
   * checks tests for the updateP1Board method
   */
  @Test
  void updateP1Board() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    testModel.addP1Ships(shipList);

    Board testBoard = new Board(10, 10);
    testModel.setDimensions(new int[] {10, 10});
    testModel.addP1Board(testBoard);
    for (Coord[] x : testBoard.getBoard()) {
      for (Coord coord : x) {
        assertFalse(coord.getShot());
        assertFalse(coord.getHide());
      }
    }

    ArrayList<Coord> testHits = new ArrayList<>();
    testHits.add(new Coord(1, 1));
    testHits.add(new Coord(2, 2));

    ArrayList<Coord> testMisses = new ArrayList<>();
    testHits.add(new Coord(3, 1));
    testHits.add(new Coord(4, 2));

    testModel.updateP1Board(testHits, testMisses);

    int counter = 0;
    for (Coord[] x : testModel.getP1Board().getBoard()) {
      for (Coord coord : x) {
        if (coord.getShot()) {
          counter++;
        }
      }
    }
    System.out.println(testBoard.getBoardToDisplay());
    assertTrue(counter > 0);
  }

  /**
   * checks tests for the showP1Shots method
   */
  @Test
  void showP1Shots() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    testModel.addP1Ships(shipList);

    Board testBoard = new Board(10, 10);
    testModel.setDimensions(new int[] {10, 10});
    testModel.addP1Board(testBoard);
    testModel.addP2Board(testBoard);
    for (Coord[] x : testBoard.getBoard()) {
      for (Coord coord : x) {
        assertFalse(coord.getShot());
        assertFalse(coord.getHide());
      }
    }

    ArrayList<Coord> testHits = new ArrayList<>();
    testHits.add(new Coord(1, 1));
    testHits.add(new Coord(2, 2));

    ArrayList<Coord> testMisses = new ArrayList<>();
    testHits.add(new Coord(3, 1));
    testHits.add(new Coord(4, 2));

    testModel.showP1Shots(testHits, testMisses);

    int counter = 0;
    for (Coord[] x : testModel.getP1Board().getBoard()) {
      for (Coord coord : x) {
        if (coord.getShowOpp()) {
          counter++;
        }
      }
    }
    assertTrue(counter > 0);
  }

  /**
   * checks tests for the showOppShots method
   */
  @Test
  void showOppShots() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    testModel.addP1Ships(shipList);

    Board testBoard = new Board(10, 10);
    testModel.setDimensions(new int[] {10, 10});
    testModel.addP1Board(testBoard);
    testModel.addP2Board(testBoard);
    for (Coord[] x : testBoard.getBoard()) {
      for (Coord coord : x) {
        assertFalse(coord.getShot());
        assertFalse(coord.getHide());
      }
    }

    ArrayList<Coord> testHits = new ArrayList<>();
    testHits.add(new Coord(1, 1));
    testHits.add(new Coord(2, 2));

    ArrayList<Coord> testMisses = new ArrayList<>();
    testHits.add(new Coord(3, 1));
    testHits.add(new Coord(4, 2));

    testModel.showP1Shots(testHits, testMisses);

    int counter = 0;
    for (Coord[] x : testModel.getP1Board().getBoard()) {
      for (Coord coord : x) {
        if (coord.getShowOpp()) {
          counter++;
        }
      }
    }
    assertTrue(counter > 0);
  }

  /**
   * checks tests for the allSunk method
   */
  @Test
  void allSunk() {
    List<Ship> testShips = new ArrayList<>();
    testShips.add(new Ship(ShipType.BATTLESHIP));
    testShips.add(new Ship(ShipType.BATTLESHIP));
    testShips.add(new Ship(ShipType.BATTLESHIP));
    for (int i = 0; i < testShips.size(); i++) {
      for (Coord c : testShips.get(i).getCoords()) {
        c.setShot();
      }
    }
    assertTrue(testModel.allSunk(testShips));

    List<Ship> testShips2 = new ArrayList<>();
    testShips2.add(new Ship(ShipType.BATTLESHIP));
    testShips2.add(new Ship(ShipType.BATTLESHIP));
    testShips2.add(new Ship(ShipType.BATTLESHIP));
    for (int i = 0; i < testShips.size(); i++) {
      testShips2.get(i).addCoord(new Coord(10, 10));
    }
    assertFalse(testModel.allSunk(testShips2));
  }

  /**
   * checks tests for the getEndP1 method
   */
  @Test
  void getEndP1() {
    assertNull(testModel.getEndP1());
    testModel.setP1EndMessage(GameResult.WIN, "You sunk all other ships");
    assertEquals("You have obtained a WIN because You sunk all other ships",
        testModel.getEndP1());
  }

  /**
   * checks tests for the getEndP2 method
   */
  @Test
  void getEndP2() {
    assertNull(testModel.getEndP2());
    testModel.setP2EndMessage(GameResult.WIN, "You sunk all other ships");
    assertEquals("You have obtained a WIN because You sunk all other ships",
        testModel.getEndP2());
  }

  /**
   * checks tests for the setP1EndMessage method
   */
  @Test
  void setP1EndMessage() {
    assertNull(testModel.getEndP1());
    testModel.setP1EndMessage(GameResult.WIN, "You sunk all other ships");
    assertEquals("You have obtained a WIN because You sunk all other ships",
        testModel.getEndP1());
  }

  /**
   * checks tests for the setP2EndMessage method
   */
  @Test
  void setP2EndMessage() {
    assertNull(testModel.getEndP2());
    testModel.setP2EndMessage(GameResult.WIN, "You sunk all other ships");
    assertEquals("You have obtained a WIN because You sunk all other ships",
        testModel.getEndP2());
  }

  /**
   * checks tests for the getEndMessages method
   */
  @Test
  void getEndMessages() {
    testModel.setP2EndMessage(GameResult.WIN, "You sunk all other ships");
    testModel.setP1EndMessage(GameResult.LOSE, "All your ships sunk");
    assertEquals("You have obtained a LOSE because All your ships sunk\n"
        + "You have obtained a WIN because You sunk all other ships", testModel.getEndMessages());
  }

  /**
   * checks tests for the allUnsunk method
   */
  @Test
  void allUnsunk() {
    List<Ship> testShips = new ArrayList<>();
    testShips.add(new Ship(ShipType.BATTLESHIP));
    testShips.add(new Ship(ShipType.BATTLESHIP));
    testShips.add(new Ship(ShipType.BATTLESHIP));
    for (int i = 0; i < testShips.size(); i++) {
      for (Coord c : testShips.get(i).getCoords()) {
        c.setShot();
      }
    }
    testModel.addP1Ships(testShips);
    assertEquals(0, testModel.allUnsunk());

    List<Ship> testShips2 = new ArrayList<>();
    testShips2.add(new Ship(ShipType.BATTLESHIP));
    testShips2.add(new Ship(ShipType.BATTLESHIP));
    testShips2.add(new Ship(ShipType.BATTLESHIP));
    for (int i = 0; i < testShips.size(); i++) {
      testShips2.get(i).addCoord(new Coord(10, 10));
    }

    testModel.getP1Ships().clear();
    testModel.addP1Ships(testShips2);
    assertEquals(3, testModel.allUnsunk());
  }
}