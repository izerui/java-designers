package Prototype;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 兽人军队.
 */
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class OrcWarlord extends Warlord {

  private final String weapon;

  public OrcWarlord(OrcWarlord orcWarlord) {
    super(orcWarlord);
    this.weapon = orcWarlord.weapon;
  }

  @Override
  public OrcWarlord copy() {
    return new OrcWarlord(this);
  }

  @Override
  public String toString() {
    return "兽人军队攻击: " + weapon;
  }

}
