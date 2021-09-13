package Command;

import lombok.extern.slf4j.Slf4j;

/**
 * 开发者: 命令接收者
 */
@Slf4j
public class DeveloperReceiver {

    // 人名
    private String name;

    public DeveloperReceiver(String name) {
        this.name = name;
    }

    public void handleCode() {
        LOGGER.info(name + "埋头撸代码");
    }

    public void cancelCode() {
        LOGGER.info(name + "删除代码逻辑");
    }
}
