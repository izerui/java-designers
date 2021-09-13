package State;

/**
 * 状态基类
 */
public interface State {

   // 进入状态
  void onEnterState();

  // 观察
  void observe();
}
