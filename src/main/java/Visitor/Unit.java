package Visitor;

import java.util.Arrays;

/**
 * 层次结构基类
 */
public abstract class Unit {

  private final Unit[] children;

  /**
   * 构造多个子单元
   */
  public Unit(Unit... children) {
    this.children = children;
  }

  /**
   * 自身的下级接受访问
   */
  public void accept(UnitVisitor visitor) {
    Arrays.stream(children).forEach(child -> child.accept(visitor));
  }
}
