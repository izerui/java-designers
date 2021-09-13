package Chain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RequestHandler.
 */
@Slf4j
@AllArgsConstructor
public abstract class RequestHandler {

  private final RequestHandler next;

  /**
   * Request handler.
   */
  public void handleRequest(Request req) {
    if (next != null) {
      next.handleRequest(req);
    }
  }

  protected void printHandling(Request req) {
    LOGGER.info("{} handling request \"{}\"", this, req);
  }

  @Override
  public abstract String toString();
}
