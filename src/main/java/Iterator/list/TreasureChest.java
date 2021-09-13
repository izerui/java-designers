package Iterator.list;

import Iterator.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 百宝箱
 */
public class TreasureChest {

  private final List<Item> items;

  /**
   * Constructor.
   */
  public TreasureChest() {
    items = Arrays.asList(
        new Item(ItemType.POTION, "勇气药水"),
        new Item(ItemType.RING, "暗影戒指"),
        new Item(ItemType.POTION, "智慧药水"),
        new Item(ItemType.POTION, "嗜血药水"),
        new Item(ItemType.WEAPON, "银剑 +1"),
        new Item(ItemType.POTION, "铁锈药水"),
        new Item(ItemType.POTION, "治疗药水"),
        new Item(ItemType.RING, "盔甲之戒"),
        new Item(ItemType.WEAPON, "钢戟"),
        new Item(ItemType.WEAPON, "毒之匕首"));
  }

  public Iterator<Item> iterator(ItemType itemType) {
    return new TreasureChestItemIterator(this, itemType);
  }

  /**
   * Get all items.
   */
  public List<Item> getItems() {
    return new ArrayList<>(items);
  }

}
