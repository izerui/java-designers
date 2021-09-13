package Builder;

import lombok.AllArgsConstructor;

/**
 * 盔甲枚举类
 */
@AllArgsConstructor
public enum Armor {

  CLOTHES("布甲"),
  LEATHER("皮甲"),
  CHAIN_MAIL("锁甲"),
  PLATE_MAIL("板甲");

  private final String title;

  @Override
  public String toString() {
    return title;
  }
}
