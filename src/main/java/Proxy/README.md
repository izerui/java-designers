# 代理 Proxy

## 又被称为

替代（代孕）模式

## 目的

为另一个对象提供代理或占位符以控制对其的访问。

## 解释

真实世界例子

> 想象有一个塔，当地的巫师去那里学习他们的法术。象牙塔只能够通过代理来进入以此来保证只有首先3个巫师才能进入。这里的代理就代表的塔的功能并添加访问控制。

通俗的说

> 使用代理模式，一个类代表另一个类的功能。

维基百科说

> 在最一般的形式上，代理是一个类，它充当与其他对象的接口。代理是客户端调用的包装器或代理对象，以访问后台的实际服务对象。代理本身可以简单地转发到真实对象，也可以提供其他逻辑。在代理中，可以提供额外的功能，例如在对实对象的操作占用大量资源时进行缓存，或者在对实对象的操作被调用之前检查前提条件。

**程序示例**

使用上面的巫师塔为例。首先我们有**塔**接口和**象牙塔**服务类 。

```java
// 塔接口
public interface WizardTower {
    // 进入一个巫师
  void enter(Wizard wizard);
}

// 象牙塔实现了塔接口
public class IvoryTower implements WizardTower {

  // 进入一个巫师(实际上进入的是一个代理)
  public void enter(Wizard wizard) {
    LOGGER.info("{} 进入象牙塔.", wizard);
  }

}
```

然后有个简单的巫师类。

```java
// 巫师
public class Wizard {

  private final String name;

  public Wizard(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
```

然后我们有巫师塔代理类为巫师塔添加访问控制。

```java
// 代理类, 代理了象牙塔用来做数量控制
public class WizardTowerProxy implements WizardTower {

  private static final int NUM_WIZARDS_ALLOWED = 3;

  private int numWizards;

  private final WizardTower tower;

  // 代理了任何塔,不仅仅是象牙塔
  public WizardTowerProxy(WizardTower tower) {
    this.tower = tower;
  }

  // 进入一个巫师,这里做了数量控制
  @Override
  public void enter(Wizard wizard) {
    if (numWizards < NUM_WIZARDS_ALLOWED) {
      tower.enter(wizard);
      numWizards++;
    } else {
      LOGGER.info("{} 不允许进入!", wizard);
    }
  }
}
```

然后这是进入塔的场景。

```java
var proxy = new WizardTowerProxy(new IvoryTower());
proxy.enter(new Wizard("Red wizard"));
proxy.enter(new Wizard("White wizard"));
proxy.enter(new Wizard("Black wizard"));
proxy.enter(new Wizard("Green wizard"));
proxy.enter(new Wizard("Brown wizard"));
```

程序输出：

```
Red wizard 进入象牙塔.
White wizard 进入象牙塔.
Black wizard 进入象牙塔.
Green wizard 不允许进入!
Brown wizard 不允许进入!
```

## 类图

![alt text](/src/main/resources/puml/uml/proxy.urm.png "Proxy pattern class diagram")

## 时序图

![alt text](/src/main/resources/puml/puml/Proxy.png)

## 实现方式

* 如果没有现成的服务接口， 你就需要创建一个接口来实现代理和服务对象的可交换性。 从服务类中抽取接口并非总是可行的， 因为你需要对服务的所有客户端进行修改， 让它们使用接口。 备选计划是将代理作为服务类的子类， 这样代理就能继承服务的所有接口了。
* 创建代理类， 其中必须包含一个存储指向服务的引用的成员变量。 通常情况下， 代理负责创建服务并对其整个生命周期进行管理。 在一些特殊情况下， 客户端会通过构造函数将服务传递给代理。
* 根据需求实现代理方法。 在大部分情况下， 代理在完成一些任务后应将工作委派给服务对象。
* 可以考虑新建一个构建方法来判断客户端可获取的是代理还是实际服务。 你可以在代理类中创建一个简单的静态方法， 也可以创建一个完整的工厂方法。
* 可以考虑为服务对象实现延迟初始化。



