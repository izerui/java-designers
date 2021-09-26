# 外观 Facade

## 目的
为一个子系统中的一系列接口提供一个统一的接口。外观定义了一个更高级别的接口以便子系统更容易使用。

## 解释

真实世界的例子

> 一个金矿是怎么工作的？“嗯，矿工下去然后挖金子！”你说。这是你所相信的因为你在使用一个金矿对外提供的一个简单接口，在内部它要却要做很多事情。这个简单的接口对复杂的子系统来说就是一个外观。

用通俗的话说

> 外观模式为一个复杂的子系统提供一个简单的接口。

维基百科说

> **外观是为很大体量的代码（比如类库）提供简单接口的一种对象**。

**程序示例**

使用上面金矿的例子。这里我们有矮人矿工们的挖矿顺序。

```java
/**
 * 矮人工作者抽象基类
 */
@Slf4j
public abstract class DwarvenMineWorker {

    public void goToSleep() {
        LOGGER.info("{} 去睡觉.", name());
    }

    public void wakeUp() {
        LOGGER.info("{} 醒来了.", name());
    }

    public void goHome() {
        LOGGER.info("{} 回家.", name());
    }

    public void goToMine() {
        LOGGER.info("{} 去矿井.", name());
    }

    private void action(Action action) {
        switch (action) {
            case GO_TO_SLEEP:
                goToSleep();
                break;
            case WAKE_UP:
                wakeUp();
                break;
            case GO_HOME:
                goHome();
                break;
            case GO_TO_MINE:
                goToMine();
                break;
            case WORK:
                work();
                break;
            default:
                LOGGER.info("Undefined action");
                break;
        }
    }

    /**
     * Perform actions.
     */
    public void action(Action... actions) {
        Arrays.stream(actions).forEach(this::action);
    }

    public abstract void work();

    public abstract String name();

    enum Action {
        GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
    }
}


@Slf4j
// 开挖掘机矿工
public class DwarvenTunnelDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} creates another promising tunnel.", name());
  }

  @Override
  public String name() {
    return "Dwarven tunnel digger";
  }
}

@Slf4j
// 淘金矿工
public class DwarvenGoldDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} digs for gold.", name());
  }

  @Override
  public String name() {
    return "Dwarf gold digger";
  }
}

@Slf4j
// 推车矿工
public class DwarvenCartOperator extends DwarvenMineWorker {

  @Override
  public void work() {
    LOGGER.info("{} moves gold chunks out of the mine.", name());
  }

  @Override
  public String name() {
    return "Dwarf cart operator";
  }
}

```

为了操纵所有这些矿工我们有了这个外观

```java
/**
 * 挖金矿的外观类
 */
public class DwarvenGoldmineFacade {

    // 矿工们
    private final List<DwarvenMineWorker> workers;

    /**
     * 通过构造器初始化相关的必要条件. 比如: 矿工种类及人数
     */
    public DwarvenGoldmineFacade() {
        workers = Arrays.asList(
                new DwarvenGoldDigger(), // 淘金矮人
                new DwarvenCartOperator(), // 运输矮人
                new DwarvenTunnelDigger()); // 挖掘矮人
    }

    // 开始新的一天
    public void startNewDay() {
        makeActions(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE); // 起床,去挖矿
    }

    // 挖矿
    public void digOutGold() {
        makeActions(workers, DwarvenMineWorker.Action.WORK); // 工作
    }

    // 一天结束
    public void endDay() {
        makeActions(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.GO_TO_SLEEP); // 回家,睡觉
    }

    // 抽取出来的复杂外观控制逻辑
    private static void makeActions(
            Collection<DwarvenMineWorker> workers,
            DwarvenMineWorker.Action... actions
    ) {
        workers.forEach(worker -> worker.action(actions));
    }
}
```

现在来使用外观

```java
DwarvenGoldmineFacade facade = new DwarvenGoldmineFacade();
facade.startNewDay();
facade.digOutGold();
facade.endDay();
```

控制台输出:

```
挖矿矮人 醒来了.
挖矿矮人 去矿井.
运输矮人 醒来了.
运输矮人 去矿井.
开挖掘机矮人 醒来了.
开挖掘机矮人 去矿井.

挖矿矮人 挖掘金子.
运输矮人 将金子运出矿山.
开挖掘机矮人 开通了另一条隧道.

挖矿矮人 回家.
挖矿矮人 去睡觉.
运输矮人 回家.
运输矮人 去睡觉.
开挖掘机矮人 回家.
开挖掘机矮人 去睡觉.
```

## 类图

![alt text](/src/main/resources/puml/uml/facade.urm.png "Facade pattern class diagram")

## 时序图

![alt text](/src/main/resources/puml/puml/Facade.png)

## 实现方式

* 考虑能否在现有子系统的基础上提供一个更简单的接口。 如果该接口能让客户端代码独立于众多子系统类， 那么你的方向就是正确的。
* 在一个新的外观类中声明并实现该接口。 外观应将客户端代码的调用重定向到子系统中的相应对象处。 如果客户端代码没有对子系统进行初始化， 也没有对其后续生命周期进行管理， 那么外观必须完成此类工作。
* 如果要充分发挥这一模式的优势， 你必须确保所有客户端代码仅通过外观来与子系统进行交互。 此后客户端代码将不会受到任何由子系统代码修改而造成的影响， 比如子系统升级后， 你只需修改外观中的代码即可。
* 如果外观变得过于臃肿， 你可以考虑将其部分行为抽取为一个新的专用外观类。

## 适用场景

* 如果你需要一个指向复杂子系统的直接接口， 且该接口的功能有限， 则可以使用外观模式。
* 如果需要将子系统组织为多层结构， 可以使用外观。

**优点**

* 你可以让自己的代码独立于复杂子系统。

**缺点**

* 外观可能成为与程序中所有类都耦合的上帝对象。

## 与其他模式的关系

* [外观模式](Facade)为现有对象定义了一个新接口， **[适配器模式](Adapter)则会试图运用已有的接口**。 [适配器](Adapter)通常只封装一个对象， 外观通常会作用于整个对象子系统上。
* 当只需对客户端代码隐藏子系统创建对象的方式时， 你可以使用[抽象工厂模式](Abstract)来代替外观。
* [享元模式](Flyweight)展示了如何生成大量的小型对象， 外观则展示了如何用一个对象来代表整个子系统。
* [外观](Facade)和[中介者模式](Mediator)的职责类似： 它们都尝试在大量紧密耦合的类中组织起合作。
* 外观类通常可以转换为[单例模式](Singleton)类， 因为在大部分情况下一个外观对象就足够了。
* [外观](Facade)与[代理模式](Proxy)的相似之处在于它们都缓存了一个复杂实体并自行对其进行初始化。 代理与其服务对象遵循同一接口， 使得自己和服务对象可以互换， 在这一点上它与外观不同。

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
