package Abstract;

/**
 * 兽人领袖.
 */
public class OrcKing implements King {

  static final String DESCRIPTION = "这是兽人领袖";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
