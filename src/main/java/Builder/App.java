package Builder;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

@Slf4j
public class App {

    public static void main(String[] args) {
        Hero mage = new Hero.Builder(Profession.MAGE, "Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();
        LOGGER.info(mage.toString());
    }
}
