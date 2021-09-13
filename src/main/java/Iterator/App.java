package Iterator;

import Iterator.bst.BstIterator;
import Iterator.bst.TreeNode;
import Iterator.list.Item;
import Iterator.list.ItemType;
import Iterator.list.TreasureChest;
import lombok.extern.slf4j.Slf4j;

import static Iterator.list.ItemType.*;

/**
 * 迭代器模式是一种设计模式，其中迭代器用于遍历容器并访问容器的元素。迭代器模式将算法与容器分离。
 *
 * <p>在此示例中，迭代器 ({@link Iterator}) 在集合 ({@link TreasureChest}) 之上添加了抽象层。这样，集合可以在不影响其客户端的情况下更改其内部实现。
 */
@Slf4j
public class App {

  private static final TreasureChest TREASURE_CHEST = new TreasureChest();

  private static void demonstrateTreasureChestIteratorForType(ItemType itemType) {
    LOGGER.info("------------------------");
    LOGGER.info("宝物类型 " + itemType + ": ");
    Iterator<Item> itemIterator = TREASURE_CHEST.iterator(itemType);
    while (itemIterator.hasNext()) {
      LOGGER.info(itemIterator.next().toString());
    }
  }

  private static void demonstrateBstIterator() {
    LOGGER.info("------------------------");
    LOGGER.info("BST Iterator: ");
    TreeNode<Integer> root = buildIntegerBst();
    BstIterator bstIterator = new BstIterator<>(root);
    while (bstIterator.hasNext()) {
      LOGGER.info("Next node: " + bstIterator.next().getVal());
    }
  }

  private static TreeNode<Integer> buildIntegerBst() {
    TreeNode root = new TreeNode<>(8);

    root.insert(3);
    root.insert(10);
    root.insert(1);
    root.insert(6);
    root.insert(14);
    root.insert(4);
    root.insert(7);
    root.insert(13);

    return root;
  }

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    demonstrateTreasureChestIteratorForType(RING);
    demonstrateTreasureChestIteratorForType(POTION);
    demonstrateTreasureChestIteratorForType(WEAPON);
    demonstrateTreasureChestIteratorForType(ANY);

    demonstrateBstIterator();
  }
}
