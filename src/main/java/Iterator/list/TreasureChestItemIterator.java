package Iterator.list;

import Iterator.Iterator;

import java.util.List;

/**
 * 百宝箱迭代器
 */
public class TreasureChestItemIterator implements Iterator<Item> {

  private final TreasureChest chest;
  private int idx;
  private final ItemType type;

  /**
   * Constructor.
   */
  public TreasureChestItemIterator(TreasureChest chest, ItemType type) {
    this.chest = chest;
    this.type = type;
    this.idx = -1;
  }

  @Override
  public boolean hasNext() {
    return findNextIdx() != -1;
  }

  @Override
  public Item next() {
    idx = findNextIdx();
    if (idx != -1) {
      return chest.getItems().get(idx);
    }
    return null;
  }

  private int findNextIdx() {
    List<Item> items = chest.getItems();
    int tempIdx = idx;
    while (true) {
      tempIdx++;
      if (tempIdx >= items.size()) {
        tempIdx = -1;
        break;
      }
      if (type.equals(ItemType.ANY) || items.get(tempIdx).getType().equals(type)) {
        break;
      }
    }
    return tempIdx;
  }
}
