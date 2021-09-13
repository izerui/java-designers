package Decorator;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * 装饰者模式让你可以在运行时通过把对象包装进一个装饰类对象中来动态的改变一个对象的行为。继承相同的接口
 *
 * <p>本示例: 附近的山丘上住着一个愤怒的巨魔。通常它是徒手的，但有时它有武器。为了武装巨魔不必创建新的巨魔，而是用合适的武器动态的装饰它。
 */
@Slf4j
public class App {


  public static void main(String[] args) {

    // 徒手巨魔
    LOGGER.info("一个赤脚双全的巨魔靠近了.");
    var troll = new SimpleTroll();
    troll.attack();
    troll.fleeBattle();
    LOGGER.info("徒手巨魔的力量: {}.\n", troll.getAttackPower());

    // 通过使用装饰器来武装巨魔,改变巨魔的行为
    LOGGER.info("一个武装巨魔靠近了.");
    var clubbedTroll = new ClubbedTroll(troll);
    clubbedTroll.attack();
    clubbedTroll.fleeBattle();
    LOGGER.info("武装巨魔的力量: {}.\n", clubbedTroll.getAttackPower());
  }
}
