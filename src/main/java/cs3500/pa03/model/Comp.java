package cs3500.pa03.model;

import cs3500.pa03.GameResult;
import cs3500.pa03.coord.Board;
import cs3500.pa03.coord.Coord;
import cs3500.pa03.ships.Ship;
import cs3500.pa03.ships.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * represents a computer class of the player interface
 */
public class Comp implements Player {
  private BattleModel model;
  private String username;
  private List<Coord> volley;
  private List<Coord> possibleshots;

  /**
   * constructs a cpu player
   *
   * @param m an inputted model
   * @param name an inputted name for the cpu
   */
  public Comp(BattleModel m, String name) {
    model = m;
    username = name;
    volley = new ArrayList<Coord>();
    possibleshots = new ArrayList<>();
  }

  /**
   * constructor for testing purposes
   *
   * @param m inputted model
   * @param name name of cpu
   * @param volley inputted shots
   */
  public Comp(BattleModel m, String name, List<Coord> volley) {
    this(m, name);
    this.volley = volley;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return username;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    Board compBoard = new Board(height, width);
    compBoard.mapShipsToBoard(specifications);
    model.addP2Board(compBoard);
    possibleshots = model.createshots();
    return compBoard.getShipsOnBoard();
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    return model.randomShots(possibleshots);
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *         ship on this board.
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hits = model.filterShots(opponentShotsOnBoard);
    List<Coord> misses = model.filtermiss(opponentShotsOnBoard);
    model.updateP2Board(hits, misses);
    return hits;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    volley.removeAll(shotsThatHitOpponentShips);
    List<Coord> misses = volley;
    model.showP2Shots(shotsThatHitOpponentShips, misses);
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    model.setP2EndMessage(result, reason);
  }
}
