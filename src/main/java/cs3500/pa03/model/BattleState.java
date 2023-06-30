package cs3500.pa03.model;

import cs3500.pa03.coord.Board;
import cs3500.pa03.ships.Ship;
import java.util.List;

/**
 * interface for battlestate
 */
public interface BattleState {

  /**
   * gets the smallest dimension of the current battleship board
   *
   * @return an int of the smaller dimension of the battleship board
   */
  int getSmallestDim();

  /**
   * gets both players' boards to be displayed
   *
   * @return a string representing instructions and each board to the user
   */
  String getBoardsToDisplay();

  /**
   * gets the height of the board
   *
   * @return an int of the height
   */
  int getHeight();

  /**
   * gets the width of the board
   *
   * @return the number of columns in the board
   */
  int getWidth();

  /**
   * returns the number of ships specified by the user
   *
   * @param fleetSelection inputted fleet specifications
   * @return total number of ships inputted
   */
  int getShips(int[] fleetSelection);

  /**
   * gets the computer's board
   *
   * @return a board controlled by the cpu
   */
  Board getP2Board();

  /**
   * gets the collective end message for the end of the game
   *
   * @return the string that combines all end states
   */
  String getEndMessages();

  /**
   * gets the number of ships that are not sunk
   *
   * @return int of operational ships
   */
  int allUnsunk();

  /**
   * gets the list of ships a user is playing with
   *
   * @return a list of user's ships
   */
  List<Ship> getP1Ships();

  /**
   * gets the list of computer ships
   *
   * @return a list of ships controlled by the cpu
   */
  List<Ship> getP2Ships();

  /**
   * gets the user's end of game message
   *
   * @return a string representing the end state of the game
   */
  String getEndP1();

  /**
   * gets the computer's end of game message
   *
   * @return a string representing the end state of the game
   */
  String getEndP2();

  /**
   * gets the current user's board
   *
   * @return a board representing the user
   */
  Board getP1Board();
}
