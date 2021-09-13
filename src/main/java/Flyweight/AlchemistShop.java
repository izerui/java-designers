package Flyweight;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 药剂商店的货架上放着药剂。使用 PotionFactory 来提供所有药水。
 */
@Slf4j
public class AlchemistShop {

  private final List<Potion> topShelf;
  private final List<Potion> bottomShelf;

  /**
   * 炼金药水商店.
   */
  public AlchemistShop() {
    PotionFactory factory = new PotionFactory();
    // 上层货架
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
    // 下层货架
    bottomShelf = Arrays.asList(
        factory.createPotion(PotionType.POISON),
        factory.createPotion(PotionType.POISON),
        factory.createPotion(PotionType.POISON),
        factory.createPotion(PotionType.HOLY_WATER),
        factory.createPotion(PotionType.HOLY_WATER)
    );
  }

  /**
   * 获取上层货架上所有药剂的只读目录
   */
  public final List<Potion> getTopShelf() {
    return Collections.unmodifiableList(this.topShelf);
  }

  /**
   * 获取下层货架上所有药剂的只读目录。
   */
  public final List<Potion> getBottomShelf() {
    return Collections.unmodifiableList(this.bottomShelf);
  }

  /**
   * 喝下所有药剂
   */
  public void drinkPotions() {
    LOGGER.info("喝下顶层货架上的所有药剂");
    topShelf.forEach(Potion::drink);
    LOGGER.info("喝下下层货架上的所有药剂");
    bottomShelf.forEach(Potion::drink);
  }
}
