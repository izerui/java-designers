package Abstract;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        KingdomFactory factory = new ElfKingdomFactory();
        Castle castle = factory.createCastle();
        King king = factory.createKing();
        Army army = factory.createArmy();
        LOGGER.info(castle.getDescription());
        LOGGER.info(king.getDescription());
        LOGGER.info(army.getDescription());
    }

//    public static void main(String[] args) {
//
//        LOGGER.info("精灵王国");
//        KingdomFactory elfKingdomFactory = FactoryMaker.makeFactory(FactoryMaker.KingdomType.ELF);
//        LOGGER.info(elfKingdomFactory.createArmy().getDescription());
//        LOGGER.info(elfKingdomFactory.createCastle().getDescription());
//        LOGGER.info(elfKingdomFactory.createKing().getDescription());
//
//        LOGGER.info("兽人王国");
//        KingdomFactory orcKingdomFactory = FactoryMaker.makeFactory(FactoryMaker.KingdomType.ORC);
//        LOGGER.info(orcKingdomFactory.createArmy().getDescription());
//        LOGGER.info(orcKingdomFactory.createCastle().getDescription());
//        LOGGER.info(orcKingdomFactory.createKing().getDescription());
//    }

    public static class FactoryMaker {

      public enum KingdomType {
        ELF, ORC
      }

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
