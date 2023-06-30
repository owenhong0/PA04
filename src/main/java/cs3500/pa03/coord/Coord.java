package cs3500.pa03.coord;

import cs3500.pa03.ships.Ship;

/**
 * represents a coordinate in the board of battelship
 */
public class Coord {
  private int xcoord;
  private int ycoord;
  private boolean isShot;
  private boolean shipHere;
  private boolean showOpp = false;
  private boolean hide = false;
  private Ship ship;

  /**
   * constructs a coordinate
   *
   * @param col x coordinate
   * @param row y coordinate
   */
  public Coord(int col, int row) {
    xcoord = row;
    ycoord = col;
    isShot = false;
    shipHere = false;
  }

  /**
   * gets the current row number
   *
   * @return the row number of the coordinate
   */
  public int getX() {
    return xcoord;
  }

  /**
   * gets the current column number
   *
   * @return the column number of the coordinate
   */
  public int getY() {
    return ycoord;
  }

  /**
   * gets whether this coordinate has been shot
   *
   * @return whether a player has shot this coordinate or not
   */
  public boolean getShot() {
    return isShot;
  }

  /**
   * sets the coordinate to an already shot status
   */
  public void setShot() {
    isShot = true;
  }

  /**
   * gets the current status of whether a ship is on this coordinate
   *
   * @return whether a ship is on this coordinate or not
   */
  public boolean isShipHere() {
    return shipHere;
  }

  /**
   * places a ship onto the current coordinate
   *
   * @param s a ship to be placed
   */
  public void placeShip(Ship s) {
    shipHere = true;
    ship = s;
  }

  /**
   * gets whether this coordinate is hidden from view
   *
   * @return whether this coordinate is hidden
   */
  public boolean getHide() {
    return hide;
  }

  /**
   * sets this coordinate to be hidden from view
   */
  public void setHide() {
    hide = true;
  }

  /**
   * sets this coordinate to not be hidden from view
   */
  public void unHide() {
    hide = false;
  }

  /**
   * sets the current coordinate to be shown to the opponent
   */
  public void setShowOpp() {
    showOpp = true;
  }

  /**
   * gets the current state of this coordinate to be displayed
   *
   * @return a string of the state of the coordinate
   */
  public String toString() {

    if (isShot && shipHere && showOpp) {
      return "H";
    } else if (isShot && !shipHere && showOpp) {
      return "M";
    }

    if (isShot && shipHere && !hide) {
      return "H";
    } else if (hide) {
      return "-";
    } else if (!isShot && shipHere) {
      return ship.getType().toString().substring(0, 1).toUpperCase();
    } else if (isShot && !shipHere) {
      return "M";
    }
    return "-";
  }

  /**
   * gets whether this coordinate should be displayed to the opponent
   *
   * @return whether to show this coordinate to the opponent
   */
  public boolean getShowOpp() {
    return showOpp;
  }
}
