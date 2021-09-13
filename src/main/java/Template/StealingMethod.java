package Template;

import lombok.extern.slf4j.Slf4j;

/**
 * StealingMethod defines skeleton for the algorithm.
 */
@Slf4j
public abstract class StealingMethod {

  protected abstract String pickTarget();

  protected abstract void confuseTarget(String target);

  protected abstract void stealTheItem(String target);

  /**
   * Steal.
   */
  public void steal() {
    String target = pickTarget();
    LOGGER.info("The target has been chosen as {}.", target);
    confuseTarget(target);
    stealTheItem(target);
  }
}
