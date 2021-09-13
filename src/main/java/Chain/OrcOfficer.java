package Chain;

/**
 * 兽人中士处理类.
 */
public class OrcOfficer extends RequestHandler {

  public OrcOfficer(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (RequestType.TORTURE_PRISONER == req.getRequestType()) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "兽人中士";
  }

}
