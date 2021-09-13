package Observer;

import Observer.generic.GHobbits;
import Observer.generic.GOrcs;
import Observer.generic.GWeather;
import Observer.generic.Observable;
import lombok.extern.slf4j.Slf4j;

/**
 * The Observer pattern is a software design pattern in which an object, called the subject,
 * maintains a list of its dependents, called observers, and notifies them automatically of any
 * state changes, usually by calling one of their methods. It is mainly used to implement
 * distributed event handling systems. The Observer pattern is also a key part in the familiar
 * model–view–controller (MVC) architectural pattern. The Observer pattern is implemented in
 * numerous programming libraries and systems, including almost all GUI toolkits.
 *
 * <p>In this example {@link Observer.Weather} has a state that can be observed. The {@link Observer.Orcs} and {@link
 * Observer.Hobbits} register as observers and receive notifications when the {@link Observer.Weather} changes.
 */
@Slf4j
public class App {

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {

    Weather weather = new Observer.Weather();
    weather.addObserver(new Observer.Orcs());
    weather.addObserver(new Observer.Hobbits());

    weather.timePasses();
    weather.timePasses();
    weather.timePasses();
    weather.timePasses();

    // Generic observer inspired by Java Generics and Collections by Naftalin & Wadler
    LOGGER.info("--Running generic version--");
    GWeather genericWeather = new GWeather();
    genericWeather.addObserver(new GOrcs());
    genericWeather.addObserver(new GHobbits());

    genericWeather.timePasses();
    genericWeather.timePasses();
    genericWeather.timePasses();
    genericWeather.timePasses();
  }
}
