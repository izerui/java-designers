# 抽象工厂 Abstract Factory

## 或称

工具包

## 目的

提供一个用于创建相关对象家族的接口，而无需指定其具体类。

## 解释

真实世界例子

> 要创建一个王国，我们需要具有共同主题的对象。 精灵王国需要精灵王，精灵城堡和精灵军队，而兽人王国需要兽王，精灵城堡和兽人军队。 王国中的对象之间存在依赖性。

通俗的说

> 工厂的工厂； 一个将单个但相关/从属的工厂分组在一起而没有指定其具体类别的工厂。

维基百科上说

> 抽象工厂模式提供了一种封装一组具有共同主题的单个工厂而无需指定其具体类的方法

**程序示例**

翻译上面的王国示例。 首先，我们为王国中的对象提供了一些接口和实现。

```java
// 城堡
public interface Castle {
    String getDescription();
}

// 国王
public interface King {
  String getDescription();
}

// 军队
public interface Army {
  String getDescription();
}

// 精灵城堡
public class ElfCastle implements Castle {
    static final String DESCRIPTION = "这是精灵城堡！";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
// 精灵王
public class ElfKing implements King {
    static final String DESCRIPTION = "这是精灵国王！";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
// 精灵军队
public class ElfArmy implements Army {
    static final String DESCRIPTION = "这是精灵军队！";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

// 兽人同上类似实现。。。

```

然后我们有了`王国工厂`的抽象和实现

```java
// 王国工厂基类
public interface KingdomFactory {
  Castle createCastle(); // 创建城堡
  King createKing(); // 创建国王
  Army createArmy(); // 创建军队
}

// 精灵王国工厂类
public class ElfKingdomFactory implements KingdomFactory {
  public Castle createCastle() {
    return new ElfCastle();
  }
  public King createKing() {
    return new ElfKing();
  }
  public Army createArmy() {
    return new ElfArmy();
  }
}

// 兽人王国工厂类
public class OrcKingdomFactory implements KingdomFactory {
  public Castle createCastle() {
    return new OrcCastle();
  }
  public King createKing() {
    return new OrcKing();
  }
  public Army createArmy() {
    return new OrcArmy();
  }
}
```

现在通过抽象工厂，我们就可以一系列创建出各个王国内部的国王、城堡、军队等。下来就可以通过创建出来的王国对象来使用相关对象了。

```java
LOGGER.info("精灵王国.");
createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
LOGGER.info(kingdom.getArmy().getDescription());
LOGGER.info(kingdom.getCastle().getDescription());
LOGGER.info(kingdom.getKing().getDescription());

LOGGER.info("兽人王国.");
createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
LOGGER.info(kingdom.getArmy().getDescription());
LOGGER.info(kingdom.getCastle().getDescription());
LOGGER.info(kingdom.getKing().getDescription());
```

程序输出:

```java
精灵王国.
这是精灵军队.
这是精灵城堡.
这是精灵国王
兽人王国.
这是兽人军队.
这是兽人城堡.
这是兽人领袖
```

现在，我们可以通过抽象工厂为不同的王国来创建工厂类。 在此示例中，我们创建了`FactoryMaker`，负责返回`ElfKingdomFactory`或`OrcKingdomFactory`的实例。 
客户可以使用`FactoryMaker`来创建所需的具体工厂，该工厂随后将生产不同的具体对象（军队，国王，城堡）。 在此示例中，我们还使用了一个枚举来参数化客户要求的王国工厂类型。（这里使用了[工厂方法模式](Factory)）

