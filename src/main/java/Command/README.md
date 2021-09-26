# 命令 Command

## 或称
行动, 事务模式

## 目的
将请求封装为对象，从而使你可以将具有不同请求的客户端参数化，队列或记录请求，并且支持可撤销操作。

## 解释
真实世界例子

> 我们日常开发工作中，项目经理提出具体的需求任务，他们把需求全部交到开发主管手上，他们不关心需求如何实现，只要能保证按时完成即可。
> 开发主管根据需求的紧急程度排定优先级，然后由具体开发组进行任务的开发。

用通俗的话说

> 用命令对象的方式存储请求以在将来时可以执行它或撤销它。

维基百科说

> 在面向对象编程中，命令模式是一种行为型设计模式，它把在稍后执行的一个动作或触发的一个事件所需要的所有信息封装到一个对象中。

**编程示例**

命令类层次:

```java
/**
 * 命令基类
 */
public interface Command {
   //执行命令
   void execute();
   //撤销命令
   void undo();
   // 命令内容
   String getName();
}

/**
 * 开发一个OA的命令,会让命令接收者干重复的活
 */
public class OACommand implements Command {

  private DeveloperReceiver receiver;

  public OACommand(DeveloperReceiver receiver) {
    this.receiver = receiver;
  }

  @Override
  public void execute() {
    receiver.handleCode();
  }

  @Override
  public void undo() {
    receiver.cancelCode();
  }

  @Override
  public String getName() {
    return "开发一个OA";
  }
}

/**
 * 开发一个CRM的命令,会让命令接收者干重复的活
 */
public class CrmCommand implements Command {

  private DeveloperReceiver developerReceiver;

  public CrmCommand(DeveloperReceiver developerReceiver) {
    this.developerReceiver = developerReceiver;
  }

  @Override
  public void execute() {
    developerReceiver.handleCode();
  }


  @Override
  public void undo() {
    developerReceiver.cancelCode();
  }

  @Override
  public String getName() {
    return "开发一个CRM";
  }
}
```

开发主管(调用者,指挥人但是不干实事),可以接收命令,并指派开发人员执行.

```java
/**
 * 开发主管
 */
@Slf4j
public class LeaderInvoker {
  // 命令
  private Command command;

  //实现需求任务
  public void handler(Command command) {
    this.command = command;
    LOGGER.info("新任务: " + command.getName());
    command.execute();
  }

  public void cacelTask() {
    command.undo();
  }
}
```

命令接收者,开发人员:
```java
/**
 * 开发者: 命令接收者
 */
@Slf4j
public class DeveloperReceiver {

    // 人名
    private String name;

    public DeveloperReceiver(String name) {
        this.name = name;
    }

    public void handleCode() {
        LOGGER.info(name + "埋头撸代码");
    }

    public void cancelCode() {
        LOGGER.info(name + "删除代码逻辑");
    }
}
```

现在开始模拟任务下达。

```java
//此处开发主管就是调用者，会传入一个命令对象，可以用来发出请求
LeaderInvoker leaderInvoker = new LeaderInvoker();
// 给阿黄提了一个OA任务
leaderInvoker.handler(new OACommand(new DeveloperReceiver("阿黄")));
// 取消任务
leaderInvoker.cacelTask();
// 给帮帮王提了一个CRM任务
leaderInvoker.handler(new CrmCommand(new DeveloperReceiver("棒棒王")));
// 取消任务
leaderInvoker.cacelTask();
```

控制台输出:
```java
新任务: 开发一个OA
阿黄埋头撸代码
阿黄删除代码逻辑
        
新任务: 开发一个CRM
棒棒王埋头撸代码
棒棒王删除代码逻辑
```

## 类图

![alt text](/src/main/resources/puml/uml/command.png "Command")

## 时序图

![alt text](/src/main/resources/puml/puml/Command.png)

## 实现方式

* 声明仅有一个执行方法的命令接口。
* 抽取请求并使之成为实现命令接口的具体命令类。 每个类都**必须有一组成员变量**来保存请求参数和对于**实际接收者对象**的引用。 所有这些变量的数值都必须**通过命令构造函数**进行初始化。
* 找到担任发送者职责的类。 在这些类中添加保存命令的成员变量。 发送者只能通过命令接口与其命令进行交互。 发送者自身通常并不创建命令对象， 而是通过客户端代码获取。
* 修改发送者使其执行命令， 而非直接将请求发送给接收者。
* 客户端必须按照以下顺序来初始化对象：
  * 创建接收者。
  * 创建命令， 如有需要可将其关联至接收者。
  * 创建发送者并将其与特定命令关联。

## 适用场景

