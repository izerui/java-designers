package Visitor;

/**
 * 访问接口
 */
public interface UnitVisitor {

  // 访问士兵
  void visitSoldier(Soldier soldier);

  // 访问中士
  void visitSergeant(Sergeant sergeant);

  // 访问指挥官
  void visitCommander(Commander commander);

}
