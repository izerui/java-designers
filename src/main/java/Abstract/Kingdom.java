package Abstract;

import lombok.Getter;
import lombok.Setter;

/**
 * 王国工具类
 */
@Getter
@Setter
public class Kingdom {

  private King king;
  private Castle castle;
  private Army army;

  /**
   * 王国工厂创建类.
   */
  public static class FactoryMaker {

    /**
     * 不同类型的王国枚举
     */
    public enum KingdomType {
      ELF, ORC
    }

    /**
     * 创建王国工厂的方法: 根据类型创建不同的王国工厂类对象.
     */
    public static KingdomFactory makeFactory(KingdomType type) {
      switch (type) {
        case ELF:
          return new ElfKingdomFactory();
        case ORC:
          return new OrcKingdomFactory();
        default:
          throw new IllegalArgumentException("KingdomType not supported.");
      }
    }
  }
}
