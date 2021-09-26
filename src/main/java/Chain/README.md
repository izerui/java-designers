# 责任链 Chain of responsibility

## 目的
通过给多个对象一个处理请求的机会，避免请求的发送者和它的接收者耦合。串联接收对象并在链条中传递请求直到一个对象处理它。

## 解释

真实世界例子

> 兽王大声命令他的军队。最近响应的是指挥官，然后是军官，然后是士兵。指挥官，军官，士兵这里就形成了一个责任链。

通俗的说

> 它帮助构建一串对象。请求从一个对象中进入并结束然后进入到一个个对象中直到找到合适的处理器。

维基百科说

> 在面向对象设计中，责任链模式是一种由源命令对象和一系列处理对象组成的设计模式。每个处理对象包含了其定义的可处理的命令对象类型的逻辑。剩下的会传递给链条中的下一个处理对象。

**程序示例**

用上面示例。首先我们有请求类

```java
/**
 * 请求类.
 */
public class Request {

  /**
   * 请求的类型，链中每个处理者通过它来判断是否执行命令。
   */
  @Getter
  private final RequestType requestType;

  /**
   * 命令
   */
  @Getter
  private final String requestDescription;

  /**
   * 标示是否处理过。请求只能从未处理状态切换到已处理状态，无法“取消处理”请求。
   */
  @Getter
  private boolean handled;

  /**
   * 创建一个指定类型和命令的请求
   *
   * @param requestType        请求类型
   * @param requestDescription 请求命令描述
   */
  public Request(final RequestType requestType, final String requestDescription) {
    this.requestType = Objects.requireNonNull(requestType);
    this.requestDescription = Objects.requireNonNull(requestDescription);
  }

  /**
   * 标记处理过
   */
  public void markHandled() {
    this.handled = true;
  }

  @Override
  public String toString() {
    return getRequestDescription();
  }

}

/**
 * 请求类型枚举
 */
public enum RequestType {

  DEFEND_CASTLE, // 保卫城堡
  TORTURE_PRISONER, // 折磨囚犯
  COLLECT_TAX // 收税

}
```

然后是请求处理器的层次结构基类

```java
/**
 * 处理基类
 */
@Slf4j
public abstract class RequestHandler {

  private final RequestHandler next;

  /**
   * 构造方法,接收下一个处理类
   * @param next
   */
  public RequestHandler(RequestHandler next) {
    this.next = next;
  }

  /**
   * 接收请求
   */
  public void handleRequest(Request req) {
    if (next != null) {
      next.handleRequest(req);
    }
  }

  protected void printHandling(Request req) {
    LOGGER.info("{} 处理请求 \"{}\"", this, req);
  }

  @Override
  public abstract String toString();
}

/**
 * 兽人指挥官处理类.
 */
public class OrcCommander extends RequestHandler {

  public OrcCommander(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (RequestType.DEFEND_CASTLE == req.getRequestType()) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "兽人指挥官";
  }
}

// 兽人中士和兽人士兵的定义与兽人指挥官类似,这里代码省略

```

然后我们有兽人国王下达命令并形成链条处理

```java
/**
 * 兽人国王发出处理链请求
 */
@Slf4j
public class OrcKing {

  private RequestHandler chain;

  public OrcKing() {
    buildChain();
  }

  private void buildChain() {
    // 定义了一个处理类,层级层次为: 指挥官 -> 中士 -> 士兵
    chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
  }

  // 处理请求
  public void makeRequest(Request req) {
    LOGGER.info("兽人国王下达命令: " + req.getRequestDescription());
    chain.handleRequest(req);
  }

}
```

然后这样使用它

```java
OrcKing king = new OrcKing();
king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "保卫城堡"));
king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "折磨囚犯"));
king.makeRequest(new Request(RequestType.COLLECT_TAX, "收税"));
```

控制台输出:

```
兽人国王下达命令: 保卫城堡
兽人指挥官 处理请求 "保卫城堡"

兽人国王下达命令: 折磨囚犯
兽人中士 处理请求 "折磨囚犯"

兽人国王下达命令: 收税
兽人士兵 处理请求 "收税"
```

## 类图

![alt text](../../resources/puml/uml/chain-of-responsibility.urm.png "Chain of Responsibility class diagram")

## 时序图

![alt text](../../resources/puml/puml/Chain.png)

## 实现方式

* 声明处理者接口并描述请求处理方法的签名。
  * 确定客户端如何将请求数据传递给方法。 最灵活的方式是将请求转换为对象， 然后将其以参数的形式传递给处理函数。
