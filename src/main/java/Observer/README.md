# 观察者 Observer

## 又被称为

发布订阅模式

## 目的

定义一种一对多的对象依赖关系这样当一个对象改变状态时，所有依赖它的对象都将自动通知或更新。

## 解释

真实世界例子

> 在遥远的土地上生活着霍比特人和兽人的种族。他们都是户外生活的人所以他们密切关注天气的变化。可以说他们不断地关注着天气。

通俗的说

> 注册成为一个观察者以接收对象状态的改变。

维基百科说

> 观察者模式是这样的一种软件设计模式：它有一个被称为主题的对象，维护着一个所有依赖于它的依赖者清单，也就是观察者清单，当主题的状态发生改变时，主题通常会调用观察者的方法来自动通知观察者们。

**编程示例**

让我们先来介绍天气观察者的接口以及我们的种族，兽人和霍比特人。

```java
// 天气观察者基类
public interface WeatherObserver {

    // 更新当前天气
  void update(WeatherType currentWeather);
}

@Slf4j
// 兽人实现了观察天气基类
public class Orcs implements WeatherObserver {

  @Override
  public void update(WeatherType currentWeather) {
    LOGGER.info("The orcs are facing " + currentWeather.getDescription() + " weather now");
  }
}

@Slf4j
// 霍比特人实现了观察天气基类
public class Hobbits implements WeatherObserver {

  @Override
  public void update(WeatherType currentWeather) {
    switch (currentWeather) {
      LOGGER.info("The hobbits are facing " + currentWeather.getDescription() + " weather now");
  }
}
```

然后这里是不断变化的天气。

```java
@Slf4j
// 天气
public class Weather {

    // 当前天气
  private WeatherType currentWeather;
  // 多个天气观察者
  private final List<WeatherObserver> observers;

  public Weather() {
    observers = new ArrayList<>();
    currentWeather = WeatherType.SUNNY;
  }

  public void addObserver(WeatherObserver obs) {
    observers.add(obs);
  }

  public void removeObserver(WeatherObserver obs) {
    observers.remove(obs);
  }

  /**
   * Makes time pass for weather.
   */
  public void timePasses() {
    var enumValues = WeatherType.values();
    currentWeather = enumValues[(currentWeather.ordinal() + 1) % enumValues.length];
    LOGGER.info("The weather changed to {}.", currentWeather);
    notifyObservers();
  }

  private void notifyObservers() {
    for (var obs : observers) {
      obs.update(currentWeather);
    }
  }
}
```

这是完整的示例。

```java
    Weather weather = new Observer.Weather();
    weather.addObserver(new Observer.Orcs());
    weather.addObserver(new Observer.Hobbits());

    weather.timePasses();
    weather.timePasses();
    weather.timePasses();
    weather.timePasses();

    // Generic observer inspired by Java Generics and Collections by Naftalin & Wadler
    LOGGER.info("--运行泛型版本--");
    GWeather genericWeather = new GWeather();
    genericWeather.addObserver(new GOrcs());
    genericWeather.addObserver(new GHobbits());

    genericWeather.timePasses();
    genericWeather.timePasses();
    genericWeather.timePasses();
    genericWeather.timePasses();
```

控制台输出:

```shell
天气变成了 雨天.
兽人发现现在天气是: 雨天
霍比特人发现当前天气是: 雨天
天气变成了 狂风天气.
兽人发现现在天气是: 狂风天气
霍比特人发现当前天气是: 狂风天气
天气变成了 冬天.
兽人发现现在天气是: 冬天
霍比特人发现当前天气是: 冬天
天气变成了 晴天.
兽人发现现在天气是: 晴天
霍比特人发现当前天气是: 晴天
        
        
--运行泛型版本--
天气变成了: 雨天.
兽人发现现在天气是: 雨天
霍比特人发现现在天气是: 雨天
天气变成了: 狂风天气.
兽人发现现在天气是: 狂风天气
霍比特人发现现在天气是: 狂风天气
天气变成了: 冬天.
兽人发现现在天气是: 冬天
霍比特人发现现在天气是: 冬天
天气变成了: 晴天.
兽人发现现在天气是: 晴天
霍比特人发现现在天气是: 晴天
```

## 类图

![alt text](/src/main/resources/puml/uml/observer.png "Observer")

## 时序图

![alt text](/src/main/resources/puml/puml/Observer.png)

## 实现方式

