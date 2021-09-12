package Abstract;

// 兽王
public class OrcKing implements King {
  static final String DESCRIPTION = "这是兽人国王！";
  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}