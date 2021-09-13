package Facade;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 矮人工作者抽象基类
 */
@Slf4j
public abstract class DwarvenMineWorker {

  public void goToSleep() {
    LOGGER.info("{} 去睡觉.", name());
  }

  public void wakeUp() {
    LOGGER.info("{} 醒来了.", name());
  }

  public void goHome() {
    LOGGER.info("{} 回家.", name());
  }

  public void goToMine() {
    LOGGER.info("{} 去矿井.", name());
  }

  private void action(Action action) {
    switch (action) {
      case GO_TO_SLEEP:
        goToSleep();
        break;
      case WAKE_UP:
        wakeUp();
        break;
      case GO_HOME:
        goHome();
        break;
      case GO_TO_MINE:
        goToMine();
        break;
      case WORK:
        work();
        break;
      default:
        LOGGER.info("Undefined action");
        break;
    }
  }

  /**
   * Perform actions.
   */
  public void action(Action... actions) {
    Arrays.stream(actions).forEach(this::action);
  }

  public abstract void work();

  public abstract String name();

  enum Action {
    GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
  }
}
