package Composite;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * 将对象组合成树结构以表示部分整体层次结构。 组合可以使客户统一对待单个对象和组合对象。
 *
 * <p>在这个例子中，我们有由字母组成的单词,由单词组成的句子。所有对象都可以通过相同的接口 ({@link LetterComposite}) 进行处理。
 *
 */
@Slf4j
public class App {


  public static void main(String[] args) {

    var messenger = new Messenger();

    LOGGER.info("来自兽人的消息: ");
    messenger.messageFromOrcs().print();

    LOGGER.info("来自精灵的消息: ");
    messenger.messageFromElves().print();
  }
}
