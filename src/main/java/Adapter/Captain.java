package Adapter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 船长 - 客户端
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Captain {

  // 划艇技能
  private RowingBoat rowingBoat;

  // 通过划艇技能开动船只
  void row() {
    rowingBoat.row();
  }

}
