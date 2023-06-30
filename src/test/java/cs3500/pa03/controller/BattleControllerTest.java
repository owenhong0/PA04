package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.BattleModel;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * test class for battlecontroller
 */
class BattleControllerTest {
  private Controller testController;
  private BattleModel model;
  private StringWriter writer = new StringWriter();
  private Readable input;

  /**
   * tests run method
   */
  @Test
  void run() {
    StringBuilder builder = new StringBuilder("10 10\n 1 1 1 1\n 1 1 2 2 3 3 4 4");
    input = new StringReader(builder.toString());
    model = new BattleModel();
    testController = new BattleController(input, writer, model);
    assertThrows(NoSuchElementException.class, () -> testController.run());
  }
}