package Bridge;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * Composition over inheritance. The Bridge pattern can also be thought of as two layers of
 * abstraction. With Bridge, you can decouple an abstraction from its implementation so that the two
 * can vary independently.
 *
 * <p>In Bridge pattern both abstraction ({@link Weapon}) and implementation ( {@link Enchantment})
 * have their own class hierarchies. The interface of the implementations can be changed without
 * affecting the clients.
 *
 * <p>In this example we have two class hierarchies. One of weapons and another one of
 * enchantments. We can easily combine any weapon with any enchantment using composition instead of
 * creating deep class hierarchy.
 */
@Slf4j
public class App {

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    LOGGER.info("The knight receives an enchanted sword.");
    var enchantedSword = new Sword(new SoulEatingEnchantment());
    enchantedSword.wield();
    enchantedSword.swing();
    enchantedSword.unwield();

    LOGGER.info("The valkyrie receives an enchanted hammer.");
    var hammer = new Hammer(new FlyingEnchantment());
    hammer.wield();
    hammer.swing();
    hammer.unwield();
  }
}
