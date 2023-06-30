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
 * represents tests for the Comp class
 */
class CompTest {
  private BattleModel testModel;
  private Player testComp;
  private List<Coord> testShots;

  /**
   * setup method
   */
  @BeforeEach
  public void setUp() {
    testShots = new ArrayList<>();
    testShots.add(new Coord(1, 1));
    testShots.add(new Coord(1, 2));
    testShots.add(new Coord(1, 3));
    testShots.add(new Coord(1, 4));
    testModel = new BattleModel();
    testComp = new Comp(testModel, "CPU", testShots);
  }

  /**
   * checks tests for the name method
   */
  @Test
  void name() {
    assertEquals("CPU", testComp.name());
  }

  /**
   * checks tests for the setup method
   */
  @Test
  void setup() {
    Map<ShipType, Integer> testFleet = new LinkedHashMap<>();
    testFleet.put(ShipType.CARRIER, 1);
    testFleet.put(ShipType.BATTLESHIP, 1);
    testFleet.put(ShipType.DESTROYER, 1);
    testFleet.put(ShipType.SUBMARINE, 1);

    List<Ship> testShips = new ArrayList<>();
    testShips.add(new Ship(ShipType.CARRIER));
    testShips.add(new Ship(ShipType.BATTLESHIP));
    testShips.add(new Ship(ShipType.DESTROYER));
    testShips.add(new Ship(ShipType.SUBMARINE));

    List<Ship> actualShips = testComp.setup(10, 10, testFleet);

    for (int i = 0; i < 4; i++) {
      assertEquals(actualShips.get(i).getType(), testShips.get(i).getType());
      assertEquals(actualShips.get(i).getLives(), testShips.get(i).getLives());
    }
  }

  /**
   * checks tests for the takeShots method
   */
  @Test
  void takeShots() {
    testComp.setup(10, 10, testModel.setRandomFleet());
    assertNotNull(testComp.takeShots());
  }

  /**
   * checks tests for the reportDamage method
   */
  @Test
  void reportDamage() {
    Map<ShipType, Integer> testFleet = new LinkedHashMap<>();
    testFleet.put(ShipType.CARRIER, 1);
    testFleet.put(ShipType.BATTLESHIP, 1);
    testFleet.put(ShipType.DESTROYER, 1);
    testFleet.put(ShipType.SUBMARINE, 1);

    List<Ship> actualShips = testComp.setup(10, 10, testFleet);
    List<Coord> oneShip = actualShips.get(0).getCoords();
    List<Coord> corner = new ArrayList<>();
    corner.add(new Coord(0, 0));

    assertNotNull(testComp.reportDamage(oneShip));
  }

  /**
   * check tests for the successfulHits method
   */
  @Test
  void successfulHits() {
    List<Ship> shipList = new ArrayList<>();
    Ship testShip = new Ship(ShipType.BATTLESHIP);
    Ship testShip2 = new Ship(ShipType.CARRIER);
    shipList.add(testShip);
    shipList.add(testShip2);
    testModel.addP1Ships(shipList);

    Board testBoard =  new Board(10, 10);
    testModel.setDimensions(new int[] {10, 10});
    testModel.addP1Board(testBoard);
    testModel.addP2Board(testBoard);
    for (Coord[] row : testBoard.getBoard()) {
      for (Coord coord : row) {
        assertFalse(coord.getShowOpp());
      }
    }

    ArrayList<Coord> testHits = new ArrayList<>();
    testHits.add(new Coord(1, 1));
    testHits.add(new Coord(2, 2));

    ArrayList<Coord> testMisses = new ArrayList<>();
    testMisses.add(new Coord(3, 1));
    testMisses.add(new Coord(4, 2));

    testHits.addAll(testMisses);
    testComp.successfulHits(testHits);

    int counter = 0;
    for (Coord[] row : testModel.getP1Board().getBoard()) {
      for (Coord coord : row) {
        if (coord.getShowOpp()) {
          counter++;
        }
      }
    }
    assertTrue(counter > 0);
  }

  /**
   * checks tests for the endGame method
   */
  @Test
  void endGame() {
    assertNull(testModel.getEndP2());
    testComp.endGame(GameResult.WIN, "You sunk all other ships");
    assertEquals("You have obtained a WIN because You sunk all other ships",
        testModel.getEndP2());
  }
}