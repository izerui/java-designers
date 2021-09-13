package Composite;

import java.util.List;

/**
 * 单词.
 */
public class Word extends LetterComposite {

  /**
   * 单词的构造方法.
   * @param letters 字母集合
   */
  public Word(List<Letter> letters) {
    letters.forEach(this::add);
  }

  /**
   * 单词的构造方法.
   * @param letters 字母数组
   */
  public Word(char... letters) {
    for (char letter : letters) {
      this.add(new Letter(letter));
    }
  }

  @Override
  protected void printThisBefore() {
    System.out.print(" ");
  }
}
