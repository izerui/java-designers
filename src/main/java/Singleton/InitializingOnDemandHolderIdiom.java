package Singleton;

/**
 * 类加载机制：静态变量仅在被用到时才进行初始化。
 * 比如这里 Singleton 被加载并不会导致 LazyHolder.INSTANCE 被加载，只有在调用 Singleton::getInstance
 * 方法时才会去加载 LazyHolder.INSTANCE 变量，以此实现了懒加载
 */
public final class InitializingOnDemandHolderIdiom {

  /**
   * Private constructor.
   */
  private InitializingOnDemandHolderIdiom() {
  }

  /**
   * 单例
   */
  public static InitializingOnDemandHolderIdiom getInstance() {
    return HelperHolder.INSTANCE;
  }

  /**
   * 提供延迟加载的单例, static 类型线程安全
   */
  private static class HelperHolder {
    private static final InitializingOnDemandHolderIdiom INSTANCE =
        new InitializingOnDemandHolderIdiom();
  }
}
