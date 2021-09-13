package State;

/**
 * 长毛象 具有定义其行为的内部状态。
 */
public class Mammoth {

  private State state;

  // 默认是平静状态
  public Mammoth() {
    state = new PeacefulState(this);
  }

  /**
   * 状态切换
   */
  public void timePasses() {
    if (state.getClass().equals(PeacefulState.class)) {
      changeStateTo(new AngryState(this));
    } else {
      changeStateTo(new PeacefulState(this));
    }
  }

  private void changeStateTo(State newState) {
    this.state = newState;
    this.state.onEnterState();
  }

  @Override
  public String toString() {
    return "长毛象";
  }

  public void observe() {
    this.state.observe();
  }
}
