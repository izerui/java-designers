package Bridge;

/**
 * 附魔基类.
 */
public interface Enchantment {

  void onActivate(); // 被激活

  void apply(); // 执行

  void onDeactivate(); // 消亡
}
