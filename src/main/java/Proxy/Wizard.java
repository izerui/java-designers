package Proxy;

import lombok.RequiredArgsConstructor;

/**
 * 巫师.
 */
@RequiredArgsConstructor
public class Wizard {

  private final String name;

  @Override
  public String toString() {
    return name;
  }

}
