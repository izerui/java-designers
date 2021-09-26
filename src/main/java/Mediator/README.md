# 中介者 Mediator

## 目的

定义一个封装一组对象如何交互的对象。调解器通过防止对象明确地相互引用来促进松散耦合，并且它允许您独立地改变它们的交互。

## 解释

真实例子

> 盗贼、巫师、霍比特人和猎人决定加入他们的部队并在同一个队伍中旅行。
> 为了避免每个成员相互耦合，他们使用聚合接口来相互通信。

简单来说

> 中介者通过强制它们的通信流通过中介对象来解耦一组类。

维基百科说

> 在软件工程中，中介者模式定义了一个对象，该对象封装了一组对象如何交互。这种模式被认为是一种行为模式，因为它可以 
> 改变程序的运行行为。在面向对象的编程中，程序通常由多个类组成。业务逻辑和计算分布在这些类中。然而，随着更多的类被添加到程序中，
> 特别是在维护和/或重构期间，这些类之间的通信问题可能会变得更加复杂。这使得程序更难阅读和维护。
> 此外，更改程序可能会变得困难，因为任何更改都可能影响其他几个类中的代码。使用中介者模式，对象之间的通信被封装在中介者对象中。
> 对象不再直接相互通信，而是通过中介进行通信。这减少了通信对象之间的依赖关系，从而减少了耦合。

**编程示例**

在这个例子中，中介器封装了一组对象如何交互。他们使用中介者接口，而不是直接相互引用。

这些人 `Rogue 盗贼`, `Wizard 巫师`, `Hobbit 霍比特人`, and `Hunter 猎人` 都继承了 `PartyMemberBase`基础抽象类,并且实现了 `PartyMember`接口

```java
// 成员接口
public interface PartyMember {

    // 加入部队
  void joinedParty(Party party);

  // 执行部队下达的命令
  void partyAction(Action action);

  // 下发命令给自己所属的部队
  void act(Action action);
}

@Slf4j
// 成员基础抽象类
public abstract class PartyMemberBase implements PartyMember {

    // 部队
  protected Party party;

  // 加入一个部队
  @Override
  public void joinedParty(Party party) {
    LOGGER.info("{} joins the party", this);
    this.party = party;
  }

  // 执行部队下达的命令
  @Override
  public void partyAction(Action action) {
    LOGGER.info("{} {}", this, action.getDescription());
  }

  // 下发命令给自己所属的部队
  @Override
  public void act(Action action) {
    if (party != null) {
      LOGGER.info("{} {}", this, action);
      party.act(this, action);
    }
  }

  @Override
  public abstract String toString();
}

// 盗贼
public class Rogue extends PartyMemberBase {

  @Override
  public String toString() {
    return "Rogue";
  }
}

// 巫师、霍比特人和猎人的实现方式类似
```

我们的中介系统由`Party 部队`接口及其实现组成。

```java
// 中介者 (部队)
public interface Party {

    // 添加成员
  void addMember(PartyMember member);

  // 把命令传递给其他人
  void act(PartyMember actor, Action action);
}

// 中介者实现 (一个部队)
public class PartyImpl implements Party {

    // 部队成员
  private final List<PartyMember> members;

  public PartyImpl() {
    members = new ArrayList<>();
  }

  // 把命令传递给其他人
  @Override
  public void act(PartyMember actor, Action action) {
    for (var member : members) {
      if (!member.equals(actor)) {
        member.partyAction(action);
      }
    }
  }

  // 添加成员
  @Override
  public void addMember(PartyMember member) {
    members.add(member);
    member.joinedParty(this);
  }
}
```

这是一个演示，显示了正在运行的中介模式。

```java
    // 创建部队和成员
    Party party = new PartyImpl();
    var hobbit = new Hobbit();
    var wizard = new Wizard();
    var rogue = new Rogue();
    var hunter = new Hunter();

    // 部队添加成员
    party.addMember(hobbit);
    party.addMember(wizard);
    party.addMember(rogue);
    party.addMember(hunter);

    // 执行命令 -> 通知其他成员执行命令
    // 由当事人通知
    hobbit.act(Action.ENEMY);
    wizard.act(Action.TALE);
    rogue.act(Action.GOLD);
    hunter.act(Action.HUNT);
```

这是运行示例的控制台输出。

```
霍比特人 加入部队
巫师 加入部队
盗贼 加入部队
猎人 加入部队

霍比特人 发现敌人
巫师 跑去掩护
盗贼 跑去掩护
猎人 跑去掩护

巫师 讲故事
霍比特人 过来听
盗贼 过来听
猎人 过来听

盗贼 发现金子
霍比特人 拿走属于自己的那份金子
巫师 拿走属于自己的那份金子
猎人 拿走属于自己的那份金子

猎人 猎杀了一只兔子
霍比特人 来吃晚饭
巫师 来吃晚饭
盗贼 来吃晚饭
```