```java
// inner class with App
public static class FactoryMaker {

  public enum KingdomType {
    ELF, ORC
  }

  public static KingdomFactory makeFactory(KingdomType type) {
    switch (type) {
      case ELF:
        return new ElfKingdomFactory();
      case ORC:
        return new OrcKingdomFactory();
      default:
        throw new IllegalArgumentException("KingdomType not supported.");
    }
  }
}

public static void main(String[] args) {
    LOGGER.info("精灵王国");
    KingdomFactory elfKingdomFactory = FactoryMaker.makeFactory(FactoryMaker.KingdomType.ELF);
    LOGGER.info(elfKingdomFactory.createArmy().getDescription());
    LOGGER.info(elfKingdomFactory.createCastle().getDescription());
    LOGGER.info(elfKingdomFactory.createKing().getDescription());

    LOGGER.info("兽人王国");
    KingdomFactory orcKingdomFactory = FactoryMaker.makeFactory(FactoryMaker.KingdomType.ORC);
    LOGGER.info(orcKingdomFactory.createArmy().getDescription());
    LOGGER.info(orcKingdomFactory.createCastle().getDescription());
    LOGGER.info(orcKingdomFactory.createKing().getDescription());
}
```

## 类图

![alt text](../../resources/puml/uml/abstract-factory.urm.png "Abstract Factory class diagram")

## 时序图

![alt text](../../resources/puml/puml/Abstract.png)

## 实现方式

* 以不同的产品类型与产品变体为维度绘制矩阵。
* 为所有产品声明抽象产品接口。 然后让所有具体产品类实现这些接口。
* 声明抽象工厂接口， 并且在接口中为所有抽象产品提供一组构建方法。
* 为每种产品变体实现一个具体工厂类。
* 在应用程序中开发初始化代码。 该代码根据应用程序配置或当前环境， 对特定具体工厂类进行初始化。 然后将该工厂对象传递给所有需要创建产品的类。
* 找出代码中所有对产品构造函数的直接调用， 将其替换为对工厂对象中相应构建方法的调用。


## 适用场景

* 如果代码需要与多个不同系列的相关产品交互， 但是由于无法提前获取相关信息， 或者出于对未来扩展性的考虑， 你不希望代码基于产品的具体类进行构建， 在这种情况下， 你可以使用抽象工厂。
* 如果你有一个基于一组抽象方法的类， 且其主要功能因此变得不明确， 那么在这种情况下可以考虑使用抽象工厂模式。

示例场景

* 在程序运行时从`FileSystemAcmeService` ，`DatabaseAcmeService` 或`NetworkAcmeService`中选择并调用一个
* 更加容易编写不同的单元测试用例
* 适用于不同操作系统的UI工具

**优点**

* 你可以确保同一工厂生成的产品相互匹配。
* 你可以避免客户端和具体产品代码的耦合。
* 单一职责原则。 你可以将产品生成代码抽取到同一位置， 使得代码易于维护。
* 开闭原则。 向应用程序中引入新产品变体时， 你无需修改客户端代码。

**缺点**

* 由于采用该模式需要向应用中引入众多接口和类， 代码可能会比之前更加复杂。

## 与其他模式的关系

* [建造者模式](Builder)重点关注如何分步生成复杂对象。 [抽象工厂](Abstract)专门用于生产一系列相关对象。 [抽象工厂](Abstract)会马上返回产品， [建造者模式](Builder)则允许你在获取产品前执行一些额外构造步骤。
* 抽象工厂模式通常基于一组工厂方法， 但你也可以使用[原型模式](Prototype)来生成这些类的方法。
* 当只需对客户端代码隐藏子系统创建对象的方式时， 你可以使用抽象工厂来代替[外观模式](Facade)。
* 你可以将抽象工厂和[桥接模式](Bridge)搭配使用。 如果由桥接定义的抽象只能与特定实现合作， 这一模式搭配就非常有用。 在这种情况下， 抽象工厂可以对这些关系进行封装， 并且对客户端代码隐藏其复杂性。
* [抽象工厂](Abstract)、 [建造者](Builder)和[原型](Prototype)都可以用[单例模式](Singleton)来实现。



## 教程

* [Abstract Factory Pattern Tutorial](https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java)

## 已知使用

* [javax.xml.parsers.DocumentBuilderFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/parsers/DocumentBuilderFactory.html)
* [javax.xml.transform.TransformerFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/transform/TransformerFactory.html#newInstance--)
* [javax.xml.xpath.XPathFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/xpath/XPathFactory.html#newInstance--)

## 相关模式

* [Factory Method](https://java-design-patterns.com/patterns/factory-method/)
* [Factory Kit](https://java-design-patterns.com/patterns/factory-kit/)

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
