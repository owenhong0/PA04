package cs3500.pa03.coord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.ships.Ship;
import cs3500.pa03.ships.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the Coord class
 */
class CoordJsonTest {
  private Coord testCoord;
  private Coord testCoord2;
  private Coord testCoord3;
  private Coord testCoord4;
  private Coord testCoord5;

  /**
   * setup method
   */
  @BeforeEach
  public void setUp() {
    testCoord = new Coord(10, 10);
    testCoord2 = new Coord(6, 6);
    testCoord3 = new Coord(7, 7);
    testCoord4 = new Coord(8, 8);
    testCoord5 = new Coord(9, 9);
  }

  /**
   * checks tests for the getRow method
   */
  @Test
  void getX() {
    assertEquals(10, testCoord.getX());
  }

  /**
   * checks tests for the getCol method
   */
  @Test
  void getY() {
    assertEquals(10, testCoord.getY());
  }

  /**
   * checks tests for the getShot method
   */
  @Test
  void getShot() {
    assertFalse(testCoord.getShot());
  }

  /**
   * checks tests for the setShot method
   */
  @Test
  void setShot() {
    assertFalse(testCoord.getShot());
    testCoord.setShot();
    assertTrue(testCoord.getShot());
  }

  /**
   * check tests for the isShipHere method
   */
  @Test
  void isShipHere() {
    assertFalse(testCoord.isShipHere());
  }

  /**
   * checks tests for the placeShip method
   */
  @Test
  void placeShip() {
    assertFalse(testCoord.isShipHere());
    testCoord.placeShip(new Ship(ShipType.BATTLESHIP));
    assertTrue(testCoord.isShipHere());
    assertEquals("B", testCoord.toString());
  }

  /**
   * checks tests for the getHide method
   */
  @Test
  void getHide() {
    assertFalse(testCoord.getHide());
    testCoord.setHide();
    assertTrue(testCoord.getHide());
  }

  /**
   * checks tests for the setHide method
   */
  @Test
  void setHide() {
    assertFalse(testCoord.getHide());
    testCoord.setHide();
    assertTrue(testCoord.getHide());
  }

  /**
   * checks tests for the unHide method
   */
  @Test
  void unHide() {
    assertFalse(testCoord.getHide());
    testCoord.setHide();
    assertTrue(testCoord.getHide());
    testCoord.unHide();
    assertFalse(testCoord.getHide());
  }

  /**
   * checks tests for the getShowOpp method
   */
  @Test
  void getShowOpp() {
    assertFalse(testCoord.getShowOpp());
  }

  /**
   * checks tests for the setShowOpp method
   */
  @Test
  void setShowOpp() {
    assertFalse(testCoord.getShowOpp());
    testCoord.setShowOpp();
    assertTrue(testCoord.getShowOpp());
  }

  /**
   * checks tests for the toString method
   */
  @Test
  void testToString() {
    assertEquals("-", testCoord.toString());
    testCoord.placeShip(new Ship(ShipType.BATTLESHIP));
    assertEquals("B", testCoord.toString());
    testCoord.setShot();
    testCoord.setShowOpp();
    assertEquals("H", testCoord.toString());
    testCoord2.setShot();
    testCoord2.setShowOpp();
    assertEquals("M", testCoord2.toString());
    testCoord3.setShot();
    assertEquals("M", testCoord3.toString());
    testCoord4.setHide();
    assertEquals("-", testCoord4.toString());
    testCoord5.placeShip(new Ship(ShipType.DESTROYER));
    testCoord5.setShot();
    testCoord5.unHide();
    assertEquals("H", testCoord5.toString());
  }
}