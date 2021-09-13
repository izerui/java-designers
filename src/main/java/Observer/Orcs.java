package Observer;

import lombok.extern.slf4j.Slf4j;

/**
 * 兽人实现了观察天气基类
 */
@Slf4j
public class Orcs implements WeatherObserver {

  @Override
  public void update(WeatherType currentWeather) {
    LOGGER.info("兽人发现现在天气是: " + currentWeather.getDescription());
  }
}
