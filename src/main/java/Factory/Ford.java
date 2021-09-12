package Factory;

/**
 * 福特
 */
public class Ford implements Car {

  static final String DESCRIPTION = "这是福特。";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}