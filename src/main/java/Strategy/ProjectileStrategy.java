package Strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 射击策略
 */
@Slf4j
public class ProjectileStrategy implements DragonSlayingStrategy {

  @Override
  public void execute() {
    LOGGER.info("你用魔法弩射龙，它倒在地上死了！");
  }
}
