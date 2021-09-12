package Factory;

import java.util.logging.Logger;

public class App {

    private final static Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) {
        Car car1 = CarsFactory.getCar(CarType.FORD);
        Car car2 = CarsFactory.getCar(CarType.FERRARI);
        LOGGER.info(car1.getDescription());
        LOGGER.info(car2.getDescription());
    }
}
