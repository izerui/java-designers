package Template;

import lombok.extern.slf4j.Slf4j;

/**
 * 贴身偷窃手法
 */
@Slf4j
public class SubtleMethod extends StealingMethod {

  @Override
  protected String pickTarget() {
    return "店主";
  }

  @Override
  protected void confuseTarget(String target) {
    LOGGER.info("流着眼泪走近 {} 并拥抱他！", target);
  }

  @Override
  protected void stealTheItem(String target) {
    LOGGER.info("在密切接触时偷走 {} 的钱包。", target);
  }
}
