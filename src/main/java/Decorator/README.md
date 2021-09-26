# 装饰 Decorator

## 或称
包装器

## 目的
动态的为对象附加额外的职责。装饰器为子类提供了灵活的替代方案，以扩展功能。

## 解释

真实世界例子

> 附近的山丘上住着一个愤怒的巨魔。通常它是徒手的，但有时它有武器。为了武装巨魔不必创建新的巨魔，而是用合适的武器动态的装饰它。

通俗的说

> 装饰者模式让你可以在运行时通过把对象包装进一个装饰类对象中来动态的改变一个对象的行为。

维基百科说

> 在面向对象的编程中，装饰器模式是一种设计模式，它允许将行为静态或动态地添加到单个对象中，而不会影响同一类中其他对象的行为。装饰器模式通常对于遵守单一责任原则很有用，因为它允许将功能划分到具有唯一关注领域的类之间。

**程序示例**

以巨魔的为例。首先我有有一个简单的巨魔，实现了巨魔接口。

```java
/**
 * 巨魔基类接口
 */
public interface Troll {

    // 攻击
    void attack();

    // 获得攻击力
    int getAttackPower();

    // 脱离战斗
    void fleeBattle();

}

/**
 * 赤脚双拳的巨魔
 */
@Slf4j
public class SimpleTroll implements Troll {

    @Override
    public void attack() {
        LOGGER.info("巨魔视图抓住你");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        LOGGER.info("巨魔尖叫的跑了.");
    }
}

```

下面我们来武装巨魔。我们可以用装饰者来动态的实现。

```java
/**
 * 武装装饰器
 */
@Slf4j
@RequiredArgsConstructor
public class ClubbedTroll implements Troll {

    private final Troll decorated; // 巨魔

    // 攻击
    @Override
    public void attack() {
        decorated.attack();
        LOGGER.info("巨魔拿棍棒攻击你.");
    }

    @Override
    public int getAttackPower() {
        return decorated.getAttackPower() + 10;
    }

    @Override
    public void fleeBattle() {
        decorated.fleeBattle();
    }
}
```

这里是巨魔的实战

```java
// 徒手巨魔
LOGGER.info("一个赤脚双全的巨魔靠近了.");
var troll = new SimpleTroll();
troll.attack();
troll.fleeBattle();
LOGGER.info("徒手巨魔的力量: {}.\n", troll.getAttackPower());

// 通过使用装饰器来武装巨魔,改变巨魔的行为
LOGGER.info("一个武装巨魔靠近了.");
var clubbedTroll = new ClubbedTroll(troll);
clubbedTroll.attack();
clubbedTroll.fleeBattle();
LOGGER.info("武装巨魔的力量: {}.\n", clubbedTroll.getAttackPower());
```

控制台输出:

```shell
 一个赤脚双全的巨魔靠近了.
 巨魔视图抓住你
 巨魔尖叫的跑了.
 徒手巨魔的力量: 10.
 
 一个武装巨魔靠近了.
 巨魔视图抓住你
 巨魔拿棍棒攻击你.
 巨魔尖叫的跑了.
 武装巨魔的力量: 20.
```

## 类图

![alt text](../../resources/puml/uml/decorator.urm.png "Decorator pattern class diagram")

## 时序图

![alt text](../../resources/puml/puml/Decorator.png)

## 实现方式

* 确保业务逻辑可用一个基本组件及多个额外可选层次表示。
* 找出基本组件和可选层次的通用方法。 创建一个组件接口并在其中声明这些方法。
* 创建一个具体组件类， 并定义其基础行为。
* 创建装饰基类， 使用一个成员变量存储指向被封装对象的引用。 该成员变量必须被声明为组件接口类型， 从而能在运行时连接具体组件和装饰。 装饰基类必须将所有工作委派给被封装的对象。
* 确保所有类实现组件接口。
* 将装饰基类扩展为具体装饰。 具体装饰必须在调用父类方法 （总是委派给被封装对象） 之前或之后执行自身的行为。
* 客户端代码负责创建装饰并将其组合成客户端所需的形式。

## 适用场景

* 如果你希望在无需修改代码的情况下即可使用对象， 且希望在运行时为对象新增额外的行为， 可以使用装饰模式。
* 如果用继承来扩展对象行为的方案难以实现或者根本不可行， 你可以使用该模式。

