package Prototype;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 精灵法师.
 */
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ElfMage extends Mage {

  private final String helpType;

  public ElfMage(ElfMage elfMage) {
    super(elfMage);
    this.helpType = elfMage.helpType;
  }

  @Override
  public ElfMage copy() {
    return new ElfMage(this);
  }

  @Override
  public String toString() {
    return "精灵法师来帮忙: " + helpType;
  }

}
