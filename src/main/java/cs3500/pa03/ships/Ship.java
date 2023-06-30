package cs3500.pa03.ships;

import cs3500.pa03.coord.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a class of a ship
 */
public class Ship {
  private boolean sunk = false;
  private int lives;
  private List<Coord> coords = new ArrayList<>();
  private ShipType type;

  /**
   * constructs a ship
   *
   * @param vesselClass an inputted type of ship
   */
  public Ship(ShipType vesselClass) {
    type = vesselClass;
    if (type.equals(ShipType.CARRIER)) {
      lives = 6;
    } else if (type.equals(ShipType.BATTLESHIP)) {
      lives = 5;
    } else if (type.equals(ShipType.DESTROYER)) {
      lives = 4;
    } else if (type.equals(ShipType.SUBMARINE)) {
      lives = 3;
    }
  }

  /**
   * gets the number of shots a ship can take
   *
   * @return a number of lives a ship has
   */
  public int getLives() {
    return lives;
  }

  /**
   * gets the type of ship
   *
   * @return the type of the current ship
   */
  public ShipType getType() {
    return type;
  }

  /**
   * determines whether a ship is sunk or not
   *
   * @return whether this current ship is sunk
   */
  public boolean isSunk() {
    sunk = true;
    for (Coord coord : coords) {
      if (!coord.toString().equals("H")) {
        sunk = false;
      }
    }
    return sunk;
  }

  /**
   * adds a coordinate to the list of coordinates that represents this ship
   *
   * @param inputCoord a given coordinate
   */
  public void addCoord(Coord inputCoord) {
    coords.add(inputCoord);
  }

  /**
   * gets the list of coordinates that represent this ship
   *
   * @return a list of coordinates of this ship
   */
  public List<Coord> getCoords() {
    List<Coord> coords = new ArrayList<>();
    for (Coord c : this.coords) {
      coords.add(new Coord(c.getY(), c.getX()));
    }
    return coords;
  }
}
