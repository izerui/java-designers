package Observer.generic;

import Observer.WeatherType;
import lombok.extern.slf4j.Slf4j;

/**
 * GWeather.
 */
@Slf4j
public class GWeather extends Observable<GWeather, Race, WeatherType> {

  private WeatherType currentWeather;

  public GWeather() {
    currentWeather = WeatherType.SUNNY;
  }

  /**
   * Makes time pass for weather.
   */
  public void timePasses() {
    WeatherType[] enumValues = WeatherType.values();
    currentWeather = enumValues[(currentWeather.ordinal() + 1) % enumValues.length];
    LOGGER.info("The weather changed to {}.", currentWeather);
    notifyObservers(currentWeather);
  }
}
