package Proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理类, 代理了象牙塔用来做数量控制
 */
@Slf4j
public class WizardTowerProxy implements WizardTower {

  private static final int NUM_WIZARDS_ALLOWED = 3;

  private int numWizards;

  private final WizardTower tower;

  // 代理了任何塔,不仅仅是象牙塔
  public WizardTowerProxy(WizardTower tower) {
    this.tower = tower;
  }

  // 进入一个巫师,这里做了数量控制
  @Override
  public void enter(Wizard wizard) {
    if (numWizards < NUM_WIZARDS_ALLOWED) {
      tower.enter(wizard);
      numWizards++;
    } else {
      LOGGER.info("{} 不允许进入!", wizard);
    }
  }
}
