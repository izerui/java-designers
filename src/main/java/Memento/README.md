# 备忘录 Memento

## 也称为

Token

## 目的

在不违反封装的情况下，捕获并外部化对象的内部状态，以便稍后可以将对象恢复到该状态。

## 解释

现实的例子

> 我们正在开发一个占星学应用程序，我们需要随着时间的推移分析恒星属性。我们正在使用 Memento 模式创建恒星状态的快照。

简单来说

> 备忘录模式捕获对象内部状态，可以轻松地在任何时间点存储和恢复对象。

维基百科说

> 备忘录模式是一种软件设计模式，它提供了将对象恢复到其先前状态（通过回滚撤消）的能力。

**编程示例**

让我们首先定义我们能够处理的恒星类型。

```java
// 类型枚举
public enum StarType {
    SUN("太阳"),
    RED_GIANT("红矮星"),
    WHITE_DWARF("白矮星"),
    SUPERNOVA("超新星"),
    DEAD("死星"); 
  ...
}
```

接下来，让我们直接进入重点。 `Star` 类是我们需要操作的快照类。特别注意 `getMemento` 和 `setMemento` 方法。

```java
// 星快照 星备忘录
public interface StarMemento {
}

public class Star {

  private StarType type;
  private int ageYears;
  private int massTons;

  public Star(StarType startType, int startAge, int startMass) {
    this.type = startType;
    this.ageYears = startAge;
    this.massTons = startMass;
  }

  public void timePasses() {
    ageYears *= 2;
    massTons *= 8;
    switch (type) {
      case RED_GIANT:
        type = StarType.WHITE_DWARF;
        break;
      case SUN:
        type = StarType.RED_GIANT;
        break;
      case SUPERNOVA:
        type = StarType.DEAD;
        break;
      case WHITE_DWARF:
        type = StarType.SUPERNOVA;
        break;
      case DEAD:
        ageYears *= 2;
        massTons = 0;
        break;
      default:
        break;
    }
  }

  StarMemento getMemento() {
    var state = new StarMementoInternal();
    state.setAgeYears(ageYears);
    state.setMassTons(massTons);
    state.setType(type);
    return state;
  }

  void setMemento(StarMemento memento) {
    var state = (StarMementoInternal) memento;
    this.type = state.getType();
    this.ageYears = state.getAgeYears();
    this.massTons = state.getMassTons();
  }

  @Override
  public String toString() {
    return String.format("%s age: %d years mass: %d tons", type.toString(), ageYears, massTons);
  }

  private static class StarMementoInternal implements StarMemento {

    private StarType type;
    private int ageYears;
    private int massTons;

    // setters and getters ->
    ...
  }
}
```

最后来看我们是如何使用`备忘录模式`来存储和恢复恒星状态的

```java
    var states = new Stack<>();
    var star = new Star(StarType.SUN, 10000000, 500000);
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    while (states.size() > 0) {
      star.setMemento(states.pop());
      LOGGER.info(star.toString());
    }
```

Program output:

```
太阳 age: 10000000 years mass: 500000 tons
红矮星 age: 20000000 years mass: 4000000 tons
白矮星 age: 40000000 years mass: 32000000 tons
超新星 age: 80000000 years mass: 256000000 tons
死星 age: 160000000 years mass: 2048000000 tons
超新星 age: 80000000 years mass: 256000000 tons
白矮星 age: 40000000 years mass: 32000000 tons
红矮星 age: 20000000 years mass: 4000000 tons
太阳 age: 10000000 years mass: 500000 tons
```

## 类图

![alt text](/src/main/resources/puml/uml/memento.png "Memento")

## 时序图

![alt text](/src/main/resources/puml/puml/Memento.png)

## 实现方式

> 原发器拥有对备忘录的完全访问权限， 负责人则只能访问元数据。

* 确定担任原发器角色的类。 重要的是明确程序使用的一个原发器中心对象， 还是多个较小的对象。
* 创建备忘录类。 逐一声明对应每个原发器成员变量的备忘录成员变量。
* 将备忘录类设为不可变。 备忘录只能通过构造函数一次性接收数据。 该类中不能包含设置器。
* 如果你所使用的编程语言支持嵌套类， 则可将备忘录嵌套在原发器中； 如果不支持， 那么你可从备忘录类中抽取一个空接口， 然后让其他所有对象通过接口来引用备忘录。 你可在该接口中添加一些元数据操作， 但不能暴露原发器的状态。
* 在原发器中添加一个创建备忘录的方法。 原发器必须通过备忘录构造函数的一个或多个实际参数来将自身状态传递给备忘录。
* 在原发器类中添加一个用于恢复自身状态的方法。 该方法接受备忘录对象作为参数。 如果你在之前的步骤中抽取了接口， 那么可将接口作为参数的类型。 在这种情况下， 你需要将输入对象强制转换为备忘录， 因为原发器需要拥有对该对象的完全访问权限。
* 无论负责人是命令对象、 历史记录或其他完全不同的东西， 它都必须要知道何时向原发器请求新的备忘录、 如何存储备忘录以及何时使用特定备忘录来对原发器进行恢复。
* 负责人与原发器之间的连接可以移动到备忘录类中。 

## 适用场景

* 当你需要创建对象状态快照来恢复其之前的状态时， 可以使用备忘录模式。
* 当直接访问对象的`成员变量`、 `get方法`或`set方法`将导致破坏其内部快照的封装特性时， 可以使用该模式。

**优点**

* 你可以在不破坏对象封装情况的前提下创建对象状态快照。
* 你可以通过让负责人维护原发器状态历史记录来简化原发器代码。

**缺点**

* 如果客户端过于频繁地创建备忘录， 程序将消耗大量内存。
* 负责人必须完整跟踪原发器的生命周期， 这样才能销毁弃用的备忘录。
* 绝大部分动态编程语言 （例如 PHP、 Python 和 JavaScript） 不能确保备忘录中的状态不被修改。

## 与其他模式的关系

* 你可以同时使用[命令模式](Command)和[备忘录模式](Memento)来实现 `撤销`。 在这种情况下， 命令用于对目标对象执行各种不同的操作， 备忘录用来保存一条命令执行前该对象的状态。
* 你可以同时使用[备忘录](Memento)和[迭代器模式](Iterator)来获取当前迭代器的状态， 并且在需要的时候进行回滚。
* 有时候[原型模式](Prototype)可以作为备忘录的一个简化版本， 其条件是你需要在历史记录中存储的对象的状态比较简单， 不需要链接其他外部资源， 或者链接可以方便地重建。

## 已知用法

* [java.util.Date](http://docs.oracle.com/javase/8/docs/api/java/util/Date.html)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
