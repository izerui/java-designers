package Command;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Wizard is the invoker of the commands.
 */
@Slf4j
public class Wizard {

  private final Deque<Runnable> undoStack = new LinkedList<>();
  private final Deque<Runnable> redoStack = new LinkedList<>();

  public Wizard() {
  }

  /**
   * Cast spell.
   */
  public void castSpell(Runnable runnable) {
    runnable.run();
    undoStack.offerLast(runnable);
  }

  /**
   * Undo last spell.
   */
  public void undoLastSpell() {
    if (!undoStack.isEmpty()) {
      Runnable previousSpell = undoStack.pollLast();
      redoStack.offerLast(previousSpell);
      previousSpell.run();
    }
  }

  /**
   * Redo last spell.
   */
  public void redoLastSpell() {
    if (!redoStack.isEmpty()) {
      Runnable previousSpell = redoStack.pollLast();
      undoStack.offerLast(previousSpell);
      previousSpell.run();
    }
  }

  @Override
  public String toString() {
    return "Wizard";
  }
}
