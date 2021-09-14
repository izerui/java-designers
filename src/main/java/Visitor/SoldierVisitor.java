package Visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * 士兵访问者.
 */
@Slf4j
public class SoldierVisitor implements UnitVisitor {

  @Override
  public void visitSoldier(Soldier soldier) {
    LOGGER.info("你好 {}", soldier);
  }

  @Override
  public void visitSergeant(Sergeant sergeant) {
    // Do nothing
  }

  @Override
  public void visitCommander(Commander commander) {
    // Do nothing
  }
}
