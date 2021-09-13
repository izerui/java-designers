package Bridge;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * 桥接模式是一个更推荐组合而不是继承的模式。将实现细节从一个层次结构推送到具有单独层次结构的另一个对象。
 *
 * <p>在桥接模式中，抽象（{@link Weapon}）武器和实现（{@link Enchantment}）附魔都有自己的类层次结构。可以在不影响客户端的情况下更改实现的接口。
 *
 * <p>在这个例子中，我们有两个类层次结构。一种武器，另一种附魔。我们可以使用组合轻松地将任何武器与任何附魔结合起来，而不是创建很深的类层次结构。
 */
@Slf4j
public class App {


  public static void main(String[] args) {
    LOGGER.info("骑士收到一个附魔了的剑.");
    var enchantedSword = new Sword(new SoulEatingEnchantment());
    enchantedSword.wield();
    enchantedSword.swing();
    enchantedSword.unwield();

    LOGGER.info("女神收到一个附魔了锤子");
    var hammer = new Hammer(new FlyingEnchantment());
    hammer.wield();
    hammer.swing();
    hammer.unwield();
  }
}
