package Template;

import lombok.extern.slf4j.Slf4j;

/**
 * 偷窃方法抽象类
 */
@Slf4j
public abstract class StealingMethod {

  // 选择目标
  protected abstract String pickTarget();

  // 迷惑目标
  protected abstract void confuseTarget(String target);

  // 偷
  protected abstract void stealTheItem(String target);

  /**
   * 偷东西
   */
  public void steal() {
    String target = pickTarget();
    LOGGER.info("目标锁定 {}", target);
    confuseTarget(target);
    stealTheItem(target);
  }
}
