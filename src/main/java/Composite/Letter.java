package Composite;

import lombok.RequiredArgsConstructor;

/**
 * Letter.
 */
@RequiredArgsConstructor
public class Letter extends LetterComposite {

  private final char character;

  @Override
  protected void printThisBefore() {
    System.out.print(character);
  }
}
