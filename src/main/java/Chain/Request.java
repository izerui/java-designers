package Chain;

import lombok.Getter;

import java.util.Objects;

/**
 * 请求类.
 */
public class Request {

  /**
   * 请求的类型，链中每个处理者通过它来判断是否执行命令。
   */
  @Getter
  private final RequestType requestType;

  /**
   * 命令
   */
  @Getter
  private final String requestDescription;

  /**
   * 标示是否处理过。请求只能从未处理状态切换到已处理状态，无法“取消处理”请求。
   */
  @Getter
  private boolean handled;

  /**
   * 创建一个指定类型和命令的请求
   *
   * @param requestType        请求类型
   * @param requestDescription 请求命令描述
   */
  public Request(final RequestType requestType, final String requestDescription) {
    this.requestType = Objects.requireNonNull(requestType);
    this.requestDescription = Objects.requireNonNull(requestDescription);
  }

  /**
   * 标记处理过
   */
  public void markHandled() {
    this.handled = true;
  }

  @Override
  public String toString() {
    return getRequestDescription();
  }

}
