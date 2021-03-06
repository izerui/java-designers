# 访问者 Visitor

## 目的

表示要在对象结构的元素上执行的操作。访问者可让你定义新操作，而无需更改其所操作元素的类。

## 解释

真实世界例子

> 考虑有一个带有军队单位的树形结构。指挥官下有两名中士，每名中士下有三名士兵。基于这个层级结构实现访问者模式，我们可以轻松创建与指挥官，中士，士兵或所有人员互动的新对象

通俗的说

> 访问者模式定义可以在数据结构的节点上执行的操作。

维基百科说

> 在面向对象的程序设计和软件工程中，访问者设计模式是一种将算法与操作对象的结构分离的方法。这种分离的实际结果是能够在不修改结构的情况下向现有对象结构添加新操作。

**程序示例**

使用上面的军队单元的例子，我们首先由单位和单位访问器类型。

```java
// 基本单元
public abstract class Unit {

  private final Unit[] children;

  // 构造多个子单元
  public Unit(Unit... children) {
    this.children = children;
  }

  // 自身的下级接受访问
  public void accept(UnitVisitor visitor) {
    Arrays.stream(children).forEach(child -> child.accept(visitor));
  }
}

/**
 * 访问接口
 */
public interface UnitVisitor {

  // 访问士兵
  void visitSoldier(Soldier soldier);

  // 访问中士
  void visitSergeant(Sergeant sergeant);

  // 访问指挥官
  void visitCommander(Commander commander);

}
```

然后我们有具体的单元。

```java
/**
 * 指挥官.
 */
public class Commander extends Unit {

  public Commander(Unit... children) {
    super(children);
  }

  @Override
  public void accept(UnitVisitor visitor) {
    visitor.visitCommander(this);
    super.accept(visitor);
  }

  @Override
  public String toString() {
    return "指挥官";
  }
}

/**
 * 中士.
 */
public class Sergeant extends Unit {

  public Sergeant(Unit... children) {
    super(children);
  }

  @Override
  public void accept(UnitVisitor visitor) {
    visitor.visitSergeant(this);
    super.accept(visitor);
  }

  @Override
  public String toString() {
    return "中士";
  }
}

/**
 * 士兵.
 */
public class Soldier extends Unit {

  public Soldier(Unit... children) {
    super(children);
  }

  @Override
  public void accept(UnitVisitor visitor) {
    visitor.visitSoldier(this);
    super.accept(visitor);
  }

  @Override
  public String toString() {
    return "士兵";
  }
}
```

然后有一些具体的访问者。

```java
/**
 * 指挥官访问者.
 */
@Slf4j
public class CommanderVisitor implements UnitVisitor {

  @Override
  public void visitSoldier(Soldier soldier) {
    // Do nothing
  }

  @Override
  public void visitSergeant(Sergeant sergeant) {
    // Do nothing
  }

  @Override
  public void visitCommander(Commander commander) {
    LOGGER.info("很高兴看见你 {}", commander);
  }
}

/**
 * 中士访问者.
 */
@Slf4j
public class SergeantVisitor implements UnitVisitor {

  @Override
  public void visitSoldier(Soldier soldier) {
    // Do nothing
  }

  @Override
  public void visitSergeant(Sergeant sergeant) {
    LOGGER.info("嗨 {}", sergeant);
  }

  @Override
  public void visitCommander(Commander commander) {
    // Do nothing
  }
}

/**
 * 士兵访问者.
 */
@Slf4j
public class SoldierVisitor implements UnitVisitor {

  @Override
  public void visitSoldier(Soldier soldier) {
    LOGGER.info("你好 {}", soldier);
  }

  @Override
  public void visitSergeant(Sergeant sergeant) {
    // Do nothing
  }

  @Override
  public void visitCommander(Commander commander) {
    // Do nothing
  }
}
```

最后，来看看实际遍历树来进行访问指定类型的节点。

```java
// 生成指挥官1-中士2-士兵6 结构的树层次结构
Unit commander = new Commander(
new Sergeant(new Soldier(), new Soldier(), new Soldier()),
new Sergeant(new Soldier(), new Soldier(), new Soldier())
);
// 遍历树访问所有士兵
commander.accept(new SoldierVisitor());
输出:
        你好 士兵
        你好 士兵
        你好 士兵
        你好 士兵
        你好 士兵
        你好 士兵

// 遍历树访问所有中士
commander.accept(new SergeantVisitor());
输出:
        嗨 中士
        嗨 中士
        
// 遍历树访问所有指挥官
commander.accept(new CommanderVisitor());
输出:        
        很高兴看见你 指挥官
```

