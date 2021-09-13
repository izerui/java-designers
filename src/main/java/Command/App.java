package Command;

import lombok.extern.slf4j.Slf4j;

/**
 * 命令模式是一种行为设计模式，其中对象用于封装执行操作或稍后触发事件所需的所有信息。此信息包括方法名称、拥有该方法的对象以及方法参数的值。
 *
 * <p>与命令模式相关的四个术语是 命令(Command)、接收者(Receiver)、调用者(Invoker)和客户端(Client)。
 * 命令对象（Command）引导接收者（Receiver）并调用接收者的实际方法。
 * 调用程序(Invoker)对象接收对要执行的命令(Command)的引用，并可选择对命令进行执行。调用者并不知道命令是如何执行的。
 * 客户端决定在哪些点执行哪些命令。为了执行命令，它将函数的引用传递给调用者对象。
 *
 * <p>换句话说，在这个例子中，App 扮演客户端角色(项目经理), 项目经理让开发主管(Invoker)接收者开发两个产品,然后主管分别把OA任务交给阿黄去做, 把CRM任务交给棒棒王去做.
 * 后续还可以扩展: 项目经理让开发主管去配客户喝酒, 开发主管把不同客户的任务交给妹子A和妹子B去陪酒.
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        LeaderInvoker leaderInvoker = new LeaderInvoker();
        // 给阿黄提了一个OA任务
        leaderInvoker.handler(new OACommand(new DeveloperReceiver("阿黄")));
        // 取消任务
        leaderInvoker.cacelTask();
        // 给帮帮王提了一个CRM任务
        leaderInvoker.handler(new CrmCommand(new DeveloperReceiver("棒棒王")));
        // 取消任务
        leaderInvoker.cacelTask();
    }
}
