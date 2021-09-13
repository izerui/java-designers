package Flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 隐形药水.
 */
@Slf4j
public class InvisibilityPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("你隐形了. (Potion={})", System.identityHashCode(this));
  }
}
