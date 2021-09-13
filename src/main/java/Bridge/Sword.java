package Bridge;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 剑.
 */
@Slf4j
@AllArgsConstructor
public class Sword implements Weapon {

  private final Enchantment enchantment;

  @Override
  public void wield() {
    LOGGER.info("剑开始挥舞.");
    enchantment.onActivate();
  }

  @Override
  public void swing() {
    LOGGER.info("剑刺了过去.");
    enchantment.apply();
  }

  @Override
  public void unwield() {
    LOGGER.info("剑安静下来.");
    enchantment.onDeactivate();
  }

  @Override
  public Enchantment getEnchantment() {
    return enchantment;
  }
}
