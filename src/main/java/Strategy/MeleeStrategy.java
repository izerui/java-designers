package Strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Melee strategy.
 */
@Slf4j
public class MeleeStrategy implements DragonSlayingStrategy {

  @Override
  public void execute() {
    LOGGER.info("With your Excalibur you sever the dragon's head!");
  }
}
