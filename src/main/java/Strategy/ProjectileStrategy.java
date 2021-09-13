package Strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Projectile strategy.
 */
@Slf4j
public class ProjectileStrategy implements DragonSlayingStrategy {

  @Override
  public void execute() {
    LOGGER.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!");
  }
}
