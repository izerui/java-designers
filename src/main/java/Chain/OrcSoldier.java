package Chain;

/**
 * 兽人士兵处理类.
 */
public class OrcSoldier extends RequestHandler {

  public OrcSoldier(RequestHandler handler) {
    super(handler);
  }

  @Override
  public void handleRequest(Request req) {
    if (RequestType.COLLECT_TAX == req.getRequestType()) {
      printHandling(req);
      req.markHandled();
    } else {
      super.handleRequest(req);
    }
  }

  @Override
  public String toString() {
    return "兽人士兵";
  }
}
