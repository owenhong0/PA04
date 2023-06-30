package cs3500.pa04.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.coord.Coord;
import cs3500.pa03.model.BattleModel;
import cs3500.pa03.model.Comp;
import cs3500.pa03.ships.Ship;
import cs3500.pa03.ships.ShipType;
import cs3500.pa04.json.EndJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonAdapter;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.VolleyJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for handling server requests
 */
public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final Comp player;
  private final ObjectMapper mapper = new ObjectMapper();
  private BattleModel model;

  private JsonAdapter adapter;
  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param s the socket connection to the server
   * @param comp cpu player
   * @throws IOException if
   */
  public ProxyController(Socket s, Comp comp) throws IOException {
    server = s;
    in = server.getInputStream();
    out = new PrintStream(server.getOutputStream());
    model = new BattleModel();
    player = comp;
    adapter = new JsonAdapter();
  }

  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void run() {
    try {
      JsonParser parser = mapper.getFactory().createParser(in);

      while (!server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      System.err.println("Disconnected from server");
    }
  }

  /**
   * Determines the type of request the server has sent (method) and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJSON used to determine what the server has sent
   */
  private void delegateMessage(MessageJson message) {
    String name = message.method();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin();
    } else if ("setup".equals(name)) {
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots();
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccessHits(arguments);
    } else if ("end-game".equals(name)) {
      handleEndGame(arguments);
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }


  /**
   * sends server join response with github username and arguments
   */
  private void handleJoin() {
    JoinJson arguments = new JoinJson("pa04-owen-nicole", "MULTI");
    JsonNode joinArguments = JsonUtils.serializeRecord(arguments);
    MessageJson clientResponse = new MessageJson("join", joinArguments);
    out.println(JsonUtils.serializeRecord(clientResponse));
  }

  /**
   * Parses the given arguments and delegates to the setup method. will then convert list of ships
   * into FleetJson and send response to server
   *
   * @param arguments the Json representation setup specifications
   */
  private void handleSetup(JsonNode arguments) {
    int height = Integer.parseInt(String.valueOf(arguments.get("height")));
    int width = Integer.parseInt(String.valueOf(arguments.get("width")));

    JsonNode fleet = arguments.get("fleet-spec");

    int submarines = Integer.parseInt(String.valueOf(fleet.get("SUBMARINE")));
    int carriers = Integer.parseInt(String.valueOf(fleet.get("CARRIER")));
    int destroyers = Integer.parseInt(String.valueOf(fleet.get("DESTROYER")));
    int battleships = Integer.parseInt(String.valueOf(fleet.get("BATTLESHIP")));

    Map<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, carriers);
    map.put(ShipType.BATTLESHIP, battleships);
    map.put(ShipType.DESTROYER, destroyers);
    map.put(ShipType.SUBMARINE, submarines);

    List<Ship> ships = player.setup(height, width, map);
    FleetJson shipfleet = adapter.convertFleet(ships);
    JsonNode res = JsonUtils.serializeRecord(shipfleet);
    MessageJson response = new MessageJson("setup", JsonUtils.serializeRecord(shipfleet));
    JsonNode finalmessage = JsonUtils.serializeRecord(response);
    out.println(finalmessage);
  }

  /** delegate to takeshots method, may need to rework player to make all methods work
   * need to
   */
  private void handleTakeShots() {
    List<Coord> coords = new ArrayList<>(player.takeShots());
    VolleyJson volley = adapter.convertVolley(coords);
    JsonNode response = JsonUtils.serializeRecord(volley);
    MessageJson res = new MessageJson("take-shots", response);
    JsonNode finalmessage = JsonUtils.serializeRecord(res);
    out.println(finalmessage);
  }

  /**
   * Parses the given arguments and delegates to the reportdamage method. will then convert list of
   * coords into volleyjson and send response to server
   *
   * @param arguments the Json representation of volleyJson specifications
   */
  private void handleReportDamage(JsonNode arguments) {
    VolleyJson volleyJson = this.mapper.convertValue(arguments, VolleyJson.class);
    List<Coord> coords = player.reportDamage(adapter.convertCoords(volleyJson));
    VolleyJson output = adapter.convertVolley(coords);
    JsonNode response = JsonUtils.serializeRecord(output);
    MessageJson res = new MessageJson("report-damage", response);
    JsonNode finalmessage = JsonUtils.serializeRecord(res);
    out.println(finalmessage);

  }

  /**
   * Parses the given arguments and delegates to the successfulhits method. will then send
   * appropriate response to server
   *
   * @param arguments the Json representation of volleyJson specifications
   */
  private void handleSuccessHits(JsonNode arguments) {
    VolleyJson volleyJson = this.mapper.convertValue(arguments, VolleyJson.class);
    player.successfulHits(adapter.convertCoords(volleyJson));
    MessageJson response = new MessageJson("successful-hits", VOID_RESPONSE);
    JsonNode res = JsonUtils.serializeRecord(response);
    out.println(res);
  }

  /**
   * Parses the given arguments and delegates to the endgame method. will then send appropriate
   * response to server
   *
   * @param arguments the Json representation of endJson specifications
   */
  private void handleEndGame(JsonNode arguments) {
    EndJson gameend = this.mapper.convertValue(arguments, EndJson.class);
    player.endGame(gameend.result(), gameend.reason());
    System.out.println(gameend.reason());
    JsonNode end = JsonUtils.serializeRecord(gameend);
    out.println(end);
    try {
      server.close();
    } catch (IOException e) {
      System.err.println("There was an error with closing the server");
    }

  }
}