package Singleton;

/**
 * <p>Thread-safe Singleton class. The instance is lazily initialized and thus needs synchronization
 * mechanism.</p>
 *
 */
public final class ThreadSafeLazyLoadedIvoryTower {

  /**
   * volatile关键字的两层语义:
   * 　1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
   * 　2）禁止进行指令重排序。
   *
   * volatile 防止指令重排序。指令重排序是 cpu 在执行汇编指令时为了优化性能，可能会对指令做一个重新排序。比如 new Singleton() 这个代码，有三条指令：
   *
   * 分配对象的内存空间
   * 初始化对象
   * 将 instance 指向刚分配的内存地址
   *
   * 正常情况这三条指令顺序执行，没有问题。但可能经过 JVM 和 CPU 的优化，顺序会变成下面的样子：
   *
   * 分配对象的内存空间
   * 将 instance 指向刚分配的内存地址
   * 初始化对象
   *
   * 在这种情况下，就会出现问题。比如线程1执行到上面的第二步将 instance 指向刚分配的内存地址，此时线程2在进行 if 判断，发现 instance 不为 null 了，就直接返回 instance，但实际上此时 instance 还是一个未初始化的对象，这是线程2在使用该对象时就会出问题。
   */
  private static volatile ThreadSafeLazyLoadedIvoryTower instance;

  private ThreadSafeLazyLoadedIvoryTower() {
    // 防止通过反射机制来实例化该对象
    if (instance == null) {
      instance = this;
    } else {
      throw new IllegalStateException("Already initialized.");
    }
  }

  /**
   * 在第一次调用该方法之前,不会创建实例.通过加入 synchronized 机制,保证线程安全
   */
  public static synchronized ThreadSafeLazyLoadedIvoryTower getInstance() {
    // synchronized 外部的 if 其实可以去除，加在这里主要是为了优化性能。如果不在外部做一个 if 判断，那么每个线程 getInstance 时，不管实例是否已经创建，都要等待锁，性能太差。
    if (instance == null) {
      // 使用 synchronized 锁住 new Singleton() 代码，防止多个线程创建出多个实例
      synchronized (ThreadSafeLazyLoadedIvoryTower.class) {
        // if 判断需要在 synchronized 内部。如果在外部，则多线程环境下无法保证只创建一个实例
        if (instance == null) {
          instance = new ThreadSafeLazyLoadedIvoryTower();
        }
      }
    }
    return instance;
  }
}
