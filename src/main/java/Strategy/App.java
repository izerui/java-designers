package Strategy;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * <p>策略模式（也称为策略模式）是一种软件设计模式，可以在运行时选择算法的行为。</p>
 *
 * <p>在 Java 8 之前，Strategies 需要是单独的类，迫使开发人员编写大量样板代码。
 * 使用现代 Java，可以轻松地通过方法引用和 lambda 传递行为，从而使代码更短且更具可读性。</p>
 *
 * <p>在这个例子中 ({@link DragonSlayingStrategy})屠龙策略封装了一个算法。 ({@link DragonSlayer}) 屠龙者可以通过改变其策略来改变其行为。</p>
 *
 */
@Slf4j
public class App {


  public static void main(String[] args) {
    // GoF Strategy pattern
    LOGGER.info("前方发现绿龙");
    DragonSlayer dragonSlayer = new DragonSlayer(new MeleeStrategy());
    dragonSlayer.goToBattle();
    LOGGER.info("红龙现身");
    dragonSlayer.changeStrategy(new ProjectileStrategy());
    dragonSlayer.goToBattle();
    LOGGER.info("黑龙在你面前降落");
    dragonSlayer.changeStrategy(new SpellStrategy());
    dragonSlayer.goToBattle();

    // Java 8 functional implementation Strategy pattern
    LOGGER.info("前方发现绿龙");
    dragonSlayer = new DragonSlayer(
        () -> LOGGER.info("你用神剑斩断了龙的头颅"));
    dragonSlayer.goToBattle();
    LOGGER.info("红龙现身");
    dragonSlayer.changeStrategy(() -> LOGGER.info(
        "你用魔法弩射龙，它倒在地上死了！"));
    dragonSlayer.goToBattle();
    LOGGER.info("黑龙在你面前降落");
    dragonSlayer.changeStrategy(() -> LOGGER.info(
        "你施展了瓦解咒语，巨龙在一堆尘土中蒸发！"));
    dragonSlayer.goToBattle();

    // Java 8 lambda implementation with enum Strategy pattern
    LOGGER.info("前方发现绿龙");
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.MeleeStrategy);
    dragonSlayer.goToBattle();
    LOGGER.info("红龙现身。");
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.ProjectileStrategy);
    dragonSlayer.goToBattle();
    LOGGER.info("黑龙在你面前降落。");
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.SpellStrategy);
    dragonSlayer.goToBattle();
  }
}
