package readentities;

public interface CustomObservable {
  /** Method that add all the observers */
  void attach();
  /** Method that notifies all the observers */
  void notifyAllObservers();
}
