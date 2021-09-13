package State;

import lombok.extern.slf4j.Slf4j;

/**
 * 平静状态
 */
@Slf4j
public class PeacefulState implements State {

  private final Mammoth mammoth;

  // 构造一个长毛象到当前状态
  public PeacefulState(Mammoth mammoth) {
    this.mammoth = mammoth;
  }

  // 观察它是平静的
  @Override
  public void observe() {
    LOGGER.info("{} 是平静的.", mammoth);
  }

  // 进入平静状态,长毛象冷静下来
  @Override
  public void onEnterState() {
    LOGGER.info("{} 冷静下来.", mammoth);
  }

}
