package Flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * HolyWaterPotion.
 */
@Slf4j
public class HolyWaterPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("You feel blessed. (Potion={})", System.identityHashCode(this));
  }
}
