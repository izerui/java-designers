package Prototype;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 兽人.
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class OrcBeast extends Beast {

  private final String weapon;

  public OrcBeast(OrcBeast orcBeast) {
    super(orcBeast);
    this.weapon = orcBeast.weapon;
  }

  @Override
  public OrcBeast copy() {
    return new OrcBeast(this);
  }

  @Override
  public String toString() {
    return "兽人开始攻击,使用武器: " + weapon;
  }

}
