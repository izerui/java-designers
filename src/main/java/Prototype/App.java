package Prototype;

import lombok.extern.slf4j.Slf4j;

/**
 * 原型模式是用于创建重复的对象，提高性能。这种模式实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。
 *
 * <p>我们有一个HeroFactory工厂，这个工厂可以生产不同类型的（兽族、精灵族）Mage(魔法师)、Warlord(军队)、
 * Beast(兽王)，只是这些类型的产物在工厂中都不是直接New出来的，而是采用原型模式通过已有类型对象clone出来的。
 */
@Slf4j
public class App {


  public static void main(String[] args) {
    HeroFactory factory = new HeroFactoryImpl(
            new ElfMage("做饭"),
            new ElfWarlord("打扫"),
            new ElfBeast("站岗")
    );
    Mage mage = factory.createMage();
    Warlord warlord = factory.createWarlord();
    Beast beast = factory.createBeast();
    LOGGER.info(mage.toString());
    LOGGER.info(warlord.toString());
    LOGGER.info(beast.toString());

    factory = new HeroFactoryImpl(
        new OrcMage("锤"),
        new OrcWarlord("剑"),
        new OrcBeast("激光")
    );
    mage = factory.createMage();
    warlord = factory.createWarlord();
    beast = factory.createBeast();
    LOGGER.info(mage.toString());
    LOGGER.info(warlord.toString());
    LOGGER.info(beast.toString());
  }
}
