package Singleton;

import lombok.var;

/**
 * 双重检测锁机制
 */
public final class ThreadSafeDoubleCheckLocking {

  private static volatile ThreadSafeDoubleCheckLocking instance;

  private static boolean flag = true;

  /**
   * 私有构造方法,防止实例化
   */
  private ThreadSafeDoubleCheckLocking() {
    // 防止通过反射机制来实例化该对象
    if (flag) {
      flag = false;
    } else {
      throw new IllegalStateException("Already initialized.");
    }
  }

  /**
   * 获取单例的公共方法
   */
  public static ThreadSafeDoubleCheckLocking getInstance() {
    // 使用局部变量能提升25%的性能 可以参看: Joshua Bloch "Effective Java, Second Edition", p. 283-284
    ThreadSafeDoubleCheckLocking result = instance;

    // 判断是否初始化,如果已经实例化则直接返回
    if (result == null) {
      // 没有实例化? 不确定的,因为可能其他线程可能同时在初始化,所以我们需要一个锁对象来互斥
      synchronized (ThreadSafeDoubleCheckLocking.class) {
        // 再次将实例分配给局部变量以检查它是否被其他线程初始化，
        // 而当前线程被阻止进入锁定区域。如果它被初始化，那么我们可以像之前的空检查一样直接返回之前创建的实例。
        result = instance;
        if (result == null) {
          // 该实例仍未初始化，因此我们可以安全地（没有其他线程可以进入此区域）创建一个实例并将其设为我们的单例实例。
          instance = result = new ThreadSafeDoubleCheckLocking();
        }
      }
    }
    return result;
  }
}