## 类图

![alt text](../../resources/uml/visitor_1.png "Visitor")

## 时序图

![alt text](../../resources/puml/Visitor.png)

## 实现方式

* 在访问者接口中声明一组 `访问` 方法， 分别对应程序中的每个具体元素类。
* 声明元素接口。 如果程序中已有元素类层次接口， 可在层次结构基类中添加抽象的 `接收` 方法。 该方法必须接受访问者对象作为参数。
* 在所有具体元素类中实现接收方法。 这些方法必须将调用重定向到当前元素对应的访问者对象中的访问者方法上。
* 元素类只能通过访问者接口与访问者进行交互。 不过访问者必须知晓所有的具体元素类， 因为这些类在访问者方法中都被作为参数类型引用。
* 为每个无法在元素层次结构中实现的行为创建一个具体访问者类并实现所有的访问者方法。
  * 你可能会遇到访问者需要访问元素类的部分私有成员变量的情况。 在这种情况下， 你要么将这些变量或方法设为公有， 这将破坏元素的封装； 要么将访问者类嵌入到元素类中。 后一种方式只有在支持嵌套类的编程语言中才可能实现。
* 客户端必须创建访问者对象并通过 `接收` 方法将其传递给元素。

## 适用场景

* 如果你需要对一个复杂对象结构 （例如对象树） 中的所有元素执行某些操作， 可使用访问者模式。
  * 访问者模式通过在访问者对象中为多个目标类提供相同操作的变体， 让你能在属于不同类的一组对象上执行同一操作。
* 可使用访问者模式来清理辅助行为的业务逻辑。
  * 该模式会将所有非主要的行为抽取到一组访问者类中， 使得程序的主要类能更专注于主要的工作。
* 当某个行为仅在类层次结构中的一些类中有意义， 而在其他类中没有意义时， 可使用该模式。
  * 你可将该行为抽取到单独的访问者类中， 只需实现接收相关类的对象作为参数的访问者方法并将其他方法留空即可。

**优点**

* 开闭原则。 你可以引入在不同类对象上执行的新行为， 且无需对这些类做出修改。
* 单一职责原则。 可将同一行为的不同版本移到同一个类中。
* 访问者对象可以在与各种对象交互时收集一些有用的信息。 当你想要遍历一些复杂的对象结构 （例如对象树）， 并在结构中的每个对象上应用访问者时， 这些信息可能会有所帮助。

**缺点**

* 每次在元素层次结构中添加或移除一个类时， 你都要更新所有的访问者。
* 在访问者同某个元素进行交互时， 它们可能没有访问元素私有成员变量和方法的必要权限。

## 与其他模式的关系

* 你可以将[访问者模式](Visitor)视为[命令模式](Command)的加强版本， 其对象可对不同类的多种对象执行操作。
* 你可以使用[访问者](Visitor)对整个**组合模式树**(嵌套对象)执行操作。
* 可以同时使用[访问者](Visitor)和[迭代器模式](Iterator)来遍历复杂数据结构， 并对其中的元素执行所需操作， 即使这些元素所属的类完全不同。

## 真实例子

* org.springframework.beans.factory.config.BeanDefinitionVisitor Spring 中的 BeanDefinitionVisitor 类主要用于访问 BeanDefinition，解析属性或者构造方法里面的占位符，并把解析结果更新到 BeanDefinition 中。这里应用的就是访问者模式
* [Apache Wicket](https://github.com/apache/wicket) component tree, see [MarkupContainer](https://github.com/apache/wicket/blob/b60ec64d0b50a611a9549809c9ab216f0ffa3ae3/wicket-core/src/main/java/org/apache/wicket/MarkupContainer.java)
* [javax.lang.model.element.AnnotationValue](http://docs.oracle.com/javase/8/docs/api/javax/lang/model/element/AnnotationValue.html) and [AnnotationValueVisitor](http://docs.oracle.com/javase/8/docs/api/javax/lang/model/element/AnnotationValueVisitor.html)
* [javax.lang.model.element.Element](http://docs.oracle.com/javase/8/docs/api/javax/lang/model/element/Element.html) and [Element Visitor](http://docs.oracle.com/javase/8/docs/api/javax/lang/model/element/ElementVisitor.html)
* [java.nio.file.FileVisitor](http://docs.oracle.com/javase/8/docs/api/java/nio/file/FileVisitor.html)

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)
