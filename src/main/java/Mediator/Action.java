package Mediator;

/**
 * 动作枚举
 */
public enum Action {
  HUNT("猎杀了一只兔子", "来吃晚饭"),
  TALE("讲故事", "过来听"),
  GOLD("发现金子", "拿走属于自己的那份金子"),
  ENEMY("发现敌人", "跑去掩护"),
  NONE("", "");

  private final String title;
  private final String description;

  Action(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String toString() {
    return title;
  }
}
