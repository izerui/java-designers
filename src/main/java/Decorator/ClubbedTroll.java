package Decorator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 武装装饰器
 */
@Slf4j
@RequiredArgsConstructor
public class ClubbedTroll implements Troll {

  private final Troll decorated; // 巨魔

  // 攻击
  @Override
  public void attack() {
    decorated.attack();
    LOGGER.info("巨魔拿棍棒攻击你.");
  }

  @Override
  public int getAttackPower() {
    return decorated.getAttackPower() + 10;
  }

  @Override
  public void fleeBattle() {
    decorated.fleeBattle();
  }
}
