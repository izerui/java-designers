package Decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 赤脚双拳的巨魔
 */
@Slf4j
public class SimpleTroll implements Troll {

  @Override
  public void attack() {
    LOGGER.info("巨魔视图抓住你");
  }

  @Override
  public int getAttackPower() {
    return 10;
  }

  @Override
  public void fleeBattle() {
    LOGGER.info("巨魔尖叫的跑了.");
  }
}
