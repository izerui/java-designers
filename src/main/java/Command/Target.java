package Command;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Base class for spell targets.
 */
@Setter
@Slf4j
@Getter
public abstract class Target {

  private Size size;

  private Visibility visibility;

  /**
   * Print status.
   */
  public void printStatus() {
    LOGGER.info("{}, [size={}] [visibility={}]", this, getSize(), getVisibility());
  }

  /**
   * Changes the size of the target.
   */
  public void changeSize() {
    Size oldSize = getSize() == Size.NORMAL ? Size.SMALL : Size.NORMAL;
    setSize(oldSize);
  }

  /**
   * Changes the visibility of the target.
   */
  public void changeVisibility() {
    Visibility visible = getVisibility() == Visibility.INVISIBLE
            ? Visibility.VISIBLE : Visibility.INVISIBLE;
    setVisibility(visible);
  }
}
