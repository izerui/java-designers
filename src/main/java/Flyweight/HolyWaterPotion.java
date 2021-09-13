package Flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 神圣药水.
 */
@Slf4j
public class HolyWaterPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("你感觉到很幸福. (Potion={})", System.identityHashCode(this));
  }
}
