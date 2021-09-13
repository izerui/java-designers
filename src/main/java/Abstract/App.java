package Abstract;

import lombok.extern.slf4j.Slf4j;

/**
 * 提供一个用于创建相关对象家族的接口，而无需指定其具体类。
 *
 * 例子: 要创建一个王国，我们需要具有共同主题的对象。 精灵王国需要精灵王，精灵城堡和精灵军队，而兽人王国需要兽王，精灵城堡和兽人军队。 王国中的对象之间存在依赖性。
 */
@Slf4j
public class App implements Runnable {

  private final Kingdom kingdom = new Kingdom();

  public Kingdom getKingdom() {
    return kingdom;
  }


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
