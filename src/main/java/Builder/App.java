package Builder;

import java.util.logging.Logger;

public class App {

    private final static Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) {
        Hero mage = new Hero.Builder(Profession.MAGE, "Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();
        LOGGER.info(mage.toString());
    }
}
