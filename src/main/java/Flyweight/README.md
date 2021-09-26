# 享元 Flyweight

## 目的

使用共享有效地支持大量细粒度对象。

## 解释

现实世界例子

> 炼金术士的商店货架上摆满了魔法药水。许多药水是相同的，因此无需为每个药水创建一个新对象。
> 相反，一个对象实例可以表示多个货架项目，因此内存占用仍然很小。

简单来说

> 它用于通过尽可能多地与相似对象共享来最小化内存使用或计算开销。

维基百科说

> 在计算机编程中，享元是一种软件设计模式。享元是一种对象通过与其他类似对象共享尽可能多的数据
> 来最小化内存使用；当简单的重复表示会使用不可接受的内存量时，这是一种使用大量对象的方法。

**编程示例**

从上面炼金术士商店的例子可以看出。首先，我们有不同类型的药水：

```java
/**
 * 药剂目录接口
 */
public interface Potion {

  void drink();
}

/**
 * 恢复药水.
 */
@Slf4j
public class HealingPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("你感觉痊愈了. (Potion={})", System.identityHashCode(this));
  }
}

/**
 * 神圣药水.
 */
@Slf4j
public class HolyWaterPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("你感觉到很幸福. (Potion={})", System.identityHashCode(this));
  }
}

/**
 * 隐形药水.
 */
@Slf4j
public class InvisibilityPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("你隐形了. (Potion={})", System.identityHashCode(this));
  }
}
```

然后是实际的 `Flyweight` 享元类 `PotionFactory`，它是创建药水的工厂。

```java
/**
 * PotionFactory 是本例中的享元类。它通过共享对象实例来最小化内存使用。
 * 它保存着药剂的目录类型，并且只有在它不存在的药剂类型下,才会创建新的药剂。
 */
public class PotionFactory {

  // 享元对象
  private final Map<PotionType, Potion> potions;

  public PotionFactory() {
    potions = new EnumMap<>(PotionType.class);
  }

  Potion createPotion(PotionType type) {
    Potion potion = potions.get(type);
    if (potion == null) {
      switch (type) {
        case HEALING:
          potion = new HealingPotion();
          potions.put(type, potion);
          break;
        case HOLY_WATER:
          potion = new HolyWaterPotion();
          potions.put(type, potion);
          break;
        case INVISIBILITY:
          potion = new InvisibilityPotion();
          potions.put(type, potion);
          break;
        case POISON:
          potion = new PoisonPotion();
          potions.put(type, potion);
          break;
        case STRENGTH:
          potion = new StrengthPotion();
          potions.put(type, potion);
          break;
        default:
          break;
      }
    }
    return potion;
  }
}
```

`AlchemistShop` 包含两个魔法药水架子。药水是使用前面提到的`PotionFactory`创建的。

```java
/**
 * 药剂商店的货架上放着药剂。使用 PotionFactory 来提供所有药水。
 */
@Slf4j
public class AlchemistShop {

  private final List<Potion> topShelf;
  private final List<Potion> bottomShelf;

  /**
   * 炼金药水商店.
   */
  public AlchemistShop() {
    PotionFactory factory = new PotionFactory();
    // 上层货架
    topShelf = Arrays.asList(
            factory.createPotion(PotionType.INVISIBILITY),
            factory.createPotion(PotionType.INVISIBILITY),
            factory.createPotion(PotionType.STRENGTH),
            factory.createPotion(PotionType.HEALING),
            factory.createPotion(PotionType.INVISIBILITY),
            factory.createPotion(PotionType.STRENGTH),
            factory.createPotion(PotionType.HEALING),
            factory.createPotion(PotionType.HEALING)
    );
    // 下层货架
    bottomShelf = Arrays.asList(
            factory.createPotion(PotionType.POISON),
            factory.createPotion(PotionType.POISON),
            factory.createPotion(PotionType.POISON),
            factory.createPotion(PotionType.HOLY_WATER),
            factory.createPotion(PotionType.HOLY_WATER)
    );
  }

  /**
   * 获取上层货架上所有药剂的只读目录
   */
  public final List<Potion> getTopShelf() {
    return Collections.unmodifiableList(this.topShelf);
  }

  /**
   * 获取下层货架上所有药剂的只读目录。
   */
  public final List<Potion> getBottomShelf() {
    return Collections.unmodifiableList(this.bottomShelf);
  }

  /**
   * 喝下所有药剂
   */
  public void drinkPotions() {
    LOGGER.info("喝下顶层货架上的所有药剂");
    topShelf.forEach(Potion::drink);
    LOGGER.info("喝下下层货架上的所有药剂");
    bottomShelf.forEach(Potion::drink);
  }
}
```

