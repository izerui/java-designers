package Facade;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 挖金矿的外观类
 */
public class DwarvenGoldmineFacade {

  // 矿工们
  private final List<DwarvenMineWorker> workers;

  /**
   * 通过构造器初始化相关的必要条件. 比如: 矿工种类及人数
   */
  public DwarvenGoldmineFacade() {
    workers = Arrays.asList(
        new DwarvenGoldDigger(), // 淘金矮人
        new DwarvenCartOperator(), // 运输矮人
        new DwarvenTunnelDigger()); // 挖掘矮人
  }

  // 开始新的一天
  public void startNewDay() {
    makeActions(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE); // 起床,去挖矿
  }

  // 挖矿
  public void digOutGold() {
    makeActions(workers, DwarvenMineWorker.Action.WORK); // 工作
  }

  // 一天结束
  public void endDay() {
    makeActions(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.GO_TO_SLEEP); // 回家,睡觉
  }

  // 抽取出来的复杂外观控制逻辑
  private static void makeActions(
      Collection<DwarvenMineWorker> workers,
      DwarvenMineWorker.Action... actions
  ) {
    workers.forEach(worker -> worker.action(actions));
  }
}
