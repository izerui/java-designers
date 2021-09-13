package Facade;

import lombok.extern.slf4j.Slf4j;

/**
 * DwarvenGoldDigger is one of the goldmine subsystems.
 */
@Slf4j
public class DwarvenGoldDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} digs for gold.", name());
  }

  @Override
  public String name() {
    return "Dwarf gold digger";
  }
}
