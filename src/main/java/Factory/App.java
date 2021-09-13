package Factory;

import lombok.extern.slf4j.Slf4j;

/**
 * 在工厂类中提供一个封装的静态工厂方法，用于隐藏对象初始化细节，使客户端代码可以专注于使用，而不用关心类的初始化过程。
 *
 * <p>这是一个炼金师制造硬币的例子. CoinFactory 是一个工厂类并且提供了一个静态方法来创建不同的硬币.
 */

@Slf4j
public class App {

    public static void main(String[] args) {
        LOGGER.info("炼金师开始工作.");
        Coin coin1 = CoinFactory.getCoin(CoinType.COPPER);
        Coin coin2 = CoinFactory.getCoin(CoinType.GOLD);
        LOGGER.info(coin1.getDescription());
        LOGGER.info(coin2.getDescription());
    }
}
