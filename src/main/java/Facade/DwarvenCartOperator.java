package Facade;

import lombok.extern.slf4j.Slf4j;

/**
 * DwarvenCartOperator is one of the goldmine subsystems.
 */
@Slf4j
public class DwarvenCartOperator extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} moves gold chunks out of the mine.", name());
  }

  @Override
  public String name() {
    return "Dwarf cart operator";
  }
}
