package Facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 运输矮人
 */
@Slf4j
public class DwarvenCartOperator extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} 将金子运出矿山.", name());
  }

  @Override
  public String name() {
    return "运输矮人";
  }
}
