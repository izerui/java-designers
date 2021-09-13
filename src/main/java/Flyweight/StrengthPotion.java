package Flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * StrengthPotion.
 */
@Slf4j
public class StrengthPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("You feel strong. (Potion={})", System.identityHashCode(this));
  }
}
