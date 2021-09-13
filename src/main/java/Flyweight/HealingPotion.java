package Flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 恢复药水.
 */
@Slf4j
public class HealingPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("你感觉痊愈了. (Potion={})", System.identityHashCode(this));
  }
}
