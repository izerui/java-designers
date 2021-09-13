package Mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * 成员基础抽象类
 */
@Slf4j
public abstract class PartyMemberBase implements PartyMember {

  // 部队
  protected Party party;

  // 加入一个部队
  @Override
  public void joinedParty(Party party) {
    LOGGER.info("{} 加入部队", this);
    this.party = party;
  }

  // 执行部队下达的命令
  @Override
  public void partyAction(Action action) {
    LOGGER.info("{} {}", this, action.getDescription());
  }

  // 下发命令给自己所属的部队
  @Override
  public void act(Action action) {
    if (party != null) {
      LOGGER.info("{} {}", this, action);
      party.act(this, action);
    }
  }

  @Override
  public abstract String toString();

}
