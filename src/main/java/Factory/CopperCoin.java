
package Factory;

/**
 * 铜币实现.
 */
public class CopperCoin implements Coin {

  static final String DESCRIPTION = "这是一枚铜币.";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
