package Strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 近战策略
 */
@Slf4j
public class MeleeStrategy implements DragonSlayingStrategy {

  @Override
  public void execute() {
    LOGGER.info("用你的神剑斩断龙头！");
  }
}
