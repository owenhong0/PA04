package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.BattleModel;
import cs3500.pa03.model.Comp;
import cs3500.pa04.client.ProxyController;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for proxycontrollet class
 */
public class ProxyControllerTest {
  private ByteArrayOutputStream testLog;
  private ProxyController controller;
  private String testTakeShots;
  private String serverSetup;
  private String reportDamge;
  private String successfulHits;
  private String endGame;


  /**
   * reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());

    serverSetup = "{\n"
        + "\t\"method-name\": \"setup\",\n"
        + "\t\"arguments\": {\n"
        + "\t\t\"width\": 10,\n"
        + "\t\t\"height\": 10,\n"
        + "\t\t\"fleet-spec\": {\n"
        + "\t\t\t\"CARRIER\": 1,\n"
        + "\t\t\t\"BATTLESHIP\": 1,\n"
        + "\t\t  \"DESTROYER\": 1,\n"
        + "\t\t\t\"SUBMARINE\": 1\n"
        + "\t\t}\n"
        + "\t}\n"
        + "}";

    testTakeShots = "{\n"
        + "\t\"method-name\": \"take-shots\",\n"
        + "\t\"arguments\": {}\n"
        + "}";

    reportDamge = "{\n"
        + "\t\"method-name\": \"report-damage\",\n"
        + "\t\"arguments\": {\n"
        + "\t\t\"coordinates\": [\n"
        + "\t\t\t{\"x\": 0, \"y\": 1},\n"
        + "\t\t\t{\"x\": 3, \"y\": 2}\n"
        + "\t\t]\n"
        + "\t}\n"
        + "}";

    successfulHits = "{\n"
        + "\t\"method-name\": \"successful-hits\",\n"
        + "\t\"arguments\": {\n"
        + "\t\t\"coordinates\": [\n"
        + "\t\t\t{\"x\": 0, \"y\": 1},\n"
        + "\t\t\t{\"x\": 3, \"y\": 2}\n"
        + "\t\t]\n"
        + "\t}\n"
        + "}";

    endGame = "{\n"
        + "\t\"method-name\": \"end-game\",\n"
        + "\t\"arguments\": {}\n"
        + "}";
  }

  /**
   * Check that the server accepts our playerInfo for the join() method
   */
  @Test
  public void testJoin() {
    JoinJson testJoin = new JoinJson("owenhong0", "SINGLE");

    JsonNode testMessage1 = createSampleMessage("join", testJoin);

    Mocket socket1 = new Mocket(testLog, List.of(testMessage1.toString()));

    try {
      controller = new ProxyController(socket1, new Comp(new BattleModel(), "owenhong0"));
    } catch (IOException e) {
      fail();
    }

    controller.run();
    System.out.println(logToString());
    String expected = "{\"method-name\":\"join\",\"arguments\":{\"name\":\"owenhong0\","
        + "\"game-type\":\"SINGLE\"}}\n";
    assertEquals(expected, logToString());
    responseToClass(MessageJson.class);

  }

  /**
   * Checks that the server accepts our fleetInfo for the setup() method
   */
  @Test
  public void testSetup() {
    Mocket socket1 = new Mocket(testLog, List.of(serverSetup));

    try {
      controller = new ProxyController(socket1, new Comp(new BattleModel(), "owenhong0"));
    } catch (IOException e) {
      System.err.println("Disconnected from server");
    }
    controller.run();
    assertTrue(logToString().contains("{\"method-name\":\"setup\",\"arguments\":"
        + "{\"fleet\":[{\"coord\":{\"x\":"));
    responseToClass(MessageJson.class);
  }

  /**
   * test for handle take shots
   */
  @Test
  public void testTakeShots() {

    Mocket socket1 = new Mocket(testLog, List.of(serverSetup, testTakeShots));

    try {
      controller = new ProxyController(socket1, new Comp(new BattleModel(), "owenhong0"));
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      System.err.println("Disconnected from server");
    }

    controller.run();
    assertTrue(logToString().contains("{\"method-name\":\"take-shots\",\"arguments\""
        + ":{\"coordinates\":[{\"x\":"));
    responseToClass(MessageJson.class);
  }


  /**
   * test for handlereportdamage method
   */
  @Test
  public void testReportDamage() {

    Mocket socket1 = new Mocket(testLog, List.of(serverSetup, testTakeShots, reportDamge));

    try {
      controller = new ProxyController(socket1, new Comp(new BattleModel(), "owenhong0"));
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      System.err.println("Disconnected from server");
    }

    controller.run();
    assertTrue(logToString().contains("{\"method-name\":\"report-damage\",\"arguments\":"
        + "{\"coordinates\":["));
    responseToClass(MessageJson.class);
  }

  /**
   * test for handlesuccessfulhits method
   */
  @Test
  public void testSuccessfulHits() {

    Mocket socket1 = new Mocket(testLog,
        List.of(serverSetup, testTakeShots, reportDamge, successfulHits));

    try {
      controller = new ProxyController(socket1, new Comp(new BattleModel(), "owenhong0"));
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      System.err.println("Disconnected from server");
    }

    controller.run();
    assertTrue(logToString().contains("{\"method-name\":\"successful-hits\","
        + "\"arguments\":\"void\"}"));
    responseToClass(MessageJson.class);
  }

  /**
   * test for end game method
   */
  @Test
  public void testEndGame() {

    Mocket socket1 = new Mocket(testLog,
        List.of(serverSetup, testTakeShots, reportDamge, successfulHits, endGame));

    try {
      controller = new ProxyController(socket1, new Comp(new BattleModel(), "owenhong0"));
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      System.err.println("Disconnected from server");
    }

    controller.run();
    System.out.println(logToString());
    responseToClass(MessageJson.class);
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Create a MessageJson for some method and arguments.
   *
   * @param methodName name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String methodName, Record messageObject) {
    MessageJson messageJson = new MessageJson(methodName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }
}
