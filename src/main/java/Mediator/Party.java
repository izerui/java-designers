package Mediator;

/**
 * 中介者接口: 部队接口,负责跟成员交互的一些方法
 */
public interface Party {

  // 添加成员
  void addMember(PartyMember member);

  // 把命令传递给其他人
  void act(PartyMember actor, Action action);

}
