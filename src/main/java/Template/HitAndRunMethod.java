package Template;

import lombok.extern.slf4j.Slf4j;

/**
 * 运动中直接偷的手法
 */
@Slf4j
public class HitAndRunMethod extends StealingMethod {

  @Override
  protected String pickTarget() {
    return "老女人";
  }

  @Override
  protected void confuseTarget(String target) {
    LOGGER.info("从后面接近 {}。", target);
  }

  @Override
  protected void stealTheItem(String target) {
    LOGGER.info("拿起手提包，快速逃跑！");
  }
}
