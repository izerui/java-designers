package Bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 嗜血附魔.
 */
@Slf4j
public class SoulEatingEnchantment implements Enchantment {

  @Override
  public void onActivate() {
    LOGGER.info("该物品开始传播嗜血效果.");
  }

  @Override
  public void apply() {
    LOGGER.info("该物品开始吞噬灵魂.");
  }

  @Override
  public void onDeactivate() {
    LOGGER.info("嗜血效果慢慢消失.");
  }
}
