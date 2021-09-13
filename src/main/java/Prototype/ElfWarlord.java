package Prototype;

import lombok.EqualsAndHashCode;

/**
 * 精灵军队.
 */
@EqualsAndHashCode
public class ElfWarlord extends Warlord {

  private final String helpType;

  public ElfWarlord(String helpType) {
    this.helpType = helpType;
  }

  public ElfWarlord(ElfWarlord elfWarlord) {
    super(elfWarlord);
    this.helpType = elfWarlord.helpType;
  }

  @Override
  public ElfWarlord copy() {
    return new ElfWarlord(this);
  }

  @Override
  public String toString() {
    return "精灵军队来帮忙:  " + helpType;
  }
}
