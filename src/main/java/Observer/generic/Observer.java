package Observer.generic;

/**
 * 观察员泛型基类.
 *
 * @param <S> Observable
 * @param <O> Observer
 * @param <A> Action
 */
public interface Observer<S extends Observable<S, O, A>, O extends Observer<S, O, A>, A> {

  void update(S subject, A argument);
}
