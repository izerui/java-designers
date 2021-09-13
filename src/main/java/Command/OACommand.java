package Command;

/**
 * 开发一个OA的命令,会让命令接收者干重复的活
 */
public class OACommand implements Command {

   private DeveloperReceiver receiver;

   public OACommand(DeveloperReceiver receiver) {
     this.receiver = receiver;
   }

   @Override
   public void execute() {
     receiver.handleCode();
   }

   @Override
   public void undo() {
     receiver.cancelCode();
   }

    @Override
    public String getName() {
        return "开发一个OA";
    }
}
