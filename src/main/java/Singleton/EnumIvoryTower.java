package Singleton;

/**
 * 基于枚举的单例实现,线程是安全的
 */
public enum EnumIvoryTower {

  INSTANCE;

  @Override
  public String toString() {
    return getDeclaringClass().getCanonicalName() + "@" + hashCode();
  }
}
