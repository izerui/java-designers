package Prototype;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 法师.
 */
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Mage implements Prototype {

  public Mage(Mage source) {
  }

  @Override
  public abstract Mage copy();

}
