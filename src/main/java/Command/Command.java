package Command;

/**
 * 命令基类
 */
public interface Command {
   //执行命令
   void execute();
   //撤销命令
   void undo();
   // 命令内容
   String getName();
}
