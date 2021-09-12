package Abstract;

import Abstract.Castle;

// 精灵城堡
public class ElfCastle implements Castle {
  static final String DESCRIPTION = "这是精灵城堡！";
  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}