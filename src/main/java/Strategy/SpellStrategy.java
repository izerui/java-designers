package Strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 咒语策略
 */
@Slf4j
public class SpellStrategy implements DragonSlayingStrategy {

  @Override
  public void execute() {
    LOGGER.info("你施展了瓦解咒语，巨龙在一堆尘土中蒸发！");
  }

}
