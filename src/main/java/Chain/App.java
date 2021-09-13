package Chain;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链模式: 它帮助构建一串对象。请求从一个对象中进入并结束然后进入到一个个对象中直到找到合适的处理器。
 *
 * <p>在这个例子中，我们将请求处理程序 ({@link RequestHandler}) 组织成一个链，其中每个处理程序都有机会处理请求。
 * 在这里，兽王 ({@link OrcKing}) 发出命令，兽人指挥官 ({@link OrcCommander}、兽人中士{@link OrcOfficer}、兽人士兵{@link OrcSoldier}) 形成处理链。
 */
@Slf4j
public class App {


  public static void main(String[] args) {
    OrcKing king = new OrcKing();
    king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "保卫城堡"));
    king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "折磨囚犯"));
    king.makeRequest(new Request(RequestType.COLLECT_TAX, "收税"));
  }
}
