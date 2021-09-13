package Singleton;

/**
 * 方式一: 静态成员属性,static 类型 线程安全
 */
public final class IvoryTower {

  /**
   * 私有构造方法,不允许任何人实例化
   */
  private IvoryTower() {
  }

  /**
   * 类静态实例
   */
  private static final IvoryTower INSTANCE = new IvoryTower();

  /**
   * 获取静态实例的方法
   *
   * @return 返回一个单例
   */
  public static IvoryTower getInstance() {
    return INSTANCE;
  }
}
