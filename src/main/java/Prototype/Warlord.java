package Prototype;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 军队抽象.
 */
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Warlord implements Prototype {

  public Warlord(Warlord source) {
  }

  @Override
  public abstract Warlord copy();

}
