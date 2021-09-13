package Chain;

/**
 * 兽人指挥官处理类.
 */
public class OrcCommander extends RequestHandler {

  public OrcCommander(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (RequestType.DEFEND_CASTLE == req.getRequestType()) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "兽人指挥官";
  }
}
