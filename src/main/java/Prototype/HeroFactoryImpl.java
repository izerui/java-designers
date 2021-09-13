package Prototype;

import lombok.RequiredArgsConstructor;

/**
 * 工厂实现类
 */
@RequiredArgsConstructor
public class HeroFactoryImpl implements HeroFactory {

  private final Mage mage; // 法师
  private final Warlord warlord; // 军队
  private final Beast beast; // 兽人

  /**
   * Create mage.
   */
  public Mage createMage() {
    return mage.copy();
  }

  /**
   * Create warlord.
   */
  public Warlord createWarlord() {
    return warlord.copy();
  }

  /**
   * Create beast.
   */
  public Beast createBeast() {
    return beast.copy();
  }

}
