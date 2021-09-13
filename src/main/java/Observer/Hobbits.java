package Observer;

import lombok.extern.slf4j.Slf4j;

/**
 * 霍比特人实现了观察天气基类
 */
@Slf4j
public class Hobbits implements WeatherObserver {

  @Override
  public void update(WeatherType currentWeather) {
    LOGGER.info("霍比特人发现当前天气是: {}", currentWeather.getDescription());
  }
}
