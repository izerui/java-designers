package Composite;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * The Composite pattern is a partitioning design pattern. The Composite pattern describes that a
 * group of objects is to be treated in the same way as a single instance of an object. The intent
 * of a composite is to "compose" objects into tree structures to represent part-whole hierarchies.
 * Implementing the Composite pattern lets clients treat individual objects and compositions
 * uniformly.
 *
 * <p>In this example we have sentences composed of words composed of letters. All of the objects
 * can be treated through the same interface ({@link LetterComposite}).
 *
 */
@Slf4j
public class App {

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {

    var messenger = new Messenger();

    LOGGER.info("Message from the orcs: ");
    messenger.messageFromOrcs().print();

    LOGGER.info("Message from the elves: ");
    messenger.messageFromElves().print();
  }
}
