package Flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 毒药.
 */
@Slf4j
public class PoisonPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("啊! 这个有毒. (Potion={})", System.identityHashCode(this));
  }
}
