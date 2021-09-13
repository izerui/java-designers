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
    LOGGER.info("霍比特人发现现在天气是: " + weatherType.getDescription());
  }
}
