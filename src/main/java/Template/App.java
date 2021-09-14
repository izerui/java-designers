package Template;

/**
 * 模板方法定义了算法的骨架。算法子类提供空白部分的实现。
 *
 * <p>在此示例中，{@link HalflingThief} 小偷包含可以更改的 {@link StealingMethod}偷窃手法。
 * 首先小偷用 {@link HitAndRunMethod} 偷东西，然后改用 {@link SubtleMethod} 偷东西。
 */
public class App {


  public static void main(String[] args) {
    HalflingThief thief = new HalflingThief(new HitAndRunMethod());
    thief.steal();
    thief.changeMethod(new SubtleMethod());
    thief.steal();
  }
}
