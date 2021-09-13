package Singleton;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * 确保一个类只有一个实例，并提供对它的全局访问点。
 *
 * <p>例子: 这里有一座象牙塔可供巫师学习魔法。所有的巫师都使用同一座象牙塔来附魔。这里的象牙塔就是单例。
 */
@Slf4j
public class App {


  public static void main(String[] args) {

    // 静态成员属性
    IvoryTower ivoryTower1 = IvoryTower.getInstance();
    IvoryTower ivoryTower2 = IvoryTower.getInstance();
    LOGGER.info("ivoryTower1={}", ivoryTower1);
    LOGGER.info("ivoryTower2={}", ivoryTower2);

    // DCL 又称双重检测锁机制: 延迟加载静态成员变量
    var threadSafeIvoryTower1 = ThreadSafeLazyLoadedIvoryTower.getInstance();
    var threadSafeIvoryTower2 = ThreadSafeLazyLoadedIvoryTower.getInstance();
    LOGGER.info("threadSafeIvoryTower1={}", threadSafeIvoryTower1);
    LOGGER.info("threadSafeIvoryTower2={}", threadSafeIvoryTower2);

    // 枚举 推荐使用
    var enumIvoryTower1 = EnumIvoryTower.INSTANCE;
    var enumIvoryTower2 = EnumIvoryTower.INSTANCE;
    LOGGER.info("enumIvoryTower1={}", enumIvoryTower1);
    LOGGER.info("enumIvoryTower2={}", enumIvoryTower2);

    // DCL 又称双重检测锁机制, 同时使用局部变量提升 25% 的性能
    var dcl1 = ThreadSafeDoubleCheckLocking.getInstance();
    LOGGER.info(dcl1.toString());
    var dcl2 = ThreadSafeDoubleCheckLocking.getInstance();
    LOGGER.info(dcl2.toString());

    // 静态内部类,支持懒加载，弥补了静态成员变量方式的缺点
    var demandHolderIdiom = InitializingOnDemandHolderIdiom.getInstance();
    LOGGER.info(demandHolderIdiom.toString());
    var demandHolderIdiom2 = InitializingOnDemandHolderIdiom.getInstance();
    LOGGER.info(demandHolderIdiom2.toString());
  }
}
