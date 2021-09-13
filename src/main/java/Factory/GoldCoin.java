
package Factory;

/**
 * 金币实现.
 */
public class GoldCoin implements Coin {

  static final String DESCRIPTION = "这是一枚金币.";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
