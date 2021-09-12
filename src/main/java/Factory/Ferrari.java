package Factory;

/**
 * 法拉利
 */
public class Ferrari implements Car {
   
  static final String DESCRIPTION = "这是法拉利";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}