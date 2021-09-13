package Facade;

import lombok.extern.slf4j.Slf4j;

/**
 * DwarvenTunnelDigger is one of the goldmine subsystems.
 */
@Slf4j
public class DwarvenTunnelDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} creates another promising tunnel.", name());
  }

  @Override
  public String name() {
    return "Dwarven tunnel digger";
  }
}
