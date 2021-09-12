package Factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        Car car1 = CarsFactory.getCar(CarType.FORD);
        Car car2 = CarsFactory.getCar(CarType.FERRARI);
        LOGGER.info(car1.getDescription());
        LOGGER.info(car2.getDescription());
    }
}
