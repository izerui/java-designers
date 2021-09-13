package Adapter;

import lombok.var;

/**
 * 将一个接口转换成另一个客户所期望的接口。适配器让那些本来因为接口不兼容的类可以合作无间。
 *
 * 适配器有两种实现方式:
 * 1. 类适配器: 实现适配器的接口
 * 2. 对象适配器: 通过组合方式将适配器包含在适配器对象中
 *
 * <p> 这个故事是这样的: 假设海盗来了，我们的船长需要逃跑，但是只有渔船可用。我们需要创造一个适配器，让船长能够用他的划艇技能操作渔船
 */
public final class App {

  private App() {
  }


  public static void main(final String[] args) {
    var captain = new Captain(new FishingBoatAdapter());
    captain.row();
  }
}
