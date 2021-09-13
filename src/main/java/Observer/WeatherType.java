package Observer;

/**
 * WeatherType enumeration.
 */
public enum WeatherType {

  SUNNY("晴天"),
  RAINY("雨天"),
  WINDY("狂风天气"),
  COLD("冬天");

  private final String description;

  WeatherType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
