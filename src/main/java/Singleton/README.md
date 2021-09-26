# 单例 Singleton

## 目的

确保一个类只有一个实例，并提供对它的全局访问点。

## 解释

现实世界例子

> 这里有一座象牙塔可供巫师学习魔法。所有的巫师都使用同一座象牙塔来附魔。这里的象牙塔就是单例。

简单来说

> 确保给特定类只创建一个对象

维基百科说

> 在软件工程中，单例模式是一种软件设计模式，它将类的实例化限制为只有一个对象。这样当恰好需要一个对象来协调整个系统的操作时，这很有用。

**编程示例**

Joshua Bloch, Effective Java 2nd Edition p.18

> 单元素枚举类型是实现单例的最佳方式

```java
public enum EnumIvoryTower {
  INSTANCE
}
```

现在这样使用:

```java
// 枚举 推荐使用
var enumIvoryTower1 = EnumIvoryTower.INSTANCE;
var enumIvoryTower2 = EnumIvoryTower.INSTANCE;
LOGGER.info("enumIvoryTower1={}", enumIvoryTower1);
LOGGER.info("enumIvoryTower2={}", enumIvoryTower2);
```

控制台输出

```
enumIvoryTower1=com.iluwatar.singleton.EnumIvoryTower@1221555852
enumIvoryTower2=com.iluwatar.singleton.EnumIvoryTower@1221555852
```

## 类图

![alt text](/src/main/resources/puml/uml/singleton.urm.png "Singleton pattern class diagram")

## 时序图

![alt text](/src/main/resources/puml/puml/Singleton.png)

## 实现方式

* 在类中添加一个私有静态成员变量用于保存单例实例。
* 声明一个公有静态构建方法用于获取单例实例。
* 在静态方法中实现"延迟初始化"。 该方法会在首次被调用时创建一个新对象， 并将其存储在静态成员变量中。 此后该方法每次被调用时都返回该实例。
* 将类的构造函数设为私有。 类的静态方法仍能调用构造函数， 但是其他对象不能调用。
* 检查客户端代码， 将对单例的构造函数的调用替换为对其静态构建方法的调用。

## 适用场景

* 如果程序中的某个类对于所有客户端只有一个可用的实例， 可以使用单例模式。
* 如果你需要更加严格地控制全局变量， 可以使用单例模式。

**优点**

* 你可以保证一个类只有一个实例。
* 你获得了一个指向该实例的全局访问节点。
* 仅在首次请求单例对象时对其进行初始化。

## 与其他模式的关系

* [外观模式](Facade)类通常可以转换为单例模式类， 因为在大部分情况下一个外观对象就足够了。
* 如果你能将对象的所有共享状态简化为一个享元对象， 那么[享元模式](Flyweight)就和单例类似了。 但这两个模式有两个根本性的不同。
* [抽象工厂模式](Abstract)、 [建造者模式](Builder)和[原型模式](Prototype)都可以用单例来实现。


单例模式的一些典型用例

* 日志类
* 管理与数据库的连接
* 文件管理器

## 已知用途

* [java.lang.Runtime#getRuntime()](http://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#getRuntime%28%29)
* [java.awt.Desktop#getDesktop()](http://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html#getDesktop--)
* [java.lang.System#getSecurityManager()](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getSecurityManager--)


## 单例带来的结果

* 通过控制它们的创建和生命周期来违反单一职责原则 (SRP)。
* 鼓励使用全局共享实例，以防止释放该对象使用的对象和资源。     
* 创建紧密耦合的代码。 Singleton 的客户端变得难以测试。
* 几乎不可能对单例进行子类化。

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Effective Java](https://www.amazon.com/gp/product/0134685997/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0134685997&linkCode=as2&tag=javadesignpat-20&linkId=4e349f4b3ff8c50123f8147c828e53eb)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)
