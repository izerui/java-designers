package Abstract;

import lombok.extern.slf4j.Slf4j;

/**
 * 提供一个用于创建相关对象家族的接口，而无需指定其具体类。
 *
 * <p>在此示例中，我们创建了`FactoryMaker`，负责返回`ElfKingdomFactory`或`OrcKingdomFactory`的实例。
 * 客户可以使用`FactoryMaker`来创建所需的具体工厂，该工厂随后将生产不同的具体对象（军队，国王，城堡）。
 * 在此示例中，我们还使用了一个枚举来参数化客户要求的王国工厂类型。（这里使用了[工厂方法模式](Factory)）
 */
@Slf4j
public class App implements Runnable {

  private final Kingdom kingdom = new Kingdom();

  public static void main(String[] args) {
    App app = new App();
    app.run();
  }

  @Override
  public void run() {
    LOGGER.info("精灵王国.");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());

    LOGGER.info("兽人王国.");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());
  }

  /**
   * Creates kingdom.
   * @param kingdomType type of Kingdom
   */
  public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
    final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
    kingdom.setKing(kingdomFactory.createKing());
    kingdom.setCastle(kingdomFactory.createCastle());
    kingdom.setArmy(kingdomFactory.createArmy());
  }
}
