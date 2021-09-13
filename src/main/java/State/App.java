package State;

/**
 * 在状态模式中，容器对象有一个定义当前行为的内部状态对象。可以更改状态对象以改变行为。
 *
 * <p>这可以是对象在运行时更改其行为的一种更简洁的方式，而无需求助于大型单片条件语句，从而提高可维护性。
 *
 * <p>在这个例子中，{@link Mammoth} 随着时间的推移改变了它的行为。
 */
public class App {

  /**
   * Program entry point.
   */
  public static void main(String[] args) {

    Mammoth mammoth = new Mammoth();
    mammoth.observe();
    mammoth.timePasses();
    mammoth.observe();
    mammoth.timePasses();
    mammoth.observe();

  }
}
