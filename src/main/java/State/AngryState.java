package State;

import lombok.extern.slf4j.Slf4j;

/**
 * 愤怒状态
 */
@Slf4j
public class AngryState implements State {

  private final Mammoth mammoth;

  // 构造一个长毛象到当前状态
  public AngryState(Mammoth mammoth) {
    this.mammoth = mammoth;
  }

  // 观察它是狂怒的
  @Override
  public void observe() {
    LOGGER.info("{} 很生气!", mammoth);
  }

  // 长毛象开始生气了...
  @Override
  public void onEnterState() {
    LOGGER.info("{} 生气了!", mammoth);
  }

}
