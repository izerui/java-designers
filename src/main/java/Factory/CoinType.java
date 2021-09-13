
package Factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * 不同的硬币类型枚举.
 */
@RequiredArgsConstructor
@Getter
public enum CoinType {

  COPPER(CopperCoin::new),
  GOLD(GoldCoin::new);

  /**
   * @FunctionalInterface 函数式接口变量,方便构造硬币对象
   */
  private final Supplier<Coin> constructor;
}
