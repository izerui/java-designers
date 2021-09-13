package Facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 开挖掘机矮人
 */
@Slf4j
public class DwarvenTunnelDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} 开通了另一条隧道.", name());
  }

  @Override
  public String name() {
    return "开挖掘机矮人";
  }
}
