package Bridge;

/**
 * 武器基类.
 */
public interface Weapon {

  void wield();

  void swing();

  void unwield();

  // 所有武器都具备获取其附魔属性的能力
  Enchantment getEnchantment();
}
