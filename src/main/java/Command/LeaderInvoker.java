package Command;

import lombok.extern.slf4j.Slf4j;

/**
 * 开发主管
 */
@Slf4j
public class LeaderInvoker {
    // 命令
    private Command command;

    //实现需求任务
    public void handler(Command command) {
        this.command = command;
        LOGGER.info("新任务: " + command.getName());
        command.execute();
    }

    public void cacelTask() {
        command.undo();
    }
}