## 适用场景

* 延迟初始化 （虚拟代理）。 如果你有一个偶尔使用的重量级服务对象， 一直保持该对象运行会消耗系统资源时， 可使用代理模式。
  * 你无需在程序启动时就创建该对象， 可将对象的初始化延迟到真正有需要的时候。
* 访问控制 （保护代理）。 如果你只希望特定客户端使用服务对象， 这里的对象可以是操作系统中非常重要的部分， 而客户端则是各种已启动的程序 （包括恶意程序）， 此时可使用代理模式。
  * 代理可仅在客户端凭据满足要求时将请求传递给服务对象。
* 本地执行远程服务 （远程代理）。 适用于服务对象位于远程服务器上的情形。
  * 在这种情形中， 代理通过网络传递客户端请求， 负责处理所有与网络相关的复杂细节。
* 记录日志请求 （日志记录代理）。 适用于当你需要保存对于服务对象的请求历史记录时。 代理可以在向服务传递请求前进行记录。
  * 缓存请求结果 （缓存代理）。 适用于需要缓存客户请求结果并对缓存生命周期进行管理时， 特别是当返回结果的体积非常大时。
  > 代理可对重复请求所需的相同结果进行缓存， 还可使用请求参数作为索引缓存的键值。
* 智能引用。 可在没有客户端使用某个重量级对象时立即销毁该对象。 
  * 代理会将所有获取了指向服务对象或其结果的客户端记录在案。 代理会时不时地遍历各个客户端， 检查它们是否仍在运行。 如果相应的客户端列表为空， 代理就会销毁该服务对象， 释放底层系统资源。
  > 代理还可以记录客户端是否修改了服务对象。 其他客户端还可以复用未修改的对象。

**优点**

* 你可以在客户端毫无察觉的情况下控制服务对象。
* 如果客户端对服务对象的生命周期没有特殊要求， 你可以对生命周期进行管理。
* 即使服务对象还未准备好或不存在， 代理也可以正常工作。
* 开闭原则。 你可以在不对服务或客户端做出修改的情况下创建新代理。

**缺点**

* 代码可能会变得复杂， 因为需要新建许多类。
* 服务响应可能会延迟。

## 与其他模式的关系

* [适配器模式](Adapter)能为被封装对象提供**不同的接口**， [代理模式](Proxy)能为对象提供**相同的接口**， [装饰模式](Decorator)则能为对象提供**加强的接口**。
* [外观模式](Facade)与[代理](Proxy)的相似之处在于它们都缓存了一个复杂实体并自行对其进行初始化。 代理与其服务对象遵循同一接口， 使得自己和服务对象可以互换， 在这一点上它与外观不同。
* [装饰](Decorator)和[代理](Proxy)有着相似的结构， 但是其意图却非常不同。 这两个模式的构建都基于组合原则， 也就是说一个对象应该将部分工作委派给另一个对象。 两者之间的不同之处在于代理通常自行管理其服务对象的生命周期， 而装饰的生成则总是由客户端进行控制。

## 典型用例

* 对象的访问控制
* 懒加载
* 实现日志记录
* 简化网络连接
* 对象的访问计数

## 教程

* [Controlling Access With Proxy Pattern](http://java-design-patterns.com/blog/controlling-access-with-proxy-pattern/)

## 已知使用

* org.springframework.aop.framework.AopProxy
* [java.lang.reflect.Proxy](http://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Proxy.html)
* [Apache Commons Proxy](https://commons.apache.org/proper/commons-proxy/)
* Mocking frameworks [Mockito](https://site.mockito.org/), 
[Powermock](https://powermock.github.io/), [EasyMock](https://easymock.org/)

## 相关设计模式

* [Ambassador](https://java-design-patterns.com/patterns/ambassador/)

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
