# 原型 Prototype

## 目的

原型模式是用于创建重复的对象，提高性能。这种模式实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。

## 解释

所有的原型类都必须有一个通用的接口， 使得即使在对象所属的具体类未知的情况下也能复制对象。 原型对象可以生成自身的完整副本， 因为相同类的不同对象可以相互访问对方的私有成员变量。

现实世界例子

> 记得多莉吗？克隆的羊！让我们不要谈细节，这里的关键点全是克隆

用通俗的话说

> 通过克隆基于现有对象来创建新对象.

维基百科说

> 原型模式是软件开发中 的一种创建性设计模式。 它用于当
> 要创建的对象类型和原型实例类型一致，该实例可以被克隆用以产生新的该类型对象

简而言之，它允许您创建现有对象的副本并根据需要对其进行修改，而不是从头开始创建对象并进行设置。

**编程示例**
在 Java 中，建议按如下方式实现原型模式。首先，创建一个带有克隆方法的接口。在这个例子中，`Prototype` 
接口通过它的 `copy` 方法实现了这一点。

```java
/**
 * 原型接口.
 */
public interface Prototype {

    Object copy();

}
```

我们的示例包含不同生物的层次结构。例如，让我们看看 `Beast` 和 `OrcBeast` 类。
```java
/**
 * 兽.
 */
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Beast implements Prototype {

    public Beast(Beast source) {
    }

    @Override
    public abstract Beast copy();

}

/**
 * 兽人.
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class OrcBeast extends Beast {

    private final String weapon;

    public OrcBeast(OrcBeast orcBeast) {
        super(orcBeast);
        this.weapon = orcBeast.weapon;
    }

    @Override
    public OrcBeast copy() {
        return new OrcBeast(this);
    }

    @Override
    public String toString() {
        return "兽人开始攻击,使用武器: " + weapon;
    }

}
```

其他的不多说，完整的示例还包含基类 `Mage` 和 `Warlord`，并且除了兽人之外，还有针对精灵的专门实现。

为了充分利用原型模式，我们创建了 `HeroFactory` 和 `HeroFactoryImpl` 类来从原型中生成不同种类的生物。

```java
/**
 * 工厂接口类
 */
public interface HeroFactory {

    // 创造法师
    Mage createMage();

    // 创造军队
    Warlord createWarlord();

    // 创造兽人
    Beast createBeast();

}

/**
 * 工厂实现类
 */
@RequiredArgsConstructor
public class HeroFactoryImpl implements HeroFactory {

    private final Mage mage; // 法师
    private final Warlord warlord; // 军队
    private final Beast beast; // 兽人

    /**
     * Create mage.
     */
    public Mage createMage() {
        return mage.copy();
    }

    /**
     * Create warlord.
     */
    public Warlord createWarlord() {
        return warlord.copy();
    }

    /**
     * Create beast.
     */
    public Beast createBeast() {
        return beast.copy();
    }

}
```

现在，我们能够展示完整的原型模式，通过克隆现有实例来生产新生物。

```java
HeroFactory factory = new HeroFactoryImpl(
new ElfMage("做饭"),
new ElfWarlord("打扫"),
new ElfBeast("站岗")
);
Mage mage = factory.createMage();
Warlord warlord = factory.createWarlord();
Beast beast = factory.createBeast();
LOGGER.info(mage.toString());
LOGGER.info(warlord.toString());
LOGGER.info(beast.toString());
factory = new HeroFactoryImpl(
new OrcMage("锤"),
new OrcWarlord("剑"),
new OrcBeast("激光")
);
mage = factory.createMage();
warlord = factory.createWarlord();
beast = factory.createBeast();
LOGGER.info(mage.toString());
LOGGER.info(warlord.toString());
LOGGER.info(beast.toString());
```

这是运行示例的控制台输出。

```
精灵法师来帮忙: 做饭
精灵军队来帮忙:  打扫
精灵鹰来帮忙: 站岗
兽人法师开始攻击,使用武器: 锤
兽人军队攻击: 剑
兽人开始攻击,使用武器: 激光
```

## 类图

![alt text](./src/main/resources/uml/prototype.urm.png "Prototype pattern class diagram")

## 时序图

![alt text](./src/main/resources/puml/Prototype.png)

## 实现方式

* 创建原型接口， 并在其中声明`clone`克隆方法。 如果你已有类层次结构， 则只需在其`父类`中添加该方法即可。
* 原型类必须另行定义一个以**该类对象为参数的构造函数**。 构造函数必须复制参数对象中的所有成员变量值到新建实体中。 如果你需要修改子类， 则必须调用父类构造函数， 让父类复制其私有成员变量值。
* 如果编程语言不支持方法重载， 那么你可能需要定义一个特殊方法来复制对象数据。 在构造函数中进行此类处理比较方便， 因为它在调用 new运算符后会马上返回结果对象。
* 克隆方法通常只有一行代码： 使用 new运算符调用原型版本的构造函数。 注意， 每个类都必须显式重写克隆方法并使用自身类名调用 new运算符。 否则， 克隆方法可能会生成父类的对象。
* 你还可以创建一个中心化原型注册表， 用于存储常用原型。

## 适用场景

当系统应该独立于其产品的创建、组合、表示和表示方式时，使用原型模式

* 在运行时指定要实例化的类时，例如，通过动态加载。
* 避免构建与产品类层次结构平行的工厂类层次结构。
* 当一个类的实例可以具有仅有的几种不同状态组合中的一种时。安装相应数量的原型并克隆它们可能更方便，而不是每次都使用适当的状态手动实例化类。
* 与克隆相比，创建对象的成本很高。

**优势**

* 你可以克隆对象， 而无需与它们所属的具体类相耦合。
* 你可以克隆预生成原型， 避免反复运行初始化代码。
* 你可以更方便地生成复杂对象。
* 你可以用继承以外的方式来处理复杂对象的不同配置。

**缺点**

* 克隆包含循环引用的复杂对象可能会非常麻烦。

## 现实案例

* 例如 Spring 中，@Service 默认都是单例的。用了私有全局变量，若不想影响下次注入或每次上下文获取 bean，就需要用到原型模式，我们可以通过以下注解来实现，@Scope("prototype")。
  * AbstractBeanDefinition 继承了 Cloneable
* [java.lang.Object#clone()](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#clone%28%29)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
