package Visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * 中士访问者.
 */
@Slf4j
public class SergeantVisitor implements UnitVisitor {

  @Override
  public void visitSoldier(Soldier soldier) {
    // Do nothing
  }

  @Override
  public void visitSergeant(Sergeant sergeant) {
    LOGGER.info("嗨 {}", sergeant);
  }

  @Override
  public void visitCommander(Commander commander) {
    // Do nothing
  }
}
