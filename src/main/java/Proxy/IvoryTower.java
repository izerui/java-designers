package Proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * The object to be proxied.
 */
@Slf4j
public class IvoryTower implements WizardTower {

  public void enter(Wizard wizard) {
    LOGGER.info("{} enters the tower.", wizard);
  }

}
