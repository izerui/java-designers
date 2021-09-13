package Decorator;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * The Decorator pattern is a more flexible alternative to subclassing. The Decorator class
 * implements the same interface as the target and uses composition to "decorate" calls to the
 * target. Using the Decorator pattern it is possible to change the behavior of the class during
 * runtime.
 *
 * <p>In this example we show how the simple {@link SimpleTroll} first attacks and then flees the
 * battle. Then we decorate the {@link SimpleTroll} with a {@link ClubbedTroll} and perform the
 * attack again. You can see how the behavior changes after the decoration.
 */
@Slf4j
public class App {

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {

    // simple troll
    LOGGER.info("A simple looking troll approaches.");
    var troll = new SimpleTroll();
    troll.attack();
    troll.fleeBattle();
    LOGGER.info("Simple troll power: {}.\n", troll.getAttackPower());

    // change the behavior of the simple troll by adding a decorator
    LOGGER.info("A troll with huge club surprises you.");
    var clubbedTroll = new ClubbedTroll(troll);
    clubbedTroll.attack();
    clubbedTroll.fleeBattle();
    LOGGER.info("Clubbed troll power: {}.\n", clubbedTroll.getAttackPower());
  }
}
