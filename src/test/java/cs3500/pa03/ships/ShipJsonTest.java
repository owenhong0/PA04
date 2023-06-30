package cs3500.pa03.ships;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.coord.Coord;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the Ship class
 */
class ShipJsonTest {
  private Ship testShip;
  private Ship testShip2;
  private Ship testShip3;
  private Ship testShip4;

  @BeforeEach
  public void setUp() {
    testShip = new Ship(ShipType.BATTLESHIP);
    testShip2 = new Ship(ShipType.CARRIER);
    testShip3 = new Ship(ShipType.DESTROYER);
    testShip4 = new Ship(ShipType.SUBMARINE);
  }

  /**
   * checks tests for the getLives method
   */
  @Test
  void getLives() {
    assertEquals(5, testShip.getLives());
    assertEquals(6, testShip2.getLives());
    assertEquals(4, testShip3.getLives());
    assertEquals(3, testShip4.getLives());
  }

  /**
   * checks tests for the getType method
   */
  @Test
  void getType() {
    assertEquals(ShipType.BATTLESHIP, testShip.getType());
    assertEquals(ShipType.CARRIER, testShip2.getType());
    assertEquals(ShipType.DESTROYER, testShip3.getType());
    assertEquals(ShipType.SUBMARINE, testShip4.getType());
  }

  /**
   * checks tests for the isSunk method
   */
  @Test
  void isSunk() {

    for (int i = 0; i < testShip.getLives(); i++) {
      testShip.addCoord(new Coord(i, i));
    }
    assertFalse(testShip.isSunk());
    for (Coord coord : testShip.getCoords()) {
      coord.placeShip(testShip);
      coord.setShot();
    }
    assertFalse(testShip.isSunk());
  }

  /**
   * checks tests for the addCoord method
   */
  @Test
  void addCoord() {
    assertEquals(0, testShip.getCoords().size());
    testShip.addCoord(new Coord(10, 10));
    assertTrue(testShip.getCoords().size() > 0);
  }

  /**
   * check tests for the getCoords method
   */
  @Test
  void getCoords() {
    List<Coord> empty = new ArrayList<>();
    Coord testCoord = new Coord(10, 10);
    assertEquals(empty, testShip.getCoords());
    testShip.addCoord(testCoord);
    empty.add(testCoord);
    assertEquals(testCoord.getX(), testShip.getCoords().get(0).getX());
  }
}