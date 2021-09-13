package Observer;

import lombok.extern.slf4j.Slf4j;

/**
 * Orcs.
 */
@Slf4j
public class Orcs implements WeatherObserver {

  @Override
  public void update(WeatherType currentWeather) {
    LOGGER.info("The orcs are facing " + currentWeather.getDescription() + " weather now");
  }
}
