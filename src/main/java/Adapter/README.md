# 适配器 Adapter

## 又被称为
包装器

## 目的
将一个接口转换成另一个客户所期望的接口。适配器让那些本来因为接口不兼容的类可以合作无间。

## 解释

现实世界例子
>
* 读卡器作为内存SD卡和电脑之间的适配器，你将内存SD卡插入读卡器，再将读卡器插入电脑USB接口，这样电脑可以通过读卡器来读取SD卡中的数据
* 另一个例子是著名的电源适配器；三脚插头不能连接到双管插座，它需要使用一个电源适配器，使其与双管插座兼容。
* 翻译人员将一个人所说的话翻译成另一个人所说的话

用直白的话来说

> 适配器模式允许你在适配器中包装一个不兼容的对象，使其与另一个类兼容

维基百科中说

> 在软件工程中，**适配器模式是一种可以让现有类的接口把其作为其他接口来使用的设计模式**。它经常用来使现有的类和其他类能够工作并且不用修改其他类的源代码。

**编程样例(对象适配器)**

假设海盗来了，我们的船长需要逃跑，但是只有渔船可用。我们需要创造一个适配器，让船长能够用他的划艇技能操作渔船.

首先我们有接口`RowingBoat 开划艇技能`和`FishingBoat 一艘渔船`

```java
/**
 * 开划艇的基本技能
 */
public interface RowingBoat {

    void row();

}

/**
 * 渔船 - 设备
 */
@Slf4j
final class FishingBoat {

    void sail() {
        LOGGER.info("渔船开始航行.");
    }

}
```
船长有一个的`RowingBoat 开划艇技能`接口的实现，这样可以航行

```java
/**
 * 船长 - 客户端
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Captain {

    // 划艇技能
    private RowingBoat rowingBoat;

    // 通过划艇技能开动船只
    void row() {
        rowingBoat.row();
    }

}
```

现在海盗来了，我们的船长需要逃跑但是只有一个渔船可用。我们需要创建一个可以让船长使用其开划艇技能来操作渔船的适配器。

```java
/**
 * 适配器类。将设备接口（{@link FishingBoat}）适配为客户端（{@link Captain}）所期望的{@link RowingBoat}接口。
 */
public class FishingBoatAdapter implements RowingBoat {

    // 渔船
    private final FishingBoat boat = new FishingBoat();

    public final void row() {
        boat.sail();
    }
}

```

现在 `船长` 可以使用`FishingBoat 渔船`来逃离海盗了。

```java
var captain = new Captain(new FishingBoatAdapter());
captain.row();
```

## 类图
![alt text](../../resources/uml/adapter.urm.png "Adapter class diagram")

## 时序图

![alt text](../../resources/puml/Adapter.png)

## 实现方式

* 确保至少有两个类的接口不兼容：
* 声明客户端接口， 描述客户端如何与服务交互。
* 创建遵循客户端接口的适配器类。 所有方法暂时都为空。
* 在适配器类中添加一个成员变量用于保存对于服务对象的引用。 通常情况下会通过构造函数对该成员变量进行初始化， 但有时在调用其方法时将该变量传递给适配器会更方便。
* 依次实现适配器类客户端接口的所有方法。 适配器会将实际工作委派给服务对象， 自身只负责接口或数据格式的转换。
* 客户端必须通过客户端接口使用适配器。 这样一来， 你就可以在不影响客户端代码的情况下修改或扩展适配器。

## 适用场景

* 当你希望使用某个类， 但是其接口与其他代码不兼容时， 可以使用适配器类。
* 如果您需要复用这样一些类， 他们处于同一个继承体系， 并且他们又有了额外的一些共同的方法， 但是这些共同的方法不是所有在这一继承体系中的子类所具有的共性。

**优点**

* 单一职责原则，你可以将接口或数据转换代码从程序主要业务逻辑中分离。
* 开闭原则。 只要客户端代码通过客户端接口与适配器进行交互， 你就能在不修改现有客户端代码的情况下在程序中添加新类型的适配器。

**缺点**

* 代码整体复杂度增加， 因为你需要新增一系列接口和类。 有时直接更改服务类使其与其他代码兼容会更简单。

## 与其他模式的关系

* [桥接模式](Bridge)通常会于开发前期进行设计， 使你能够将程序的各个部分独立开来以便开发。 另一方面， 适配器模式通常在已有程序中使用， 让相互不兼容的类能很好地合作。
* 适配器可以对已有对象的接口进行修改， [装饰模式](Decorator)则能在不改变对象接口的前提下强化对象功能。 此外， 装饰还支持递归组合， 适配器则无法实现。
* 适配器能为被封装对象提供不同的接口， [代理模式](Proxy)能为对象提供相同的接口， [装饰模式](Decorator)则能为对象提供加强的接口。
* [外观模式](Facade)为现有对象定义了一个新接口， 适配器则会试图运用已有的接口。 适配器通常只封装一个对象， [外观模式](Facade)通常会作用于整个对象子系统上。
* [桥接模式](Bridge)、 [状态模式](State)和[策略模式](Strategy) （在某种程度上包括适配器） 模式的接口非常相似。 实际上， 它们都基于组合模式——即**将工作委派给其他对象**， 不过也各自解决了不同的问题。 模式并不只是以特定方式组织代码的配方， 你还可以使用它们来和其他开发者讨论模式所解决的问题。

## 现实世界的案例

* Spring 中用上的适配器模式:
  * JpaVendorAdapter
  * HibernateJpaVendorAdapter
  * HandlerInterceptorAdapter
  * MessageListenerAdapter
  * SpringContextResourceAdapter
  * ClassPreProcessorAgentAdapter
  * RequestMappingHandlerAdapter
  * AnnotationMethodHandlerAdapter
  * WebMvcConfigurerAdapter
* [java.util.Arrays#asList()](http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#asList%28T...%29)
* [java.util.Collections#list()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#list-java.util.Enumeration-)
* [java.util.Collections#enumeration()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#enumeration-java.util.Collection-)
* [javax.xml.bind.annotation.adapters.XMLAdapter](http://docs.oracle.com/javase/8/docs/api/javax/xml/bind/annotation/adapters/XmlAdapter.html#marshal-BoundType-)


## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [J2EE Design Patterns](https://www.amazon.com/gp/product/0596004273/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596004273&linkCode=as2&tag=javadesignpat-20&linkId=48d37c67fb3d845b802fa9b619ad8f31)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)

```

```
