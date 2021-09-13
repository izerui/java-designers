package Bridge;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 锤子.
 */
@Slf4j
@AllArgsConstructor
public class Hammer implements Weapon {

  private final Enchantment enchantment;

  @Override
  public void wield() {
    LOGGER.info("挥舞着锤子.");
    enchantment.onActivate();
  }

  @Override
  public void swing() {
    LOGGER.info("锤子摆动起来.");
    enchantment.apply();
  }

  @Override
  public void unwield() {
    LOGGER.info("锤子安静下来.");
    enchantment.onDeactivate();
  }

  @Override
  public Enchantment getEnchantment() {
    return enchantment;
  }
}
