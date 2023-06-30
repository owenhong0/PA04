//package cs3500.pa03.view;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import cs3500.pa03.coord.Board;
//import cs3500.pa03.coord.Coord;
//import cs3500.pa03.model.BattleModel;
//import cs3500.pa03.model.Comp;
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
///**
// * represents tests for the BattleView class
// */
//class BattleViewTest {
//  private View testView;
//  private View mockView;
//  private StringWriter writer;
//  private MockAppendable mock;
//  private BattleModel model;
//
//  @BeforeEach
//  public void setUp() {
//    writer = new StringWriter();
//    testView = new BattleView(writer);
//    mock = new MockAppendable();
//    mockView = new BattleView(mock);
//    model = new BattleModel();
//  }
//
//  /**
//   * checks tests for the printWelcome method
//   */
//  @Test
//  void printWelcome() {
//    int[] ints = new int[] {10, 10};
//    for (int i = 0; i < 2; i++) {
//      assertEquals(ints[i], testView.printWelcome());
//    }
//
//    Runnable runnable = () -> mockView.printWelcome();
//    runnable.run();
//  }
//
//  /**
//   * checks tests for the printInvalidBoard method
//   */
//  @Test
//  void printInvalidBoard() {
//    int[] ints = new int[] {10, 10};
//    for (int i = 0; i < 2; i++) {
//      assertEquals(ints[i], testView.printInvalidBoard()[i]);
//    }
//
//    Runnable runnable = () -> mockView.printInvalidBoard();
//    runnable.run();
//  }
//
//  /**
//   * checks tests for the printFleetSelection method
//   */
//  @Test
//  void printFleetSelection() {
//    int[] ints = new int[] {1, 1, 1, 1};
//    for (int i = 0; i < 2; i++) {
//      assertEquals(ints[i], testView.printFleetSelection(4)[i]);
//    }
//
//    Runnable runnable = () -> mockView.printFleetSelection(4);
//    runnable.run();
//  }
//
//  /**
//   * checks tests for the printInvalidFleet method
//   */
//  @Test
//  void printInvalidFleet() {
//    int[] ints = new int[] {1, 1, 1, 1};
//    for (int i = 0; i < 2; i++) {
//      assertEquals(ints[i], testView.printInvalidFleet(4)[i]);
//    }
//
//    Runnable runnable = () -> mockView.printInvalidFleet(4);
//    runnable.run();
//  }
//
//  /**
//   * checks tests for the printBoards method
//   */
//  @Test
//  void printBoards() {
//    Board test = new Board(10, 10);
//    List<Coord> shots = new ArrayList<>();
//    shots.add(new Coord(1, 1));
//    shots.add(new Coord(1, 2));
//    shots.add(new Coord(1, 3));
//    shots.add(new Coord(1, 4));
//    model.addP2Board(test);
//    model.addP1Board(test);
//    model.addP1Ships(new Comp(model,
//        "CPU", shots).setup(10, 10, model.setRandomFleet()));
//    model.addP2Ships(new Comp(model,
//        "CPU2", shots).setup(10, 10, model.setRandomFleet()));
//
//    testView.printBoards(model.getBoardsToDisplay());
//    assertEquals(model.getBoardsToDisplay(), writer.toString());
//  }
//
//  /**
//   * checks tests for the printSalvo method
//   */
//  @Test
//  void printSalvo() {
//    int[] ints = new int[] {1, 1, 1, 1};
//    for (int i = 0; i < 2; i++) {
//      System.out.println(Arrays.toString(testView.printSalvo(new StringReader("1 1 1 1"), 2)));
//      assertEquals(0, testView.printSalvo(new StringReader("1 1 1 1"), 2)[i]);
//    }
//
//    Runnable runnable = () -> mockView.printSalvo(new StringReader("1 1 1 1"), 2);
//    runnable.run();
//  }
//
//  /**
//   * checks tests for the printEnd method
//   */
//  @Test
//  void printEnd() {
//    testView.printEnd("You Lost!");
//    assertEquals("You Lost!", writer.toString());
//  }
//}