* 仔细检查你的业务逻辑， 试着将其拆分为两个部分： 独立于**其他代码的核心功能将作为发布者**； **其他代码则将转化为一组订阅类**。
* 声明订阅者接口。 **该接口至少应声明一个 update方法**。
* **声明发布者接口并定义一些接口来在列表中添加和删除订阅对象**。 记住发布者必须仅通过订阅者接口与它们进行交互。
* 确定存放实际订阅列表的位置并实现订阅方法。 通常所有类型的发布者代码看上去都一样， 因此将列表放置在直接扩展自发布者接口的抽象类中是显而易见的。 具体发布者会扩展该类从而继承所有的订阅行为。
  * 但是， 如果你需要在现有的类层次结构中应用该模式， 则可以考虑使用组合的方式： 将订阅逻辑放入一个独立的对象， 然后让所有实际订阅者使用该对象。
* 创建具体发布者类。 每次发布者发生了重要事件时都必须通知所有的订阅者。
* 在具体订阅者类中实现通知更新的方法。 绝大部分订阅者需要一些与事件相关的上下文数据。 这些数据可作为通知方法的参数来传递。
  * 但还有另一种选择。 订阅者接收到通知后直接从通知中获取所有数据。 在这种情况下， 发布者必须通过更新方法将自身传递出去。 另一种不太灵活的方式是通过构造函数将发布者与订阅者永久性地连接起来。
* **客户端必须生成所需的全部订阅者， 并在相应的发布者处完成注册工作**。

## 适用场景

* 当一个对象状态的改变需要改变其他对象， 或实际对象是事先未知的或动态变化的时， 可使用观察者模式。
  * 当你使用图形用户界面类时通常会遇到一个问题。 比如， 你创建了自定义按钮类并允许客户端在按钮中注入自定义代码， 这样当用户按下按钮时就会触发这些代码。
  > 观察者模式允许任何实现了订阅者接口的对象订阅发布者对象的事件通知。 你可在按钮中添加订阅机制， 允许客户端通过自定义订阅类注入自定义代码。
* 当应用中的一些对象必须观察其他对象时， 可使用该模式。 但仅能在有限时间内或特定情况下使用。
  * 订阅列表是动态的， 因此订阅者可随时加入或离开该列表。

**优点**

* 开闭原则。 你无需修改发布者代码就能引入新的订阅者类 （如果是发布者接口则可轻松引入发布者类）。
* 你可以在运行时建立对象之间的联系。

**缺点**

* 订阅者的通知顺序是随机的。

## 与其他模式的关系

* [责任链模式](Chain)、 [命令模式](Command)、 [中介者模式](Mediator)和[观察者模式](Observer)用于处理请求发送者和接收者之间的不同连接方式：
  * [责任链](Chain)**按照顺序**将请求**动态传递**给一系列的潜在接收者， 直至其中一名接收者对请求进行处理。
  * [命令](Command)在发送者和请求者之间建立单向连接。一对一.
  * [中介者](Mediator)**屏蔽**了发送者和请求者之间的直接连接， 强制它们通过一个中介对象进行间接沟通。
  * [观察者](Observer)允许接收者**动态地订阅**或**取消**接收请求。
* 中[介者](Mediator)和[观察者](Observer)之间的区别往往很难记住。 在大部分情况下， 你可以使用其中一种模式， 而有时可以同时使用。 让我们来看看如何做到这一点。
  * **中介者的主要目标是消除一系列系统组件之间的相互依赖**。 这些组件将依赖于同一个中介者对象。 **观察者的目标是在对象之间建立动态的单向连接**， 使得部分对象可作为其他对象的附属发挥作用。
  * 有一种流行的中介者模式实现方式依赖于观察者。 中介者对象担当发布者的角色， 其他组件则作为订阅者， 可以订阅中介者的事件或取消订阅。 当中介者以这种方式实现时， 它可能看上去与观察者非常相似。
  * 当你感到疑惑时， 记住可以采用其他方式来实现中介者。 例如， 你可永久性地将所有组件链接到同一个中介者对象。 这种实现方式和观察者并不相同， 但这仍是一种中介者模式。
  * 假设有一个程序， 其所有的组件都变成了发布者， 它们之间可以相互建立动态连接。 这样程序中就没有中心化的中介者对象， 而只有一些分布式的观察者。

## 典型用例

* 一个对象的改变导致其他对象的改变

## Java中的例子

* [java.util.Observer](http://docs.oracle.com/javase/8/docs/api/java/util/Observer.html)
* [java.util.EventListener](http://docs.oracle.com/javase/8/docs/api/java/util/EventListener.html)
* [javax.servlet.http.HttpSessionBindingListener](http://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSessionBindingListener.html)
* [RxJava](https://github.com/ReactiveX/RxJava)

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Java Generics and Collections](https://www.amazon.com/gp/product/0596527756/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596527756&linkCode=as2&tag=javadesignpat-20&linkId=246e5e2c26fe1c3ada6a70b15afcb195)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)
