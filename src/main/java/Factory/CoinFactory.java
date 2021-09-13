
package Factory;

/**
 * 硬币工厂
 */
public class CoinFactory {

  /**
   * 工厂方法通过 [硬币类型] 参数返回适当的硬币对象.
   */
  public static Coin getCoin(CoinType type) {
    return type.getConstructor().get();
  }
}
