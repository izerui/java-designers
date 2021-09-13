package Observer.generic;

import Observer.WeatherType;
import lombok.extern.slf4j.Slf4j;

/**
 * GHobbits.
 */
@Slf4j
public class GHobbits implements Race {

  @Override
  public void update(GWeather weather, WeatherType weatherType) {
    LOGGER.info("The hobbits are facing " + weatherType.getDescription() + " weather now");
  }
}