## 类图

![alt text](../../resources/puml/uml/mediator_1.png "Mediator")

## 时序图

![alt text](../../resources/puml/puml/Mediator.png)

## 实现方式

* 找到一组当前紧密耦合， 且提供其独立性能带来更大好处的类 （例如更易于维护或更方便复用）。
* 声明中介者接口并描述中介者和各种组件之间所需的交流接口。 在绝大多数情况下， 一个接收组件通知的方法就足够了。 如果你希望在不同情景下复用组件类， 那么该接口将非常重要。 只要组件使用通用接口与其中介者合作， 你就能将该组件与不同实现中的中介者进行连接。
* 实现具体中介者类。 该类可从自行保存其下所有组件的引用中受益。
* 你可以更进一步， 让中介者负责组件对象的创建和销毁。 此后， 中介者可能会与工厂或外观类似。
* 组件必须保存对于中介者对象的引用。 该连接通常在组件的构造函数中建立， 该函数会将中介者对象作为参数传递。
* 修改组件代码， 使其可调用中介者的通知方法， 而非其他组件的方法。 然后将调用其他组件的代码抽取到中介者类中， 并在中介者接收到该组件通知时执行这些代码。

## 适用场景

在以下情况下使用中介者模式

* 当一些对象和其他对象紧密耦合以致难以对其进行修改时， 可使用中介者模式。
* 当组件因过于依赖其他组件而无法在不同应用中复用时， 可使用中介者模式。
* 如果为了能在不同情景下复用一些基本行为， 导致你需要被迫创建大量组件子类时， 可使用中介者模式。

**优点**

* 单一职责原则。 你可以将多个组件间的交流抽取到同一位置， 使其更易于理解和维护。
* 开闭原则。 你无需修改实际组件就能增加新的中介者。
* 你可以减轻应用中多个组件间的耦合情况。
* 你可以更方便地复用各个组件。

**缺点**

* 一段时间后， 中介者可能会演化成为上帝对象。

## 与其他模式的关系

* [责任链模式](Chain)、 [命令模式](Command)、 [中介者模式](Mediator)和[观察者模式](Observer)用于处理请求发送者和接收者之间的不同连接方式：
  * [责任链](Chain)按照顺序将请求动态传递给一系列的潜在接收者， 直至其中一名接收者对请求进行处理。
  * [命令](Command)在发送者和请求者之间建立单向连接。
  * [中介者](Mediator)清除了发送者和请求者之间的直接连接， 强制它们通过一个中介对象进行间接沟通。
  * [观察者](Observer)允许接收者动态地订阅或取消接收请求。
* [外观模式](Facade)和[中介者](Mediator)的职责类似： 它们都尝试在大量紧密耦合的类中组织起合作。
  * 外观为子系统中的所有对象定义了一个简单接口， 但是它不提供任何新功能。 子系统本身不会意识到外观的存在。 子系统中的对象可以直接进行交流。
  * 中介者将系统中组件的沟通行为中心化。 各组件只知道中介者对象， 无法直接相互交流。
* [中介者](Mediator)和[观察者](Observer)之间的区别往往很难记住。 在大部分情况下， 你可以使用其中一种模式， 而有时可以同时使用。 让我们来看看如何做到这一点。
  * [中介者](Mediator)的主要目标是消除一系列系统组件之间的相互依赖。 这些组件将依赖于同一个中介者对象。 [观察者](Observer)的目标是在对象之间建立动态的单向连接， 使得部分对象可作为其他对象的附属发挥作用。
  * 有一种流行的[中介者模式](Mediator)实现方式依赖于[观察者](Observer)。 中介者对象担当发布者的角色， 其他组件则作为订阅者， 可以订阅中介者的事件或取消订阅。 当中介者以这种方式实现时， 它可能看上去与观察者非常相似。
  * 当你感到疑惑时， 记住可以采用其他方式来实现中介者。 例如， 你可永久性地将所有组件链接到同一个中介者对象。 这种实现方式和观察者并不相同， 但这仍是一种中介者模式。
  * 假设有一个程序， 其所有的组件都变成了发布者， 它们之间可以相互建立动态连接。 这样程序中就没有中心化的中介者对象， 而只有一些分布式的观察者。

## 已知用法

* All scheduleXXX() methods of [java.util.Timer](http://docs.oracle.com/javase/8/docs/api/java/util/Timer.html)
* [java.util.concurrent.Executor#execute()](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html#execute-java.lang.Runnable-)
* submit() and invokeXXX() methods of [java.util.concurrent.ExecutorService](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)
* scheduleXXX() methods of [java.util.concurrent.ScheduledExecutorService](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html)
* [java.lang.reflect.Method#invoke()](http://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html#invoke-java.lang.Object-java.lang.Object...-)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
