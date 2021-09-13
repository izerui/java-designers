package Adapter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Captain uses {@link RowingBoat} to sail. <br> This is the client in the pattern.
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Captain {

  private RowingBoat rowingBoat;

  void row() {
    rowingBoat.row();
  }

}
