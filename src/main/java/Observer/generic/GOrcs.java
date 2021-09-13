package Observer.generic;

import Observer.WeatherType;
import lombok.extern.slf4j.Slf4j;

/**
 * GOrcs.
 */
@Slf4j
public class GOrcs implements Race {

  @Override
  public void update(GWeather weather, WeatherType weatherType) {
    LOGGER.info("兽人发现现在天气是: " + weatherType.getDescription());
  }
}