* 如果你需要通过操作来参数化对象， 可使用命令模式。
  * 命令模式可将特定的方法调用转化为独立对象。 这一改变也带来了许多有趣的应用： 你可以将命令作为方法的参数进行传递、 将命令保存在其他对象中， 或者在运行时切换已连接的命令等。
  > 举个例子： 你正在开发一个 GUI 组件 （例如上下文菜单）， 你希望用户能够配置菜单项， 并在点击菜单项时触发操作。
* 如果你想要将操作放入队列中、 操作的执行或者远程执行操作， 可使用命令模式。
  * 同其他对象一样， 命令也可以实现序列化 （序列化的意思是转化为字符串）， 从而能方便地写入文件或数据库中。 一段时间后， 该字符串可被恢复成为最初的命令对象。 因此， 你可以延迟或计划命令的执行。 但其功能远不止如此！ 使用同样的方式， 你还可以将命令放入队列、 记录命令或者通过网络发送命令。
* 如果你想要实现操作回滚功能， 可使用命令模式。
  * 尽管有很多方法可以实现撤销和恢复功能， 但命令模式可能是其中最常用的一种。
  > 为了能够回滚操作， 你需要实现已执行操作的历史记录功能。 命令历史记录是一种包含所有已执行命令对象及其相关程序状态备份的栈结构。<br/>
  > 这种方法有两个缺点。 首先， 程序状态的保存功能并不容易实现， 因为部分状态可能是私有的。 你可以使用备忘录模式来在一定程度上解决这个问题。<br/>
  > 其次， 备份状态可能会占用大量内存。 因此， 有时你需要借助另一种实现方式： 命令无需恢复原始状态， 而是执行反向操作。 反向操作也有代价： 它可能会很难甚至是无法实现。

**优点**

* 单一职责原则。 你可以解耦触发和执行操作的类。
* 开闭原则。 你可以在不修改已有客户端代码的情况下在程序中创建新的命令。
* 你可以实现撤销和恢复功能。
* 你可以实现操作的延迟执行。
* 你可以将一组简单命令组合成一个复杂命令。

**缺点**

* 代码可能会变得更加复杂， 因为你在发送者和接收者之间增加了一个全新的层次。

## 与其他模式的关系
* [责任链模式](Chain)、 [命令模式](Command)、 [中介者模式](Mediator)和[观察者模式](Observer)用于处理请求发送者和接收者之间的不同连接方式：
  * [责任链](Chain)按照顺序将请求动态传递给一系列的潜在接收者， 直至其中一名接收者对请求进行处理。
  * [命令](Command)在发送者和请求者之间建立单向连接。
  * [中介者](Mediator)清除了发送者和请求者之间的直接连接， 强制它们通过一个中介对象进行间接沟通。
  * [观察者](Observer)允许接收者动态地订阅或取消接收请求。
* [责任链](Chain)的管理者可使用[命令模式](Command)实现。 在这种情况下， 你可以对由请求代表的同一个上下文对象执行许多不同的操作。
  * 还有另外一种实现方式， 那就是请求自身就是一个[命令对象](Command)。 在这种情况下， 你可以对由一系列不同上下文连接而成的链执行相同的操作。
* 你可以同时使用[命令](Command)和[备忘录模式](Memento)来实现 `撤销`。 在这种情况下， 命令用于对目标对象执行各种不同的操作， 备忘录用来保存一条命令执行前该对象的状态。
* [命令](Command)和[策略模式](Strategy)看上去很像， 因为两者都能通过某些行为来参数化对象。 但是， 它们的意图有非常大的不同。
  * 你可以使用[命令](Command)来将任何操作转换为对象。 操作的参数将成为对象的成员变量。 你可以通过转换来延迟操作的执行、 将操作放入队列、 保存历史命令或者向远程服务发送命令等。
  * 另一方面， [策略](Strategy)通常可用于描述完成某件事的不同方式， 让你能够在同一个上下文类中切换算法。
* [原型模式](Prototype)可用于保存命令的历史记录。
* 你可以将[访问者模式](Visitor)视为[命令模式](Command)的加强版本， 其对象可对不同类的多种对象执行操作。

## 典型用例

* 保留请求历史
* 实现回调功能
* 实现撤销功能

## Java世界例子

* [java.lang.Runnable](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)
* [org.junit.runners.model.Statement](https://github.com/junit-team/junit4/blob/master/src/main/java/org/junit/runners/model/Statement.java)
* [Netflix Hystrix](https://github.com/Netflix/Hystrix/wiki)
* [javax.swing.Action](http://docs.oracle.com/javase/8/docs/api/javax/swing/Action.html)

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)
* [J2EE Design Patterns](https://www.amazon.com/gp/product/0596004273/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596004273&linkCode=as2&tag=javadesignpat-20&linkId=f27d2644fbe5026ea448791a8ad09c94)
