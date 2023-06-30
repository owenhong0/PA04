package cs3500.pa03.model;

import cs3500.pa03.GameResult;
import cs3500.pa03.coord.Board;
import cs3500.pa03.coord.Coord;
import cs3500.pa03.ships.Ship;
import cs3500.pa03.ships.ShipType;
import java.util.List;
import java.util.Map;

/**
 * interface for battle interactions
 */
public interface BattleInteractions extends BattleState {
  /**
   * sets the dimensions of the battleship board to given dimensions
   *
   * @param boardDimensions inputted dimensions
   */
  void setDimensions(int[] boardDimensions);

  /**
   * sets the cpu's board to a given board
   *
   * @param p2Board an inputted board
   */
  void addP2Board(Board p2Board);

  /**
   * sets the user's board to a given board
   *
   * @param p1Board an inputted board
   */
  void addP1Board(Board p1Board);

  /**
   * converts fleet specifications into a map of ships and how many of each there are
   *
   * @param fleetSelection inputted fleet specifications
   * @return a map of a fleet
   */
  Map<ShipType, Integer> convertFleet(int[] fleetSelection);

  /**
   * gets a randomly generated fleet
   *
   * @return a map of a randomly specified fleet
   */
  Map<ShipType, Integer> setRandomFleet();

  /**
   * sets the user's ships to a given list of ships
   *
   * @param p1Ships an inputted list of ships
   */
  void addP1Ships(List<Ship> p1Ships);

  /**
   * sets the computer's ships to a given list of ships
   *
   * @param p2Ships an inputted list of ships
   */
  void addP2Ships(List<Ship> p2Ships);

  /**
   * converts an array of shot coordinates into a list of coordinates
   *
   * @param shotSelections an inputted array of shot coordinates
   * @return a list of coordinates of shots
   */
  List<Coord> convertShots(int[] shotSelections);

  /**
   * generates a list of random coordinate shots
   *
   * @param possible coordinates
   * @return a list of coordinates of shots
   */
  List<Coord> randomShots(List<Coord> possible);

  /**
   * updates the computer's board with hits and misses
   *
   * @param hitList an inputted list of hits shots
   * @param missList an inputted list of missed shots
   */
  void updateP2Board(List<Coord> hitList, List<Coord> missList);

  /**
   * updates the user's board with hits and misses
   *
   * @param hitList an inputted list of hit shots
   * @param missList an inputted list of missed shots
   */
  void updateP1Board(List<Coord> hitList, List<Coord> missList);

  /**
   * updates the user's board with hits and misses to be displayed to the computer
   *
   * @param shotsThatHitOpponentShips an inputted list of hit coordinates
   * @param misses an inputted list of missed coordinates
   */
  void showP1Shots(List<Coord> shotsThatHitOpponentShips, List<Coord> misses);

  /**
   * updates the computer's board with hits and misses to be displayed to the user
   *
   * @param shotsThatHitOpponentShips an inputted list of hit coordinates
   * @param misses an inputted list of missed coordinates
   */
  void showP2Shots(List<Coord> shotsThatHitOpponentShips, List<Coord> misses);

  /**
   * determines whether all ships in a given list are sunk
   *
   * @param ships a given list of ships
   * @return whether all ships are sunk in that list
   */
  boolean allSunk(List<Ship> ships);

  /**
   * sets the user's end game message
   *
   * @param result a given end state of the game
   * @param reason a string that describes the end state
   */
  void setP1EndMessage(GameResult result, String reason);

  /**
   *  sets the cpu's end game message
   *
   * @param result a given end state of the game
   * @param reason a string that describes the end state
   */
  void setP2EndMessage(GameResult result, String reason);

  /**
   * determines whether given dimensions are valid board sizes
   *
   * @param dimensions inputted dimensions
   * @return whether a board can be constructed with such dimensions or not
   */
  boolean checkDimensions(int[] dimensions);

  /**
   * determines whether given fleet specifications are valid for the game
   *
   * @param fleetSelection inputted fleet specifications
   * @return whether a fleet can be constructed or not
   */
  boolean checkFleet(int[] fleetSelection);

  /**
   * determines whether inputted shots are valid shots on the board
   *
   * @param shotSelections an inputted array of coordinates
   * @return whether all of those shots fall into the game requirements
   */
  boolean checkShots(int[] shotSelections);
}
