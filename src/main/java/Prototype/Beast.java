package Prototype;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 兽.
 */
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Beast implements Prototype {

  public Beast(Beast source) {
  }

  @Override
  public abstract Beast copy();

}
