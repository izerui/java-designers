package Bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 飞行附魔.
 */
@Slf4j
public class FlyingEnchantment implements Enchantment {

  @Override
  public void onActivate() {
    LOGGER.info("该物品开始发光.");
  }

  @Override
  public void apply() {
    LOGGER.info("飞行并击中中敌人后返回.");
  }

  @Override
  public void onDeactivate() {
    LOGGER.info("光芒逐渐消失.");
  }
}
