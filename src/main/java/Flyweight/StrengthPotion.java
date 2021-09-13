package Flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 力量药水.
 */
@Slf4j
public class StrengthPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("你感觉强壮了. (Potion={})", System.identityHashCode(this));
  }
}
