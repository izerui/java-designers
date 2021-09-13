package Prototype;

import lombok.RequiredArgsConstructor;

/**
 * Concrete factory class.
 */
@RequiredArgsConstructor
public class HeroFactoryImpl implements HeroFactory {

  private final Mage mage;
  private final Warlord warlord;
  private final Beast beast;

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