在我们的场景中，一位勇敢的访客进入炼金术士商店并喝下所有的药水。

```java
// 创建一个摆满药剂的炼金药水商店
AlchemistShop alchemistShop = new AlchemistShop();
// 一位勇敢的访客进入炼金术士商店并喝下所有的药水
alchemistShop.drinkPotions();
```

控制台输出:

```java
喝下顶层货架上的所有药剂
你隐形了. (Potion=1313953385)
你隐形了. (Potion=1313953385)
你感觉强壮了. (Potion=1347137144)
你感觉痊愈了. (Potion=997608398)
你隐形了. (Potion=1313953385)
你感觉强壮了. (Potion=1347137144)
你感觉痊愈了. (Potion=997608398)
你感觉痊愈了. (Potion=997608398)
        
喝下下层货架上的所有药剂
啊! 这个有毒. (Potion=1212899836)
啊! 这个有毒. (Potion=1212899836)
啊! 这个有毒. (Potion=1212899836)
你感觉到很幸福. (Potion=1174290147)
你感觉到很幸福. (Potion=1174290147)
```

## 类图

![alt text](../../resources/puml/uml/flyweight.urm.png "Flyweight pattern class diagram")

## 时序图

![alt text](../../resources/puml/puml/Flyweight.png)

## 实现方式

* 将需要改写为享元的类成员变量拆分为两个部分：
  * 内在状态： 包含不变的、 可在许多对象中重复使用的数据的成员变量。
  * 外在状态： 包含每个对象各自不同的情景数据的成员变量
* 保留类中表示内在状态的成员变量， 并将其属性设置为不可修改。 这些变量仅可在构造函数中获得初始数值。
* 找到所有使用外在状态成员变量的方法， 为在方法中所用的每个成员变量新建一个参数， 并使用该参数代替成员变量。
* 你可以有选择地创建工厂类来管理享元缓存池， 它负责在新建享元时检查已有的享元。 如果选择使用工厂， 客户端就只能通过工厂来请求享元， 它们需要将享元的内在状态作为参数传递给工厂。
* 客户端必须存储和计算外在状态 （情景） 的数值， 因为只有这样才能调用享元对象的方法。 为了使用方便， 外在状态和引用享元的成员变量可以移动到单独的情景类中。

## 适用场景

Flyweight 模式的有效性在很大程度上取决于它的使用方式和位置。当以下所有条件都为真时，应用享元模式：

* 应用程序使用大量对象。
* 由于对象数量庞大，存储成本很高。
* 大多数对象状态都可以是外在的。
* 一旦外部状态被移除，许多对象组可能会被相对较少的共享对象替换。
* 应用程序不依赖于对象标识。由于享元对象可能是共享的，因此对于概念上不同的对象，身份测试将返回 true。

**优点**

* 如果程序中有很多相似对象， 那么你将可以节省大量内存。

**缺点**

* 你可能需要牺牲执行速度来换取内存， 因为他人每次调用享元方法时都需要重新计算部分情景数据。
* 代码会变得更加复杂。 团队中的新成员总是会问： ​ “为什么要像这样拆分一个实体的状态？”。

## 与其他模式的关系

* 你可以使用[享元模式](Flyweight)实现[组合模式](Composite)树的共享叶节点以节省内存。
* [享元](Flyweight)展示了如何生成大量的小型对象， [外观模式](Facade)则展示了如何用一个对象来代表整个子系统。
* 如果你能将对象的所有共享状态简化为一个享元对象， 那么享元就和单例模式类似了。 但这两个模式有两个根本性的不同。
  * 只会有一个单例实体， 但是享元类可以有多个实体， 各实体的内在状态也可以不同。
  * 单例对象可以是可变的。 享元对象是不可变的。

## 已知用途

* org.springframework.beans.factory.support.DefaultListableBeanFactory
* [java.lang.Integer#valueOf(int)](http://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#valueOf%28int%29) and similarly for Byte, Character and other wrapped types.

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
