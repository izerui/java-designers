package Mediator;

import lombok.var;

/**
 * 中介者: 定义一个封装一组对象如何交互的对象。调解器通过防止对象明确地相互引用来促进松散耦合，并且它允许您独立地改变它们的交互。
 *
 * <p>在此示例中，中介器封装了一组对象 ({@link PartyMember}) 如何交互。他们使用中介（{@link Party}）接口，而不是直接相互引用。
 */
public class App {


  public static void main(String[] args) {

    // 创建部队和成员
    Party party = new PartyImpl();
    var hobbit = new Hobbit();
    var wizard = new Wizard();
    var rogue = new Rogue();
    var hunter = new Hunter();

    // 部队添加成员
    party.addMember(hobbit);
    party.addMember(wizard);
    party.addMember(rogue);
    party.addMember(hunter);

    // 执行命令 -> 通知其他成员执行命令
    // 由当事人通知
    hobbit.act(Action.ENEMY);
    wizard.act(Action.TALE);
    rogue.act(Action.GOLD);
    hunter.act(Action.HUNT);
  }
}
