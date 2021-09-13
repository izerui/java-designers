package Proxy;

/**
 * 代理，就其最一般的形式而言，是一个充当其他事物接口的类。代理可以连接到任何东西：网络连接、内存中的大对象、文件或其他一些昂贵或无法复制的资源。
 * 简而言之，代理是一个包装器或代理对象，客户端调用它来访问幕后的真实服务对象。
 *
 * <p>代理设计模式允许您通过创建包装类作为代理来为其他对象提供接口。作为代理的包装类可以向感兴趣的对象添加附加功能，而无需更改对象的代码。
 *
 * <p>在此示例中，代理 ({@link WizardTowerProxy}) 控制对实际对象 ({@link IvoryTower}) 的访问。
 */
public class App {

  /**
   * Program entry point.
   */
  public static void main(String[] args) {

    WizardTower proxy = new WizardTowerProxy(new IvoryTower());
    proxy.enter(new Wizard("Red wizard"));
    proxy.enter(new Wizard("White wizard"));
    proxy.enter(new Wizard("Black wizard"));
    proxy.enter(new Wizard("Green wizard"));
    proxy.enter(new Wizard("Brown wizard"));

  }
}
