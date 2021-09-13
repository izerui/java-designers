package Proxy;

import lombok.RequiredArgsConstructor;

/**
 * Wizard.
 */
@RequiredArgsConstructor
public class Wizard {

  private final String name;

  @Override
  public String toString() {
    return name;
  }

}
