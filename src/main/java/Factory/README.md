# 工厂方法 Factory

## 也被称为

* 简单工厂
* 静态工厂方法

## 含义

在工厂类中提供一个封装的静态工厂方法，用于隐藏对象初始化细节，使客户端代码可以专注于使用，而不用关心类的初始化过程。

## 解释

现实例子

> 假设我们有一个需要连接到 SQL Server 的 Web 应用，但现在我们需要切换到连接 Oracle。为了不修改现有代码的情况下做到这一点，
> 我们需要实现简单工厂模式。在这种模式下，可以通过调用一个静态方法来创建与给定数据库的连接。

维基百科

> 工厂类是一个用于创建其他对象的对象 -- 从形式上看，工厂方法是一个用于返回不同原型或类型的函数或方法。

**编程示例**

这是一个炼金师制造硬币的例子. CoinFactory 是一个工厂类并且提供了一个静态方法来创建不同的硬币.

我们有一个  `Coin 硬币`接口，以及实现类 `GoldCoin 金币`, `CopperCoin 铜币`。

```java
/**
 * 硬币接口
 */
public interface Coin {

    String getDescription();

}

/**
 * 铜币实现.
 */
public class CopperCoin implements Coin {

    static final String DESCRIPTION = "这是一枚铜币.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

/**
 * 金币实现.
 */
public class GoldCoin implements Coin {

    static final String DESCRIPTION = "这是一枚金币.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
```

以下的枚举用于表示支持的 `Coin 硬币` 多个类型

```java
/**
 * 不同的硬币类型枚举.
 */
@RequiredArgsConstructor
@Getter
public enum CoinType {

    COPPER(CopperCoin::new),
    GOLD(GoldCoin::new);

    /**
     * @FunctionalInterface 函数式接口变量,方便构造硬币对象
     */
    private final Supplier<Coin> constructor;
}

```

接着我们实现了一个静态方法  `getCoin` 用于封装工厂类 `CoinFactory`  创建 `Coin` 具体对象实例的细节。

```java
/**
 * 硬币工厂
 */
public class CoinFactory {

    /**
     * 工厂方法通过 [硬币类型] 参数返回适当的硬币对象.
     */
    public static Coin getCoin(CoinType type) {
        return type.getConstructor().get();
    }
}
```

现在我们可以在客户端代码中通过工厂类创建不同类型的 `Car` 对象实例。

```java
LOGGER.info("炼金师开始工作.");
Coin coin1 = CoinFactory.getCoin(CoinType.COPPER);
Coin coin2 = CoinFactory.getCoin(CoinType.GOLD);
LOGGER.info(coin1.getDescription());
LOGGER.info(coin2.getDescription());
```

程序输出：

```java
炼金师开始工作.
这是一枚铜币.
这是一枚金币.
```

## 类图

![alt text](../../resources/puml/uml/factory.urm.png "Factory pattern class diagram")

## 时序图

![alt text](../../resources/puml/puml/Factory.png)

## 实现方式

* 所有产品子类都基于相同的一个接口基类来实现，该接口声明的方法对所有产品子类来说都有意义。
* 在创建类中添加一个空的工厂方法，该方法的返回类型必须遵循通用的产品接口。
* 在创建类的创建方法中来创建产品，可以通过入参控制返回的产品类型。
* 如果应用中的产品类型太多， 那么为每个产品创建子类并无太大必要，这时你也可以在子类中复用基类中的控制参数。例如： 通过传递给`飞机` 类传入一些参数比如：`运输`、`战斗`来返回相同的对象，但是就是一个运输机、战斗机。。。

## 适用场景

* 当你在编写代码的过程中，如果无法预知对象确切类别（可能会有未知产品添加）及其依赖关系时，可使用工厂方法。
* 如果你希望用户能扩展你软件库或框架的内部组件，可使用工厂方法。
* 如果你希望复用现有对象来节省系统资源，而不是每次都重新创建对象，可使用工厂方法。

**优点**

* 可以避免创建者和具体产品之间的紧密耦合。
* 单一职责原则：可以将产品创建代码放在程序的单一位置， 从而使得代码更容易维护。
* 开闭原则：无需更改现有客户端代码， 就可以在程序中引入新的产品类型。

**缺点**

* 应用工厂方法模式需要引入许多新的子类， 代码可能会因此变得更复杂。 最好的情况是将该模式引入创建者类的现有层次结构中。

## 与其他模式的关系
* 在许多设计工作的初期都会使用工厂方法模式 （较为简单， 而且可以更方便地通过子类进行定制）， 随后演化为使用[抽象工厂模式](Abstract)、 [原型模式](Prototype)或[建造者模式](Builder) （更灵活但更加复杂）。
* [抽象工厂模式](Abstract)通常基于一组工厂方法， 但你也可以使用[原型模式](Prototype)来生成这些类的方法。
* 你可以同时使用工厂方法和[迭代器模式](Iterator)来让子类集合返回不同类型的迭代器， 并使得迭代器与集合相匹配。
* [原型模式](Prototype)并不基于继承， 因此没有继承的缺点。 另一方面， 原型需要对被复制对象进行复杂的初始化。 工厂方法基于继承， 但是它不需要初始化步骤。
* 工厂方法是[模板方法模式](Template)的一种特殊形式。 同时， 工厂方法可以作为一个大型模板方法中的一个步骤。

## 现实案例

* org.springframework.beans.factory.BeanFactory#getBean()
* [java.util.Calendar#getInstance()](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html#getInstance--)
* [java.util.ResourceBundle#getBundle()](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html#getBundle-java.lang.String-)
* [java.text.NumberFormat#getInstance()](https://docs.oracle.com/javase/8/docs/api/java/text/NumberFormat.html#getInstance--)
* [java.nio.charset.Charset#forName()](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html#forName-java.lang.String-)
* [java.net.URLStreamHandlerFactory#createURLStreamHandler(String)](https://docs.oracle.com/javase/8/docs/api/java/net/URLStreamHandlerFactory.html) (Returns different singleton objects, depending on a protocol)
* [java.util.EnumSet#of()](https://docs.oracle.com/javase/8/docs/api/java/util/EnumSet.html#of(E))
* [javax.xml.bind.JAXBContext#createMarshaller()](https://docs.oracle.com/javase/8/docs/api/javax/xml/bind/JAXBContext.html#createMarshaller--) and other similar methods.

## 相关模式

* [Factory Method](https://java-design-patterns.com/patterns/factory-method/)
* [Factory Kit](https://java-design-patterns.com/patterns/factory-kit/)
* [Abstract Factory](https://java-design-patterns.com/patterns/abstract-factory/)

