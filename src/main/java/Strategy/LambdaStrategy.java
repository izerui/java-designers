package Strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaStrategy {

  public enum Strategy implements DragonSlayingStrategy {
    MeleeStrategy(() -> LOGGER.info(
        "用你的剑斩断了龙的头颅!")),
    ProjectileStrategy(() -> LOGGER.info(
        "你用魔法弩射龙,龙倒在地上死了!")),
    SpellStrategy(() -> LOGGER.info(
        "你施展了瓦解咒语，巨龙在一堆尘土中蒸发！"));

    private final DragonSlayingStrategy dragonSlayingStrategy;

    Strategy(DragonSlayingStrategy dragonSlayingStrategy) {
      this.dragonSlayingStrategy = dragonSlayingStrategy;
    }

    @Override
    public void execute() {
      dragonSlayingStrategy.execute();
    }
  }
}
