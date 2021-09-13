package Memento;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * 备忘录是一种行为设计模式， 允许生成对象状态的快照并在以后将其还原。
 *
 * <p>在此示例中，对象 ({@link Star}) 发出包含对象状态的“快照类”({@link StarMemento})。稍后可以将纪念品设置回恢复状态的对象。
 */
@Slf4j
public class App {

  /**
   * Program entry point.
   */
  public static void main(String[] args) {
    Stack<StarMemento> states = new Stack<StarMemento>();

    Star star = new Star(StarType.SUN, 10000000, 500000);
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    states.add(star.getMemento());
    star.timePasses();
    LOGGER.info(star.toString());
    while (states.size() > 0) {
      star.setMemento(states.pop());
      LOGGER.info(star.toString());
    }
  }
}
