package Adapter;

/**
 * 适配器类。将设备接口（{@link FishingBoat}）适配为客户端（{@link Captain}）所期望的{@link RowingBoat}接口。
 */
public class FishingBoatAdapter implements RowingBoat {

  // 渔船
  private final FishingBoat boat = new FishingBoat();

  public final void row() {
    boat.sail();
  }
}
