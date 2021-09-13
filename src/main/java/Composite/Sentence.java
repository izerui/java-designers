package Composite;

import java.util.List;

/**
 * 句子.
 */
public class Sentence extends LetterComposite {

  /**
   * 构造方法.
   */
  public Sentence(List<Word> words) {
    words.forEach(this::add);
  }

  @Override
  protected void printThisAfter() {
    System.out.print(".\n");
  }
}
