package Visitor;

/**
 * <p>访问者模式定义了一种在层次结构中的节点上应用操作的机制。无需更改节点界面即可添加新操作。</p>
 *
 * <p>在这个例子中，有一个从 {@link Commander} 开始的单位层次结构。
 * 访问者依次遍历此层次结构:
 * - {@link SoldierVisitor} 士兵访问者访问 {@link Soldier}士兵
 * - {@link SergeantVisitor} 中士访问者访问 {@link Sergeant} 中士等。
 * - ...
 * </p>
 */
public class App {


  public static void main(String[] args) {

    // 生成指挥官1-中士2-士兵6 结构的树层次结构
    Unit commander = new Commander(
        new Sergeant(new Soldier(), new Soldier(), new Soldier()),
        new Sergeant(new Soldier(), new Soldier(), new Soldier())
    );
    // 遍历树访问所有士兵
    commander.accept(new SoldierVisitor());
    // 遍历树访问所有中士
    commander.accept(new SergeantVisitor());
    // 遍历树访问所有指挥官
    commander.accept(new CommanderVisitor());

  }
}
