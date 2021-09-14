package Template;

/**
 * 贼
 */
public class HalflingThief {

  // 偷窃方法
  private StealingMethod method;

  public HalflingThief(StealingMethod method) {
    this.method = method;
  }

  // 偷窃
  public void steal() {
    method.steal();
  }

  // 改变手法
  public void changeMethod(StealingMethod method) {
    this.method = method;
  }
}
