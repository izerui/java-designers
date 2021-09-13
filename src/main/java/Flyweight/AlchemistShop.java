package Flyweight;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * AlchemistShop holds potions on its shelves. It uses PotionFactory to provide the potions.
 */
@Slf4j
public class AlchemistShop {

  private final List<Potion> topShelf;
  private final List<Potion> bottomShelf;

  /**
   * Constructor.
   */
  public AlchemistShop() {
    var factory = new PotionFactory();
    topShelf = Arrays.asList(
            factory.createPotion(PotionType.INVISIBILITY),
            factory.createPotion(PotionType.INVISIBILITY),
            factory.createPotion(PotionType.STRENGTH),
            factory.createPotion(PotionType.HEALING),
            factory.createPotion(PotionType.INVISIBILITY),
            factory.createPotion(PotionType.STRENGTH),
            factory.createPotion(PotionType.HEALING),
            factory.createPotion(PotionType.HEALING)
    );
    bottomShelf = Arrays.asList(
        factory.createPotion(PotionType.POISON),
        factory.createPotion(PotionType.POISON),
        factory.createPotion(PotionType.POISON),
        factory.createPotion(PotionType.HOLY_WATER),
        factory.createPotion(PotionType.HOLY_WATER)
    );
  }

  /**
   * Get a read-only list of all the items on the top shelf.
   *
   * @return The top shelf potions
   */
  public final List<Potion> getTopShelf() {
    return Collections.unmodifiableList(this.topShelf);
  }

  /**
   * Get a read-only list of all the items on the bottom shelf.
   *
   * @return The bottom shelf potions
   */
  public final List<Potion> getBottomShelf() {
    return Collections.unmodifiableList(this.bottomShelf);
  }

  /**
   * Drink all the potions.
   */
  public void drinkPotions() {
    LOGGER.info("Drinking top shelf potions");
    topShelf.forEach(Potion::drink);
    LOGGER.info("Drinking bottom shelf potions");
    bottomShelf.forEach(Potion::drink);
  }
}
