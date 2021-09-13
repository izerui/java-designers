package Chain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理基类
 */
@Slf4j
public abstract class RequestHandler {

  private final RequestHandler next;

  /**
   * 构造方法,接收下一个处理类
   * @param next
   */
  public RequestHandler(RequestHandler next) {
    this.next = next;
  }

  /**
   * 接收请求
   */
  public void handleRequest(Request req) {
    if (next != null) {
      next.handleRequest(req);
    }
  }

  protected void printHandling(Request req) {
    LOGGER.info("{} 处理请求 \"{}\"", this, req);
  }

  @Override
  public abstract String toString();
}
