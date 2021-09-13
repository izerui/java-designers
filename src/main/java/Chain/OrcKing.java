package Chain;

import lombok.extern.slf4j.Slf4j;

/**
 * 兽人国王发出处理链请求
 */
@Slf4j
public class OrcKing {

    private RequestHandler chain;

    public OrcKing() {
        buildChain();
    }

    private void buildChain() {
        // 定义了一个处理类,层级层次为: 指挥官 -> 中士 -> 士兵
        chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
    }

    // 处理请求
    public void makeRequest(Request req) {
        LOGGER.info("兽人国王下达命令: " + req.getRequestDescription());
        chain.handleRequest(req);
    }

}
