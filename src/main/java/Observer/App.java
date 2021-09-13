package Observer;

import Observer.generic.GHobbits;
import Observer.generic.GOrcs;
import Observer.generic.GWeather;
import Observer.generic.Observable;
import lombok.extern.slf4j.Slf4j;

/**
 * 观察者模式是一种软件设计模式，其中称为主体的对象维护其依赖者列表，称为观察者，并通常通过调用它们的方法之一来自动通知它们任何状态更改。
 * 它主要用于实现分布式事件处理系统。观察者模式也是熟悉的模型-视图-控制器 (MVC) 架构模式中的关键部分。观察者模式在许多编程库和系统中实现，
 * 包括几乎所有的 GUI 工具包。
 *
 * <p>在这个例子中，{@link Observer.Weather} 有一个可以被观察到的状态。 {@link Observer.Orcs} 和 {@link Observer.Hobbits}
 * 注册为观察者并在 {@link Observer.Weather} 更改时收到通知。
 */
@Slf4j
public class App {


  public static void main(String[] args) {

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
  }
}
