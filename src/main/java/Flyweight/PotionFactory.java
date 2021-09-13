package Flyweight;

import java.util.EnumMap;
import java.util.Map;

/**
 * PotionFactory 是本例中的享元类。它通过共享对象实例来最小化内存使用。
 * 它保存着药剂的目录类型，并且只有在它不存在的药剂类型下,才会创建新的药剂。
 */
public class PotionFactory {

  // 享元对象
  private final Map<PotionType, Potion> potions;

  public PotionFactory() {
    potions = new EnumMap<>(PotionType.class);
  }

  Potion createPotion(PotionType type) {
    Potion potion = potions.get(type);
    if (potion == null) {
      switch (type) {
        case HEALING:
          potion = new HealingPotion();
          potions.put(type, potion);
          break;
        case HOLY_WATER:
          potion = new HolyWaterPotion();
          potions.put(type, potion);
          break;
        case INVISIBILITY:
          potion = new InvisibilityPotion();
          potions.put(type, potion);
          break;
        case POISON:
          potion = new PoisonPotion();
          potions.put(type, potion);
          break;
        case STRENGTH:
          potion = new StrengthPotion();
          potions.put(type, potion);
          break;
        default:
          break;
      }
    }
    return potion;
  }
}
