package cs3500.pa04;

import cs3500.pa03.model.BattleModel;
import cs3500.pa03.model.Comp;
import cs3500.pa04.client.ProxyController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("143.198.169.138", 35001);
      Appendable output = System.out;
      Readable input = new InputStreamReader(System.in);
      ProxyController
          controller = new ProxyController(socket, new Comp(new BattleModel(), "pa04-owen-nicole"));
      controller.run();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println(e);
      System.out.println(":(");
    }
  }
}