package Command;

/**
 * 开发一个CRM的命令,会让命令接收者干重复的活
 */
public class CrmCommand implements Command {

   private DeveloperReceiver developerReceiver;

   public CrmCommand(DeveloperReceiver developerReceiver) {
     this.developerReceiver = developerReceiver;
   }

   @Override
   public void execute() {
     developerReceiver.handleCode();
   }


   @Override
   public void undo() {
     developerReceiver.cancelCode();
   }

    @Override
    public String getName() {
        return "开发一个CRM";
    }
}
