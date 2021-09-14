package Visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * 指挥官访问者.
 */
@Slf4j
public class CommanderVisitor implements UnitVisitor {

  @Override
  public void visitSoldier(Soldier soldier) {
    // Do nothing
  }

  @Override
  public void visitSergeant(Sergeant sergeant) {
    // Do nothing
  }

  @Override
  public void visitCommander(Commander commander) {
    LOGGER.info("很高兴看见你 {}", commander);
  }
}
