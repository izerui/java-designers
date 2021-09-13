package Facade;

/**
 * 外观模式为一个复杂的子系统提供一个简单的接口。
 *
 * <p>在这个例子中，({@link DwarvenGoldmineFacade})是一个外观类，它为挖金矿的复杂动作提供了一个更简单的接口。
 */
public class App {


  public static void main(String[] args) {
    DwarvenGoldmineFacade facade = new DwarvenGoldmineFacade();
    facade.startNewDay();
    facade.digOutGold();
    facade.endDay();
  }
}
