package cs3500.pa03.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * represents a view for battleship
 */
public class BattleView implements View {
  private Appendable output;
  private Scanner scanner;

  /**
   * constructs a battleview
   *
   * @param in input stream
   * @param outStream an output stream
   */
  public BattleView(Readable in, Appendable outStream) {
    output = outStream;
    scanner = new Scanner(in);
  }

  /**
   * displays the welcome message to the player and prompts for the board size
   *
   * @return an int array containing board dimensions
   */
  @Override
  public int[] printWelcome() {
    StringBuilder builder = new StringBuilder("Hello! Welcome to the OOD Battle Salvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "------------------------------------------------------\n");
    return getDimensions(builder);
  }

  /**
   * gets the dimensions of the board inputted by the user
   *
   * @param builder an inputted welcome message
   * @return an array of numbers of the dimensions of the board
   */
  private int[] getDimensions(StringBuilder builder) {
    int[] boardDimensions = new int[2];
    try {
      output.append(builder);
    } catch (IOException e) {
      System.err.println("There was an error in printing the welcome message");
    }

    try {
      for (int i = 0; i < 2; i++) {
        boardDimensions[i] = scanner.nextInt();
      }
    } catch (InputMismatchException e) {
      System.err.println("Please only input numbers");
    }
    System.out.println(Arrays.toString(boardDimensions));
    return boardDimensions;
  }

  /**
   * gets another user input for the dimensions of the board if previously inputted incorrectly
   *
   * @return an array that represents another set of dimensions
   */
  public int[] printInvalidBoard() {
    StringBuilder builder = new StringBuilder(
        "Uh Oh! You've entered invalid dimensions. Please remember that the height and width\n"
            + "of the game must be in the range (6, 15), inclusive. Try again!\n"
            + "------------------------------------------------------\n");
    return getDimensions(builder);
  }

  /**
   * displays the message for the player to input their fleet specifications
   *
   * @param maxFleetSize the number limit of how big a fleet can be
   * @return an array of ints of how many of each ship the player wants
   */
  public int[] printFleetSelection(int maxFleetSize) {
    StringBuilder builder = new StringBuilder();
    builder.append(
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet may not exceed size ").append(maxFleetSize).append("\n");

    return getFleet(builder);
  }

  /**
   * prompts the user to input another set of fleet specifications
   *
   * @param maxFleetSize the max amount of ships to be inputted
   * @return an array representing a fleet of the user's possible ships
   */
  public int[] printInvalidFleet(int maxFleetSize) {
    StringBuilder builder = new StringBuilder();
    builder.append("Uh Oh! You've entered invalid fleet sizes.");
    builder.append("\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size ").append(maxFleetSize).append("\n");

    return getFleet(builder);
  }

  /**
   * helper to actually get user input for fleet selection
   *
   * @param builder a string to prompt the user to enter specifications
   * @return an array of ship numbers for selection
   */
  private int[] getFleet(StringBuilder builder) {
    int[] fleetSpecs = new int[4];
    try {
      output.append(builder);
    } catch (IOException e) {
      System.err.println("There was an error in printing the fleet selection");
    }

    try {
      for (int i = 0; i < 4; i++) {

        fleetSpecs[i] = scanner.nextInt();
      }
    } catch (InputMismatchException e) {
      System.err.println("Please only input numbers");
    }
    return fleetSpecs;
  }

  /**
   * displays an inputted board to the player
   *
   * @param boards an inputted string of boards to be displayed
   */
  @Override
  public void printBoards(String boards) {
    try {
      output.append(boards);
    } catch (IOException e) {
      System.err.println("Error printing out boards");
    }
  }

  /**
   * prompts the user to input a salvo of shots to be processed
   *
   * @param salvoSize the max amont of coordinates to be inputted
   * @return an array representing a salvo of shots by the user
   */
  public int[] printSalvo(int salvoSize) {
    int[] shots = new int[salvoSize * 2];
    System.out.println(salvoSize * 2);
    try {
      for (int i  = 0; i < salvoSize * 2; i++) {
        if (scanner.hasNext()) {
          System.out.println("1 here");
        }
        int originalShot = scanner.nextInt();
        shots[i] = originalShot - 1;
        System.out.println("i: " + i);
      }
    } catch (InputMismatchException e) {
      System.err.println("Please only input numbers");
    }
    System.out.println("shots: " + Arrays.toString(shots));
    return shots;
  }

  /**
   * displays to the user whether they have won or lost or had a draw
   *
   * @param result an inputted end game string
   */
  @Override
  public void printEnd(String result) {
    try {
      output.append(result);
    } catch (IOException e) {
      System.err.println("Error printing end game message");
    }
  }
}
