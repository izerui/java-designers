package Facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 挖矿矮人
 */
@Slf4j
public class DwarvenGoldDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} 挖掘金子.", name());
  }

  @Override
  public String name() {
    return "挖矿矮人";
  }
}
