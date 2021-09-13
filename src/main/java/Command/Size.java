package Command;

import lombok.RequiredArgsConstructor;

/**
 * Enumeration for target size.
 */
@RequiredArgsConstructor
public enum Size {

  SMALL("small"),
  NORMAL("normal");

  private final String title;

  @Override
  public String toString() {
    return title;
  }
}
