package Prototype;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 兽人法师.
 */
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class OrcMage extends Mage {

  private final String weapon;

  public OrcMage(OrcMage orcMage) {
    super(orcMage);
    this.weapon = orcMage.weapon;
  }

  @Override
  public OrcMage copy() {
    return new OrcMage(this);
  }

  @Override
  public String toString() {
    return "兽人法师开始攻击,使用武器: " + weapon;
  }

}
