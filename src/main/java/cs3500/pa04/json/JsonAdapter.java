package cs3500.pa04.json;

import cs3500.pa03.coord.Coord;
import cs3500.pa03.ships.Orientation;
import cs3500.pa03.ships.Ship;
import java.util.ArrayList;
import java.util.List;

/**
 * class for all adapter methods
 */
public class JsonAdapter {
  /**
   * method for getting direction of ship
   *
   * @param inputShip ship to be converted
   * @return orientation of ship
   */
  private Orientation convertDir(Ship inputShip) {
    Coord start = inputShip.getCoords().get(0);
    Coord next = inputShip.getCoords().get(1);
    return (Math.abs(start.getY() - next.getY()) == 0)
        ? Orientation.HORIZONTAL : Orientation.VERTICAL;
  }

  /**
   * method for converting list of coords to volley
   *
   * @param coords coordinates to be converted
   * @return volleyjson representation of coordinates
   */
  public VolleyJson convertVolley(List<Coord> coords) {
    CoordJson[] resultCoords = new CoordJson[coords.size()];

    for (int i = 0; i < resultCoords.length; i++) {
      resultCoords[i] = convertCoord(coords.get(i));
    }
    return new VolleyJson(resultCoords);
  }

  /**
   * method for converting volley to list of coords
   *
   * @param volley volleyjson
   * @return a list of coords from the volley
   */
  public List<Coord> convertCoords(VolleyJson volley) {
    CoordJson[] coords = volley.coordinates();
    List<Coord> resultCoords = new ArrayList<>();
    for (CoordJson c : coords) {
      resultCoords.add(new Coord(c.x(), c.y()));
    }
    return resultCoords;
  }

  /**
   * converts a single coord to json
   *
   * @param inputCoord coordinate
   * @return the json rep of inputcoord
   */
  private CoordJson convertCoord(Coord inputCoord) {
    return new CoordJson(inputCoord.getX(), inputCoord.getY());
  }

  /**
   * method for converting list of ships to fleet
   *
   * @param ships list of ships
   * @return fleetjson representation of list
   */
  public FleetJson convertFleet(List<Ship> ships) {
    ShipJson[] resultShips = new ShipJson[ships.size()];

    for (int i = 0; i < resultShips.length; i++) {
      resultShips[i] = convertShip(ships.get(i));
    }
    return new FleetJson(resultShips);
  }

  /**
   * method for converting a single ship
   *
   * @param myShip input ship
   * @return shipjson representation of input ship
   */
  public ShipJson convertShip(Ship myShip) {
    Coord start = myShip.getCoords().get(0);
    ShipJson resultShip = new ShipJson(convertCoord(start), myShip.getLives(),
        convertDir(myShip).toString());
    return resultShip;
  }

}