* 为了在具体处理者中消除重复的样本代码， 你可以根据处理者接口创建抽象处理者基类。
  * 该类需要有一个成员变量来存储指向链上下个处理者的引用。 你可以将其设置为不可变类。 但如果你打算在运行时对链进行改变， 则需要定义一个设定方法来修改引用成员变量的值。
  * 为了使用方便， 你还可以实现处理方法的默认行为。 如果还有剩余对象， 该方法会将请求传递给下个对象。 具体处理者还能够通过调用父对象的方法来使用这一行为。
* 依次创建具体处理者子类并实现其处理方法。 每个处理者在接收到请求后都必须做出两个决定：
  * 是否自行处理这个请求。
  * 是否将该请求沿着链进行传递。
* 客户端可以自行组装链， 或者从其他对象处获得预先组装好的链。 在后一种情况下， 你必须实现工厂类以根据配置或环境设置来创建链。
* 客户端可以触发链中的任意处理者， 而不仅仅是第一个。 请求将通过链进行传递， 直至某个处理者拒绝继续传递， 或者请求到达链尾。
* 由于链的动态性， 客户端需要准备好处理以下情况：
  * 链中可能只有单个链接。
  * 部分请求可能无法到达链尾。
  * 其他请求可能直到链尾都未被处理。

## 适用场景

* 当程序需要使用不同方式处理不同种类请求， 而且请求类型和顺序预先未知时， 可以使用责任链模式。
  * 该模式能将多个处理者连接成一条链。 接收到请求后， 它会 “询问” 每个处理者是否能够对其进行处理。 这样所有处理者都有机会来处理请求。
* 当必须按顺序执行多个处理者时， 可以使用该模式。
  * 无论你以何种顺序将处理者连接成一条链， 所有请求都会严格按照顺序通过链上的处理者。
* 如果所需处理者及其顺序必须在运行时进行改变， 可以使用责任链模式。
  * 如果在处理者类中有对引用成员变量的设定方法， 你将能动态地插入和移除处理者， 或者改变其顺序。

**优点**

* 你可以控制请求处理的顺序。
* 单一职责原则。 你可对发起操作和执行操作的类进行解耦。
* 开闭原则。 你可以在不更改现有代码的情况下在程序中新增处理者。

**缺点**

* 部分请求可能未被处理。

## 与其他模式的关系

* [责任链模式](Chain)、 [命令模式](Command)、 [中介者模式](Mediator)和[观察者模式](Observer)用于处理请求发送者和接收者之间的不同连接方式：
    * **[责任链](Chain)按照顺序将请求动态传递给一系列的潜在接收者， 直至其中一名接收者对请求进行处理。**
    * **[命令](Command)在发送者和请求者之间建立单向连接。**
    * **[中介者](Mediator)清除了发送者和请求者之间的直接连接， 强制它们通过一个中介对象进行间接沟通。**
    * **[观察者](Observer)允许接收者动态地订阅或取消接收请求。**
* **[责任链](Chain)通常和[组合模式](Composite)结合使用**。 在这种情况下， 叶组件接收到请求后， 可以将请求沿包含全体父组件的链一直传递至对象树的底部。
* [责任链](Chain)的管理者可使用[命令模式](Command)实现。 在这种情况下， 你可以对由请求代表的同一个上下文对象执行许多不同的操作。
* 还有另外一种实现方式， 那就是请求自身就是一个命令对象。 在这种情况下， 你可以对由一系列不同上下文连接而成的链执行相同的操作。
* [责任链](Chain)和[装饰模式](Decorator)的类结构非常相似。 两者都依赖递归组合将需要执行的操作传递给一系列对象。 但是， 两者有几点重要的不同之处。
  * 责任链的管理者可以相互独立地执行一切操作， 还可以随时停止传递请求。 另一方面， 各种装饰可以在遵循基本接口的情况下扩展对象的行为。 此外， 装饰无法中断请求的传递。

## Java世界例子

* javax.servlet.FilterChain
* org.springframework.web.server.handler.DefaultWebFilterChain
* [java.util.logging.Logger#log()](http://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html#log%28java.util.logging.Level,%20java.lang.String%29)
* [Apache Commons Chain](https://commons.apache.org/proper/commons-chain/index.html)
* [javax.servlet.Filter#doFilter()](http://docs.oracle.com/javaee/7/api/javax/servlet/Filter.html#doFilter-javax.servlet.ServletRequest-javax.servlet.ServletResponse-javax.servlet.FilterChain-)

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
