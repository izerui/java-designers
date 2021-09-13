package Proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 象牙塔实现了塔接口
 */
@Slf4j
public class IvoryTower implements WizardTower {

  // 进入一个巫师(实际上进入的是一个代理)
  public void enter(Wizard wizard) {
    LOGGER.info("{} 进入象牙塔.", wizard);
  }

}
