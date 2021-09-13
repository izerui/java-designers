package Iterator.bst;

import Iterator.Iterator;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/**
 * BST 二叉树迭代器的有序实现。例如，给定一个带有整数值的 BST，期望根据整数的自然顺序（1、2、3...）检索树节点
 *
 * @param <T> This Iterator has been implemented with generic typing to allow for TreeNodes of
 *            different value types
 */
public class BstIterator<T extends Comparable<T>> implements Iterator<TreeNode<T>> {

  private final ArrayDeque<TreeNode<T>> pathStack;

  public BstIterator(TreeNode<T> root) {
    pathStack = new ArrayDeque<>();
    pushPathToNextSmallest(root);
  }

  /**
   * This BstIterator manages to use O(h) extra space, where h is the height of the tree It achieves
   * this by maintaining a stack of the nodes to handle (pushing all left nodes first), before
   * handling self or right node.
   *
   * @param node TreeNode that acts as root of the subtree we're interested in.
   */
  private void pushPathToNextSmallest(TreeNode<T> node) {
    while (node != null) {
      pathStack.push(node);
      node = node.getLeft();
    }
  }

  /**
   * Checks if there exists next element.
   *
   * @return true if this iterator has a "next" element
   */
  @Override
  public boolean hasNext() {
    return !pathStack.isEmpty();
  }

  /**
   * Gets the next element.
   *
   * @return TreeNode next. The next element according to our in-order traversal of the given BST
   * @throws NoSuchElementException if this iterator does not have a next element
   */
  @Override
  public TreeNode<T> next() throws NoSuchElementException {
    if (pathStack.isEmpty()) {
      throw new NoSuchElementException();
    }
    TreeNode<T> next = pathStack.pop();
    pushPathToNextSmallest(next.getRight());
    return next;
  }

}
