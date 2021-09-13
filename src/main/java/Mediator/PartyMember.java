package Mediator;

/**
 * 定义了一些成员跟部队交互的方法 {@link Party}.
 */
public interface PartyMember {

  // 加入部队
  void joinedParty(Party party);

  // 执行部队下达的命令
  void partyAction(Action action);

  // 下发命令给自己所属的部队
  void act(Action action);
}
