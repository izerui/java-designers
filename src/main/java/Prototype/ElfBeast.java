package Prototype;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 精灵鹰.
 */
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ElfBeast extends Beast {

  private final String helpType;

  public ElfBeast(ElfBeast elfBeast) {
    super(elfBeast);
    this.helpType = elfBeast.helpType;
  }

  @Override
  public ElfBeast copy() {
    return new ElfBeast(this);
  }

  @Override
  public String toString() {
    return "精灵鹰来帮忙: " + helpType;
  }

}
