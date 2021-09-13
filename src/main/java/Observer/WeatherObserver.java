package Observer;

/**
 * 天气观察者基类
 */
public interface WeatherObserver {

  // 更新当前天气
  void update(Observer.WeatherType currentWeather);

}