**优点**

* 你无需创建新子类即可扩展对象的行为。
* 你可以在运行时添加或删除对象的功能。
* 你可以用多个装饰封装对象来组合几种行为。
* 单一职责原则。 你可以将实现了许多不同行为的一个大类拆分为多个较小的类。

**缺点**

* 在封装器栈中删除特定封装器比较困难。
* 实现行为不受装饰栈顺序影响的装饰比较困难。
* 各层的初始化配置代码看上去可能会很糟糕。

## 与其他模式的关系

* [适配器模式](Adapter)可以对已有对象的接口进行修改， [装饰模式](Decorator)则能在不改变对象接口的前提下强化对象功能。 此外， [装饰](Decorator)还支持递归组合， [适配器](Adapter)则无法实现。
* **[适配器](Adapter)能为被封装对象提供不同的接口， [代理模式](Proxy)能为对象提供相同的接口， [装饰模式](Decorator)则能为对象提供加强的接口。**
* [责任链模式](Chain)和[装饰模式](Decorator)的类结构非常相似。 两者都依赖递归组合将需要执行的操作传递给一系列对象。 但是， 两者有几点重要的不同之处。
  * [责任链](Chain)的管理者可以相互独立地执行一切操作， 还可以随时停止传递请求。 另一方面， 各种装饰可以在遵循基本接口的情况下扩展对象的行为。 此外， [装饰](Decorator)无法中断请求的传递。
* [组合模式](Composite)和[装饰](Decorator)的结构图很相似， 因为两者都依赖递归组合来组织无限数量的对象。
  * 装饰类似于组合， 但其只有一个子组件。 此外还有一个明显不同： 装饰为被封装对象添加了额外的职责， 组合仅对其子节点的结果进行了 “求和”。
  * 但是， 模式也可以相互合作： 你可以使用装饰来扩展组合树中特定对象的行为。
* 大量使用组合和装饰的设计通常可从对于[原型模式](Prototype)的使用中获益。 你可以通过该模式来复制复杂结构， 而非从零开始重新构造。
* [装饰](Decorator)可让你更改对象的外表， [策略模式](Strategy)则让你能够改变其本质。
* [装饰](Decorator)和[代理](Proxy)有着相似的结构， 但是其意图却非常不同。 这两个模式的构建都基于组合原则， 也就是说**一个对象应该将部分工作委派给另一个对象**。 两者之间的不同之处在于代理通常自行管理其服务对象的生命周期， 而装饰的生成则总是由客户端进行控制。

## 教程
* [Decorator Pattern Tutorial](https://www.journaldev.com/1540/decorator-design-pattern-in-java-example)

## Java世界的例子
 * Spring 中的例子:
   * 织入通知到 Spring 应用程序中。它使用装饰者模式的 CGLib 代理，其通过在运行时生成目标类的子类来工作。
   * BeanDefinitionDecorator: 它通过使用自定义属性来增强 bean 的定义。
   * WebSocketHandlerDecorator: 它用来增强一个 WebSocketHandler 附加行为。
 * [java.io.InputStream](http://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html), [java.io.OutputStream](http://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html),
 [java.io.Reader](http://docs.oracle.com/javase/8/docs/api/java/io/Reader.html) and [java.io.Writer](http://docs.oracle.com/javase/8/docs/api/java/io/Writer.html)
 * [java.util.Collections#synchronizedXXX()](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedCollection-java.util.Collection-)
 * [java.util.Collections#unmodifiableXXX()](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#unmodifiableCollection-java.util.Collection-)
 * [java.util.Collections#checkedXXX()](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#checkedCollection-java.util.Collection-java.lang.Class-)


## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Functional Programming in Java: Harnessing the Power of Java 8 Lambda Expressions](https://www.amazon.com/gp/product/1937785467/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=1937785467&linkCode=as2&tag=javadesignpat-20&linkId=7e4e2fb7a141631491534255252fd08b)
* [J2EE Design Patterns](https://www.amazon.com/gp/product/0596004273/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596004273&linkCode=as2&tag=javadesignpat-20&linkId=48d37c67fb3d845b802fa9b619ad8f31)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)
* [J2EE Design Patterns](https://www.amazon.com/gp/product/0596004273/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596004273&linkCode=as2&tag=javadesignpat-20&linkId=f27d2644fbe5026ea448791a8ad09c94)
