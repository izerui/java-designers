package Prototype;

/**
 * 工厂接口类
 */
public interface HeroFactory {

  // 创造法师
  Mage createMage();

  // 创造军队
  Warlord createWarlord();

  // 创造兽人
  Beast createBeast();

}
