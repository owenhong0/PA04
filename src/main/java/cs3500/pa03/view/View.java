package cs3500.pa03.view;

/**
 * represents the interface for the view of battleship
 */
public interface View {

  /**
   * displays the welcome message to the player and prompts for the board size
   *
   * @return an int array containing board dimensions
   */
  int[] printWelcome();

  /**
   * displays the message for the player to input their fleet specifications
   *
   * @param maxFleetSize the number limit of how big a fleet can be
   * @return an array of ints of how many of each ship the player wants
   */
  int[] printFleetSelection(int maxFleetSize);

  /**
   * prompts the user to input another set of fleet specifications
   *
   * @param maxFleetSize the max amount of ships to be inputted
   * @return an array representing a fleet of the user's possible ships
   */
  int[] printInvalidFleet(int maxFleetSize);

  /**
   * displays an inputted board to the player
   *
   * @param boards an inputted string of boards to be displayed
   */
  void printBoards(String boards);

  /**
   * displays to the user whether they have won or lost or had a draw
   *
   * @param result an inputted end game string
   */
  void printEnd(String result);

  /**
   * gets another user input for the dimensions of the board if previously inputted incorrectly
   *
   * @return an array that represents another set of dimensions
   */
  int[] printInvalidBoard();

  /**
   * prompts the user to input a salvo of shots to be processed
   *
   * @param salvoSize the max amont of coordinates to be inputted
   * @return an array representing a salvo of shots by the user
   */
  int[] printSalvo(int salvoSize);
}
