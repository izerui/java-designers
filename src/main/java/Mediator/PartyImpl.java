package Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者实现类: 部队的实现
 */
public class PartyImpl implements Party {

  // 部队成员
  private final List<PartyMember> members;

  public PartyImpl() {
    members = new ArrayList<>();
  }

  // 把命令传递给其他人
  @Override
  public void act(PartyMember actor, Action action) {
    for (PartyMember member : members) {
      if (!member.equals(actor)) {
        member.partyAction(action);
      }
    }
  }

  // 添加成员
  @Override
  public void addMember(PartyMember member) {
    members.add(member);
    member.joinedParty(this);
  }
}
